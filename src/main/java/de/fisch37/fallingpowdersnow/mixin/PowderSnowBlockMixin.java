package de.fisch37.fallingpowdersnow.mixin;

import de.fisch37.fallingpowdersnow.PowderSnowBlockExtras;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PowderSnowBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Debug(export = true)
@Mixin(PowderSnowBlock.class)
public abstract class PowderSnowBlockMixin extends Block {
    public PowderSnowBlockMixin(Settings settings) {
        super(settings);
        throw new IllegalStateException("Mixin constructor called");
    }

    @Override @Unique
    protected void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        PowderSnowBlockExtras.onBlockAdded((PowderSnowBlock) ((Object)this), world, pos);
        super.onBlockAdded(state, world, pos, oldState, notify);
    }

    @Override @Unique
    protected BlockState getStateForNeighborUpdate(
            BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos,
            Direction direction, BlockPos neighborPos, BlockState neighborState, Random random
    ) {
        // I feel like there should be some sort of comment for this, but tbh I built this code months ago and I have no clue what's going on.
        PowderSnowBlockExtras.getStateForNeighborUpdate((PowderSnowBlock) ((Object)this), tickView, pos);
        return super.getStateForNeighborUpdate(
                state, world, tickView, pos,
                direction, neighborPos, neighborState, random
        );
    }

    @Override @Unique
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        PowderSnowBlockExtras.scheduledTick((PowderSnowBlock) ((Object) this), state, world, pos);
    }
}
