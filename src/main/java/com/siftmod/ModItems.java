package com.siftmod;

import com.siftmod.item.RiftShardItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public final class ModItems {
    private ModItems() {}

    public static final Item RIFT_SHARD = register("rift_shard",
            new RiftShardItem(new Item.Settings().maxCount(16).rarity(Rarity.EPIC)));

    public static final Item SOUL_WISP = register("soul_wisp", new Item(new Item.Settings()) {
        @Override
        public boolean hasGlint(ItemStack stack) {
            return true;
        }
    });

    public static final Item SOULSTONE = blockItem("soulstone", ModBlocks.SOULSTONE);
    public static final Item WISP_BULB = blockItem("wisp_bulb", ModBlocks.WISP_BULB);
    public static final Item SOULGLASS = blockItem("soulglass", ModBlocks.SOULGLASS);
    public static final Item RIMEGLASS = blockItem("rimeglass", ModBlocks.RIMEGLASS);
    public static final Item WAIL_THORN = blockItem("wail_thorn", ModBlocks.WAIL_THORN);
    public static final Item HUSH_SILT = blockItem("hush_silt", ModBlocks.HUSH_SILT);

    public static final Item BLUB_SPAWN_EGG = register("blub_spawn_egg",
            new SpawnEggItem(ModEntities.BLUB, 0x9FE8E4, 0xFFB0D8, new Item.Settings()));
    public static final Item ICE_ZOMBIE_SPAWN_EGG = register("ice_zombie_spawn_egg",
            new SpawnEggItem(ModEntities.ICE_ZOMBIE, 0x98D0EE, 0xFF7AD9, new Item.Settings()));

    public static final ItemGroup SIFT_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(SiftMod.ID, "sift"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(RIFT_SHARD))
                    .displayName(Text.translatable("itemGroup.sift"))
                    .entries((context, entries) -> {
                        entries.add(RIFT_SHARD);
                        entries.add(SOUL_WISP);
                        entries.add(SOULSTONE);
                        entries.add(WISP_BULB);
                        entries.add(SOULGLASS);
                        entries.add(RIMEGLASS);
                        entries.add(WAIL_THORN);
                        entries.add(HUSH_SILT);
                        entries.add(BLUB_SPAWN_EGG);
                        entries.add(ICE_ZOMBIE_SPAWN_EGG);
                    })
                    .build());

    private static Item blockItem(String name, Block block) {
        return register(name, new BlockItem(block, new Item.Settings()));
    }

    private static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(SiftMod.ID, name), item);
    }

    public static void init() {}
}
