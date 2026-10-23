package com.siftmod.client;

import com.siftmod.SiftMod;
import com.siftmod.entity.IceZombieEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class IceZombieRenderer extends BipedEntityRenderer<IceZombieEntity, ZombieEntityModel<IceZombieEntity>> {
    private static final Identifier TEXTURE = new Identifier(SiftMod.ID, "textures/entity/ice_zombie.png");

    public IceZombieRenderer(EntityRendererFactory.Context context) {
        super(context, new ZombieEntityModel<>(context.getPart(EntityModelLayers.ZOMBIE)), 0.5f);
        this.addFeature(new ArmorFeatureRenderer<>(this,
                new ZombieEntityModel<>(context.getPart(EntityModelLayers.ZOMBIE_INNER_ARMOR)),
                new ZombieEntityModel<>(context.getPart(EntityModelLayers.ZOMBIE_OUTER_ARMOR)),
                context.getModelManager()));
    }

    @Override
    public Identifier getTexture(IceZombieEntity entity) {
        return TEXTURE;
    }
}
