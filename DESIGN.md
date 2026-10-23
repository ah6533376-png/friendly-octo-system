# the sift - dimension pitch

team, this is the one. a fourth dimension that looks like a candy shop and plays like a lullaby sung by a graveyard.

**legend**
- [canon] = confirmed by mojang, taken as given
- [invented] = my guess, swap it out freely
- [coded] = already implemented in this prototype mod (everything else is design only)

every line is [invented] unless it is tagged [canon].

---

## 1. dimension overview

**what is canon**
- [canon] the sift is a fourth dimension next to the overworld, nether and end
- [canon] mojang calls it beautiful, vibrant and teeming with souls
- [canon] bright pastel colors, pink grass, way more colorful than normal biomes
- [canon] new mobs (friendly and hostile), new blocks (some hazardous), new biomes
- [canon] known creatures: the blub and an ice zombie
- [canon] mysterious rifts in minecraft dungeons 2 lead into it
- [canon] longer post-game content than the nether

**tone and atmosphere**
- [invented] the pitch line: "the prettiest place that has ever watched you back"
- [invented] everything is soft, round and glowing, but nothing is ever fully alone. you always feel like the air is full of someone
- [invented] no hard darkness anywhere on the surface. the scary part is that it never gets dark enough to hide

**sky and lighting**
- [invented][coded] the sift sits in permanent golden hour. time is frozen at late afternoon so shadows are long and the light is warm
- [invented][coded] each biome has its own sky and fog tint (pink, lavender, icy blue, butter yellow, mint), and you watch the sky color slide as you cross borders
- [invented] a huge pale ring hangs low in the sky instead of a sun disk (needs a custom sky renderer, not coded)
- [invented][coded] ambient light is slightly raised so caves feel dim instead of pitch black
- [invented][coded] beds do not work here. you cannot sleep in a place that never stops humming

**ambient sound**
- [invented][coded] soft whispers fade in and out, borrowed from soul sand valley sounds in the prototype
- [invented] wind chime notes that shift key per biome, a low choir hum that swells near rifts and lost souls, and no cave rumble. replace with custom sounds later
- [invented] footsteps are slightly muffled on grass so mobs seem to appear out of nowhere

**ambient visuals**
- [invented][coded] slow floating soul wisps (the vanilla soul particle) drift upward in every biome, thicker in the mint veil

**the vibe of exploring**
- [invented] you do not hunt for danger, danger hunts for the moment you stop looking at the pretty thing
- [invented] soul wisp trails and glowing bulbs act as a natural breadcrumb system. follow the glow to find structures

---

## 2. biomes (5)

biomes are picked by temperature and humidity on the overworld climate noise, so they come in big readable regions. all use the overworld terrain shaper so the world feels like minecraft.

### blush meadows - the starter biome [coded]
- palette: [invented] bubblegum pink grass #FF9AC8, cherry leaves, sky #F9C6EA
- terrain: [invented] rolling hills, shallow pink ponds
- signature blocks: [invented] pink grass, cherry trees, wisp bulb clusters, small wail thorn patches
- mobs: [invented] blubs (common)
- why: [invented] it is the first thing you see through a rift. safe enough to build a base, with a hint of thorns to teach you the rules

### whisper fen [coded]
- palette: [invented] lavender grass #B9A2FF, violet water, sky #CDB9FF
- terrain: [invented] flat, boggy, lots of shallow water
- signature blocks: [invented] giant mushrooms, hush silt pools, wisp bulbs
- mobs: [invented] blubs (uncommon). planned: hush wraiths
- why: [invented] the first real hazard biome. beautiful, quiet, and the silt makes the dark creep in

### frostbloom fields [coded]
- palette: [invented] icy cyan grass #AEE6FF, frosted snow caps, sky #BFE9FF
- terrain: [invented] wide rolling frozen plains with crystal snow drifts
- signature blocks: [invented] snow layer, snowy trees, rimeglass ruins (planned)
- mobs: [invented] ice zombies (common), blubs (rare)
- why: [invented] the first combat biome. pastel snow is the prettiest place to get frostbitten

### sunmote steppe [coded]
- palette: [invented] butter yellow grass #FFE08A, peach foliage, sky #FFE8B8
- terrain: [invented] huge open plains with sparse trees and thorn thickets
- signature blocks: [invented] wail thorn thickets everywhere
- mobs: [invented] blubs. planned: thornbacks, sunmote swarms
- why: [invented] open ground and long sight lines. the danger here is the terrain, not the mobs

