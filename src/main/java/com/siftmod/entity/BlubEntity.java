package com.siftmod.entity;

import com.siftmod.ModBlocks;
import com.siftmod.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.entity.SpawnReason;

/**
 * The blub: a shy, glowing, soul-fed blob. Follows anyone holding a soul wisp or wisp bulb,
 * runs from ice zombies, and repays a soul wisp with a short regeneration + glow gift.
 */
public class BlubEntity extends PathAwareEntity {
    public BlubEntity(EntityType<? extends BlubEntity> type, World world) {
        super(type, world);
    }

    public static DefaultAttributeContainer.Builder createBlubAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 12.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.6));
        this.goalSelector.add(2, new FleeEntityGoal<>(this, IceZombieEntity.class, 8.0f, 1.2, 1.5));
        this.goalSelector.add(3, new TemptGoal(this, 1.1,
                Ingredient.ofItems(ModItems.SOUL_WISP, ModBlocks.WISP_BULB.asItem()), false));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 0.8));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.add(6, new LookAroundGoal(this));
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        if (stack.isOf(ModItems.SOUL_WISP)) {
            if (!this.getWorld().isClient) {
                if (!player.getAbilities().creativeMode) {
                    stack.decrement(1);
                }
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 120, 0));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 200, 0));
                ((ServerWorld) this.getWorld()).spawnParticles(ParticleTypes.SOUL,
                        this.getX(), this.getBodyY(0.7), this.getZ(), 12, 0.3, 0.3, 0.3, 0.02);
                this.playSound(SoundEvents.ENTITY_ALLAY_ITEM_GIVEN, 1.0f, 1.4f);
            }
            return ActionResult.success(this.getWorld().isClient);
        }
        return super.interactMob(player, hand);
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        if (this.getWorld().isClient && this.random.nextInt(12) == 0) {
            this.getWorld().addParticle(ParticleTypes.SOUL,
                    this.getParticleX(0.5), this.getRandomBodyY(), this.getParticleZ(0.5), 0.0, 0.02, 0.0);
        }
    }

    @Override
    public boolean canSpawn(WorldAccess world, SpawnReason spawnReason) {
        BlockPos below = this.getBlockPos().down();
        return world.getBlockState(below).isSideSolidFullSquare(world, below, Direction.UP);
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_ALLAY_AMBIENT_WITHOUT_ITEM;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_ALLAY_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ALLAY_DEATH;
    }
}
