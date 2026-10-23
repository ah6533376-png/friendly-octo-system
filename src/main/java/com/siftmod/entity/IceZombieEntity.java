package com.siftmod.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

/**
 * The ice zombie: tougher than a zombie, does not burn in the sift sun, hunts blubs,
 * and every hit stacks frostbite on its victim.
 */
public class IceZombieEntity extends ZombieEntity {
    public IceZombieEntity(EntityType<? extends IceZombieEntity> type, World world) {
        super(type, world);
    }

    public static DefaultAttributeContainer.Builder createIceZombieAttributes() {
        return ZombieEntity.createZombieAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 24.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.21);
    }

    // the sift sun is gentle: ice zombies do not burn in daylight
    protected boolean burnsInDaylight() {
        return false;
    }

    @Override
    protected void initCustomGoals() {
        super.initCustomGoals();
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, BlubEntity.class, false));
    }

    @Override
    public boolean tryAttack(Entity target) {
        boolean hit = super.tryAttack(target);
        if (hit && target instanceof LivingEntity living) {
            living.setFrozenTicks(Math.min(living.getFrozenTicks() + 70, 280));
            living.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 80, 0), this);
        }
        return hit;
    }

    @Override
    public void onDeath(DamageSource source) {
        super.onDeath(source);
        if (this.getWorld() instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ParticleTypes.SNOWFLAKE,
                    this.getX(), this.getBodyY(0.5), this.getZ(), 24, 0.4, 0.6, 0.4, 0.05);
        }
    }

    @Override
    public boolean canSpawn(WorldAccess world, SpawnReason spawnReason) {
        BlockPos below = this.getBlockPos().down();
        return world.getBlockState(below).isSideSolidFullSquare(world, below, Direction.UP);
    }
}