### mint veil [coded]
- palette: [invented] mint grass #8FF0D0, teal water, sky #B8F5E6
- terrain: [invented] dense forest with amethyst geodes under the surface
- signature blocks: [invented] amethyst geodes, wisp bulbs, hush silt pools
- mobs: [invented] ice zombies (some), blubs. planned: bulbfangs, lost souls
- why: [invented] the densest soul particles. this is where the late game structures live

---

## 3. mobs (8)

mobs marked [coded] have working entities in the prototype. the rest are design only.

### 1. blub [canon creature, rest invented] - friendly [coded]
- [canon] mysterious creature
- behavior: [invented] shy glowing blob. follows anyone holding a soul wisp or wisp bulb, flees ice zombies, wanders in small groups
- drops: [invented] 0 to 1 soul wisp (please do not hit it)
- unique mechanic: [invented] **gift**: feed it a soul wisp and it burbles, then gives you regeneration and glowing for a short time. it becomes the early game scout because ice zombies chase it

### 2. ice zombie [canon enemy, rest invented] - hostile [coded]
- behavior: [invented] a tougher zombie that does not burn in the sift sun, hunts blubs and players
- drops: [invented] 0 to 2 soul wisps, 0 to 3 snowballs
- unique mechanic: [invented] **frostbite stacking**: each hit adds freeze time and slowness, so three hits in a row will freeze you like powder snow. it is immune to freezing itself and shatters into snowflakes when it dies

### 3. chimewing - friendly flyer
- behavior: [invented] small pastel birds that flock in blush meadows and sunmote steppe
- drops: [invented] chime feather (crafting item for sound-based tools)
- unique mechanic: [invented] **warning song**: when a hostile mob is within 24 blocks the flock sings. pitch rises as the threat gets closer

### 4. hush wraith - hostile
- behavior: [invented] tall pale silhouette that lives near hush silt. silent footsteps
- drops: [invented] 1 to 2 soul wisps, rarely a hush veil (crafting item for darkness-resist gear)
- unique mechanic: [invented] **echo**: it replays your footsteps a few seconds later from where you walked, so you hear it coming from where you were. sneaking breaks the echo

### 5. thornback - neutral
- behavior: [invented] armored grazer that eats wail thorns and stays calm until you break thorns near it
- drops: [invented] thorn hide (armor material)
- unique mechanic: [invented] **roll**: when hurt it curls up, takes 80 percent less damage and rolls straight at you

### 6. sunmote swarm - friendly ambient
- behavior: [invented] tiny glowing motes that drift in the steppe
- drops: [invented] none. catch them with a glass bottle to get a bottled sunmote (light source)
- unique mechanic: [invented] **orbit**: they circle whoever is carrying the most soul wisps, lighting up the area like a soft halo

### 7. bulbfang - hostile ambusher
- behavior: [invented] anglerfish-like predator in the mint veil with a lure that looks exactly like a wisp bulb
- drops: [invented] 1 soul wisp, a rare lure bulb (reusable light source)
- unique mechanic: [invented] **fake light**: it hides as a wisp bulb block. mining the wrong bulb makes it spring out. real bulbs make a tiny chime when mined

### 8. lost soul - friendly escort
- behavior: [invented] pale humanoid wisp that wanders confused. follows lantern light
- drops: [invented] none. it gives a reward when delivered
- unique mechanic: [invented] **guide it home**: hold a soul wisp to lead it to a soul well structure. hostile mobs target it. deliver it safely for a soul shard and experience, lose it and nothing is lost but the chance

---

## 4. blocks (6) - all implemented [coded]

| block | type | what it does | how you get it |
|---|---|---|---|
| soulstone | safe | pastel stone that makes up big veins in the terrain. main building block, soft pink and lavender | [invented] mine with a pickaxe |
| wisp bulb | safe | glowing cube, light level 13. also tempts blubs | [invented] mine from natural patches |
| soulglass | safe | translucent lavender glass, never spawns mobs on top | [invented] craft: 8 glass around 1 wisp bulb |
| rimeglass | safe | frosty glass that is very slippery like ice | [invented] craft: 1 soulglass + 1 ice |
| wail thorn | **hazard** | thorny bramble. hurts anything that moves through it, slows you, and wails (soul escape sound) when it cuts you. blubs are immune | [invented] natural patches, breaks instantly |
| hush silt | **hazard** | soft silt that slows you to a crawl, reduces your jump height and gives you the darkness effect while you stand in it. sneaking across avoids the darkness | [invented] natural pools, dig with a shovel |

