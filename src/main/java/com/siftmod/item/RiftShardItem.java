package com.siftmod.item;

import com.siftmod.ModBlocks;
import com.siftmod.SiftDimensions;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/** Right click the ground in the overworld or the sift to tear open a 2 block tall rift. */
public class RiftShardItem extends Item {
    public RiftShardItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        boolean allowed = world.getRegistryKey() == World.OVERWORLD || world.getRegistryKey() == SiftDimensions.SIFT_WORLD;
        if (!allowed) {
            if (player != null && !world.isClient) {
                player.sendMessage(Text.translatable("message.sift.rift_wrong_dimension"), true);
            }
            return ActionResult.FAIL;
        }

        BlockPos lower = context.getBlockPos().offset(context.getSide());
        BlockPos upper = lower.up();
        if (!world.getBlockState(lower).isReplaceable() || !world.getBlockState(upper).isReplaceable()) {
            return ActionResult.FAIL;
        }
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }

        BlockState rift = ModBlocks.RIFT_PORTAL.getDefaultState();
        world.setBlockState(lower, rift);
        world.setBlockState(upper, rift);
        world.playSound(null, lower, SoundEvents.BLOCK_END_PORTAL_SPAWN, SoundCategory.BLOCKS, 0.6f, 1.6f);
        if (player == null || !player.getAbilities().creativeMode) {
            context.getStack().decrement(1);
        }
        return ActionResult.SUCCESS;
    }
}
