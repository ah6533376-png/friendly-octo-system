package com.siftmod;

import com.siftmod.entity.BlubEntity;
import com.siftmod.entity.IceZombieEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class ModEntities {
    private ModEntities() {}

    public static final EntityType<BlubEntity> BLUB = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(SiftMod.ID, "blub"),
            EntityType.Builder.create(BlubEntity::new, SpawnGroup.CREATURE)
                    .setDimensions(0.75f, 0.75f).maxTrackingRange(8).build("blub"));

    public static final EntityType<IceZombieEntity> ICE_ZOMBIE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(SiftMod.ID, "ice_zombie"),
            EntityType.Builder.create(IceZombieEntity::new, SpawnGroup.MONSTER)
                    .setDimensions(0.6f, 1.95f).maxTrackingRange(8).build("ice_zombie"));

    public static void init() {
        FabricDefaultAttributeRegistry.register(BLUB, BlubEntity.createBlubAttributes());
        FabricDefaultAttributeRegistry.register(ICE_ZOMBIE, IceZombieEntity.createIceZombieAttributes());
    }
}