technical: [invented][coded] the **rift** block is the portal itself. it is not in the creative tab.

---

## 5. portal and travel [coded]

**opening a rift**
- [invented] craft a **rift shard**: 1 dragon breath + 1 ender pearl + 2 amethyst shards. that gates the sift behind beating the ender dragon, so it is a true post-game dimension
- [invented] right click the ground in the overworld with a shard to tear open a 2 block tall rift column
- [invented] walk into it to enter the sift. travel is 1:1 (no coordinate scaling) so rifts stay predictable instead of copying the nether 8:1 rule
- [canon] rifts are the way in (from minecraft dungeons 2)

**arrival**
- [invented] you arrive at the same x and z on the surface. a return rift appears 2 blocks east on a small soulstone pad
- [invented] return rifts in the sift cannot be broken, so you cannot strand yourself. rifts in the overworld break instantly
- [invented] standing inside a rift right after arriving does not bounce you back

**getting home**
- [invented] walk into the return rift. it takes you to the same x and z in the overworld and plants a rift pad there

**wild rifts** (design only)
- [invented] after the dragon dies, rare natural rifts appear next to amethyst geodes in the overworld, matching the dungeons 2 mystery

---

## 6. progression - why it outlasts the nether (design only, except soul wisps)

**the loop: wisps, light, structures, shards, heart**
1. **entry (blush meadows)**: soulstone, wisp bulbs and your first soul wisps from blubs and ice zombies. build a base near the return rift
2. **resource tier**
   - soul wisp [coded]: dropped by mobs. currency, fuel, mob bait
   - soulstone [coded]: building and tool crafting
   - thorn hide, chime feather, hush veil: gear materials from mobs
   - keepsake shards: dropped by structures
3. **structures (one per biome)**: [invented]
   - soul well (blush meadows): where lost souls are delivered
   - rimeglass ruins (frostbloom fields): frozen vaults with keepsake shards
   - hush crypt (whisper fen): dark, silent dungeon full of hush wraiths
   - sunmote spire (sunmote steppe): tall tower with a long climb
   - veil sanctum (mint veil): the deepest structure, gated by bulbfangs
4. **gear**: [invented] wisp tier tools and armor from soulstone and soul wisps. one perk per biome material (thorn hide reflects damage, hush veil cancels darkness, chime feather alerts you to threats)
5. **endgame goal**: [invented] collect one keepsake shard from each of the 5 structures and bring them to the **sift heart**, an altar floating at the world border of the sift
6. **boss: the hollow choir**: [invented] a huge pastel ring of souls that sings in phases. each phase is a different note you have to answer by standing on the right colored tile. kills reward a **choir crown** (cosmetic plus wisp magnet) and the right to craft rift anchors

**why it is longer than the nether**
- [invented] five biomes with five structures that each need a different counter, instead of one fortress and one bastion
- [invented] gear that only works well after you visit other biomes, so you loop the whole dimension instead of strip mining one layer

---

## 7. the soul theme in gameplay

souls are not decoration, they are the economy and the threat.

- **soul wisps are currency and bait** [coded]: they drop from mobs, feed blubs, tempt blubs, and (planned) fuel gear and furnaces
- **souls are escorted** [design]: the lost soul mob turns the dimension into an escort mission. protecting something fragile is the opposite of the usual minecraft loop
- **souls warn you** [design]: chimewings sing when danger is close, so listening becomes a skill
- **souls punish noise** [design]: the hush wraith echo makes your own footsteps a liability. sneaking is a strategy, not just a trick
- **souls tempt you** [design]: bulbfangs disguise themselves as the glow you want. the same light that guides you can kill you
- **frostbite is chill, not damage** [coded]: ice zombies leave you slowed and frozen, not hurt, which keeps the fights fair
- **death in the sift** [design, invented]: your items stay in a soul urn at the spot where you died, and hostile mobs avoid it for two minutes. the dimension is kind to the dead, it just wants you to come back and listen

---

## what is real in the prototype

| area | status |
|---|---|
| dimension, dimension type, 5 biomes, features | [coded] json worldgen |
| 6 blocks plus rift block | [coded] with hand-authored textures |
| rift shard, soul wisp, spawn eggs, creative tab | [coded] |
| blub, ice zombie | [coded] with models and textures |
| chimewing, hush wraith, thornback, sunmote swarm, bulbfang, lost soul | design only |
| structures, boss, gear, custom sounds, custom sky | design only |
