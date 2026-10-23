package com.siftmod.client;

import com.siftmod.SiftMod;
import com.siftmod.entity.BlubEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class BlubModel extends EntityModel<BlubEntity> {
    public static final EntityModelLayer LAYER = new EntityModelLayer(new Identifier(SiftMod.ID, "blub"), "main");

    private final ModelPart root;
    private final ModelPart finLeft;
    private final ModelPart finRight;
    private final ModelPart antenna;

    public BlubModel(ModelPart root) {
        this.root = root;
        this.finLeft = root.getChild("fin_left");
        this.finRight = root.getChild("fin_right");
        this.antenna = root.getChild("antenna");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData data = new ModelData();
        ModelPartData rootData = data.getRoot();
        float quarterTurn = (float) (Math.PI / 2.0);
        rootData.addChild("body",
                ModelPartBuilder.create().uv(0, 0).cuboid(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f),
                ModelTransform.pivot(0.0f, 24.0f, 0.0f));
        rootData.addChild("fin_left",
                ModelPartBuilder.create().uv(0, 16).cuboid(-2.0f, -2.0f, -0.5f, 4.0f, 4.0f, 1.0f),
                ModelTransform.of(4.5f, 20.0f, 0.0f, 0.0f, quarterTurn, 0.0f));
        rootData.addChild("fin_right",
                ModelPartBuilder.create().uv(10, 16).cuboid(-2.0f, -2.0f, -0.5f, 4.0f, 4.0f, 1.0f),
                ModelTransform.of(-4.5f, 20.0f, 0.0f, 0.0f, quarterTurn, 0.0f));
        rootData.addChild("antenna",
                ModelPartBuilder.create().uv(20, 16).cuboid(-1.0f, -4.0f, -1.0f, 2.0f, 4.0f, 2.0f),
                ModelTransform.pivot(0.0f, 16.0f, 0.0f));
        return TexturedModelData.of(data, 32, 32);
    }

    @Override
    public void setAngles(BlubEntity entity, float limbAngle, float limbDistance, float animationProgress,
                          float headYaw, float headPitch) {
        float t = animationProgress * 0.25f;
        this.finLeft.roll = MathHelper.sin(t) * 0.5f;
        this.finRight.roll = -MathHelper.sin(t) * 0.5f;
        this.antenna.pitch = MathHelper.sin(t * 0.7f) * 0.25f;
        this.antenna.roll = MathHelper.cos(t * 0.5f) * 0.2f;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay,
                       float red, float green, float blue, float alpha) {
        this.root.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}
