#!/usr/bin/env python3
"""pxl2png - turns hand-authored .pxl pixel art files into PNG files.

Nothing is drawn, noised or randomised here. Every pixel comes from the
character grids written by hand in art/pxl/*.pxl. This script only looks up
each character in the palette and encodes the PNG.

usage: python3 tools/pxl2png.py art/pxl src/main/resources [--preview DIR]
"""
import sys, struct, zlib, pathlib


def parse(path):
    w = h = None
    colors, patterns, places, output, cur = {}, {}, [], None, None
    for raw in pathlib.Path(path).read_text().splitlines():
        line = raw.strip()
        if cur is not None:
            if line == "end":
                cur = None
            elif line and not line.startswith("//"):
                patterns[cur].append(line)
            continue
        if not line or line.startswith("//"):
            continue
        p = line.split()
        if p[0] == "size":
            w, h = int(p[1]), int(p[2])
        elif p[0] == "output":
            output = p[1]
        elif p[0] == "color":
            v = p[2].lstrip("#")
            a = int(v[6:8], 16) if len(v) == 8 else 255
            colors[p[1]] = (int(v[0:2], 16), int(v[2:4], 16), int(v[4:6], 16), a)
        elif p[0] == "pattern":
            cur = p[1]
            patterns[cur] = []
        elif p[0] == "place":
            places.append((p[1], int(p[2]), int(p[3]), "flipx" in p[4:]))
        else:
            raise SystemExit(f"{path}: unknown line: {line}")
    return w, h, colors, patterns, places, output


def render(path):
    w, h, colors, patterns, places, output = parse(path)
    px = [[(0, 0, 0, 0)] * w for _ in range(h)]
    for name, ox, oy, flip in places:
        rows = patterns[name]
        pw = len(rows[0])
        for ry, row in enumerate(rows):
            if len(row) != pw:
                raise SystemExit(f"{path}: pattern {name} row {ry} has {len(row)} chars, expected {pw}")
            if flip:
                row = row[::-1]
            for rx, ch in enumerate(row):
                if ch == ".":
                    continue
                if ch not in colors:
                    raise SystemExit(f"{path}: pattern {name} uses undefined color {ch}")
                x, y = ox + rx, oy + ry
                if not (0 <= x < w and 0 <= y < h):
                    raise SystemExit(f"{path}: pattern {name} goes out of bounds at {x},{y}")
                px[y][x] = colors[ch]
    return w, h, px, output


def chunk(t, d):
    c = struct.pack(">I", len(d)) + t + d
    return c + struct.pack(">I", zlib.crc32(t + d) & 0xFFFFFFFF)


def png(w, h, rows, rgba=True):
    ch = 4 if rgba else 3
    raw = b"".join(b"\x00" + bytes(v for p in r for v in p[:ch]) for r in rows)
    return (b"\x89PNG\r\n\x1a\n"
            + chunk(b"IHDR", struct.pack(">IIBBBBB", w, h, 8, 6 if rgba else 2, 0, 0, 0))
            + chunk(b"IDAT", zlib.compress(raw, 9)) + chunk(b"IEND", b""))


def preview(w, h, px):
    s = max(1, 384 // max(w, h))
    out = []
    for y in range(h * s):
        row = []
        for x in range(w * s):
            r, g, b, a = px[y // s][x // s]
            bg = 74 if ((x // (s * 2)) + (y // (s * 2))) % 2 == 0 else 96
            al = a / 255.0
            row.append((int(r * al + bg * (1 - al)), int(g * al + bg * (1 - al)), int(b * al + bg * (1 - al))))
        out.append(row)
    return png(w * s, h * s, out, rgba=False)


def main():
    src, dst = pathlib.Path(sys.argv[1]), pathlib.Path(sys.argv[2])
    prev = pathlib.Path(sys.argv[sys.argv.index("--preview") + 1]) if "--preview" in sys.argv else None
    for f in sorted(src.glob("*.pxl")):
        w, h, px, output = render(f)
        target = dst / output
        target.parent.mkdir(parents=True, exist_ok=True)
        target.write_bytes(png(w, h, px))
        if prev:
            prev.mkdir(parents=True, exist_ok=True)
            (prev / (f.stem + ".png")).write_bytes(preview(w, h, px))
        print("wrote", target)


main()
