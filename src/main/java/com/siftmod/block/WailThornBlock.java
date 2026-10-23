package com.siftmod.block;

import com.siftmod.entity.BlubEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

/** Hazard: a thorny bramble that cuts anything that moves through it and wails when it does. Blubs are immune. */
public class WailThornBlock extends PlantBlock {
    private static final VoxelShape SHAPE = Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 14.0, 14.0);

    public WailThornBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!(entity instanceof LivingEntity living) || entity instanceof BlubEntity) {
            return;
        }
        entity.slowMovement(state, new Vec3d(0.8, 0.75, 0.8));
        if (!world.isClient) {
            double dx = Math.abs(entity.getX() - entity.lastRenderX);
            double dz = Math.abs(entity.getZ() - entity.lastRenderZ);
            if (dx >= 0.003 || dz >= 0.003) {
                living.damage(world.getDamageSources().sweetBerryBush(), 1.0f);
                world.playSound(null, pos, SoundEvents.PARTICLE_SOUL_ESCAPE, SoundCategory.BLOCKS, 0.8f, 0.7f);
            }
        }
    }
}
