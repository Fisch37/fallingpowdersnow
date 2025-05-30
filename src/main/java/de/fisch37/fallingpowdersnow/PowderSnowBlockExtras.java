package de.fisch37.fallingpowdersnow;

import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.PowderSnowBlock;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.tick.ScheduledTickView;

public abstract class PowderSnowBlockExtras {
    private static final int FALL_DELAY = 2;

    public static void onBlockAdded(
            PowderSnowBlock instance,
            World world, BlockPos pos
    ) {
        world.scheduleBlockTick(pos, instance, FALL_DELAY);
    }

    public static void getStateForNeighborUpdate(
            PowderSnowBlock instance,
            ScheduledTickView tickView,
            BlockPos pos
    ) {
        tickView.scheduleBlockTick(pos, instance, FALL_DELAY);
    }

    public static void scheduledTick(PowderSnowBlock instance, BlockState state, ServerWorld world, BlockPos pos) {
        if (FallingBlock.canFallThrough(world.getBlockState(pos.down())) && pos.getY() >= world.getBottomY()) {
            FallingBlockEntity.spawnFromBlock(world, pos, state).dropItem = false;
        }
    }
}
