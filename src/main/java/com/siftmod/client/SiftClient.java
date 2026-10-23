package com.siftmod.client;

import com.siftmod.ModBlocks;
import com.siftmod.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class SiftClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(BlubModel.LAYER, BlubModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.BLUB, BlubRenderer::new);
        EntityRendererRegistry.register(ModEntities.ICE_ZOMBIE, IceZombieRenderer::new);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(),
                ModBlocks.SOULGLASS, ModBlocks.RIMEGLASS, ModBlocks.RIFT_PORTAL);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), ModBlocks.WAIL_THORN);
    }
}
