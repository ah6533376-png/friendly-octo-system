package com.siftmod;

import net.fabricmc.api.ModInitializer;

public class SiftMod implements ModInitializer {
    public static final String ID = "sift";

    @Override
    public void onInitialize() {
        ModBlocks.init();
        ModEntities.init();
        ModItems.init();
    }
}
