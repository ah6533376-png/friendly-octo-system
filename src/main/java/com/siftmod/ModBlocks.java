package com.siftmod;

import com.siftmod.block.HushSiltBlock;
import com.siftmod.block.RiftPortalBlock;
import com.siftmod.block.WailThornBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.GlassBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public final class ModBlocks {
    private ModBlocks() {}

    // safe blocks
    public static final Block SOULSTONE = register("soulstone", new Block(AbstractBlock.Settings.create()
            .mapColor(MapColor.PINK).strength(1.5f, 6.0f).requiresTool().sounds(BlockSoundGroup.STONE)));

    public static final Block WISP_BULB = register("wisp_bulb", new Block(AbstractBlock.Settings.create()
            .mapColor(MapColor.YELLOW).strength(0.6f).luminance(state -> 13).sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    public static final Block SOULGLASS = register("soulglass", new GlassBlock(glass(MapColor.PURPLE)));

    public static final Block RIMEGLASS = register("rimeglass", new GlassBlock(glass(MapColor.LIGHT_BLUE).slipperiness(0.98f)));

    // hazardous blocks
    public static final Block WAIL_THORN = register("wail_thorn", new WailThornBlock(AbstractBlock.Settings.create()
            .mapColor(MapColor.PURPLE).noCollision().breakInstantly().nonOpaque()
            .sounds(BlockSoundGroup.SWEET_BERRY_BUSH).pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block HUSH_SILT = register("hush_silt", new HushSiltBlock(AbstractBlock.Settings.create()
            .mapColor(MapColor.PURPLE).strength(0.6f).sounds(BlockSoundGroup.SOUL_SAND)
            .velocityMultiplier(0.5f).jumpVelocityMultiplier(0.7f)));

    // technical block: the rift itself, not in the creative tab
    public static final Block RIFT_PORTAL = register("rift_portal", new RiftPortalBlock(AbstractBlock.Settings.create()
            .mapColor(MapColor.PURPLE).noCollision().strength(0.5f).luminance(state -> 15)
            .sounds(BlockSoundGroup.AMETHYST_BLOCK).nonOpaque().dropsNothing()));

    private static AbstractBlock.Settings glass(MapColor color) {
        return AbstractBlock.Settings.create().mapColor(color).strength(0.3f).sounds(BlockSoundGroup.GLASS)
                .nonOpaque()
                .allowsSpawning((state, world, pos, type) -> false)
                .solidBlock((state, world, pos) -> false)
                .suffocates((state, world, pos) -> false)
                .blockVision((state, world, pos) -> false);
    }

    private static Block register(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(SiftMod.ID, name), block);
    }

    public static void init() {}
}
