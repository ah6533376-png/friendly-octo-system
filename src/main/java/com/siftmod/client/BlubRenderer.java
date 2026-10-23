package com.siftmod.client;

import com.siftmod.SiftMod;
import com.siftmod.entity.BlubEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class BlubRenderer extends MobEntityRenderer<BlubEntity, BlubModel> {
    private static final Identifier TEXTURE = new Identifier(SiftMod.ID, "textures/entity/blub.png");

    public BlubRenderer(EntityRendererFactory.Context context) {
        super(context, new BlubModel(context.getPart(BlubModel.LAYER)), 0.4f);
    }

    @Override
    public Identifier getTexture(BlubEntity entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(BlubEntity entity, MatrixStack matrices, float amount) {
        matrices.scale(1.5f, 1.5f, 1.5f);
    }
}
