package com.siftmod;

import net.minecraft.block.BlockState;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;

public final class SiftDimensions {
    private SiftDimensions() {}

    public static final RegistryKey<World> SIFT_WORLD =
            RegistryKey.of(RegistryKeys.WORLD, new Identifier(SiftMod.ID, "the_sift"));

    /** Sends the player overworld <-> sift at the same x/z (1:1, no scaling) and plants a return rift 2 blocks away. */
    public static void travel(ServerPlayerEntity player) {
        MinecraftServer server = player.getServer();
        if (server == null) {
            return;
        }
        ServerWorld from = player.getServerWorld();
        boolean toSift = from.getRegistryKey() != SIFT_WORLD;
        ServerWorld to = toSift ? server.getWorld(SIFT_WORLD) : server.getOverworld();
        if (to == null) {
            return;
        }

        int x = player.getBlockX();
        int z = player.getBlockZ();
        int y = surfaceY(to, x, z);

        // the return rift stands on a small soulstone pad, 2 blocks east of the arrival point
        BlockPos rift = new BlockPos(x + 2, surfaceY(to, x + 2, z), z);
        BlockState riftState = ModBlocks.RIFT_PORTAL.getDefaultState();
        to.setBlockState(rift.down(), ModBlocks.SOULSTONE.getDefaultState());
        to.setBlockState(rift, riftState);
        to.setBlockState(rift.up(), riftState);

        player.teleport(to, x + 0.5, y, z + 0.5, player.getYaw(), player.getPitch());
        player.resetPortalCooldown();
        to.playSound(null, x + 0.5, y, z + 0.5, SoundEvents.BLOCK_AMETHYST_CLUSTER_BREAK, SoundCategory.PLAYERS, 1.0f, 0.6f);
    }

    private static int surfaceY(ServerWorld world, int x, int z) {
        world.getChunk(x >> 4, z >> 4); // force the chunk to load
        return world.getTopY(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, x, z);
    }
}
