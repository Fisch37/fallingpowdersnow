package de.fisch37.fallingpowdersnow.mixin;

import de.fisch37.fallingpowdersnow.FreezingModifier;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FallingBlockEntity.class)
public abstract class FallingBlockEntityMixin {
    @Shadow
    private BlockState block;

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/FallingBlockEntity;tickPortalTeleportation()V"))
    private void tickInjectTickPortalTeleportation(CallbackInfo ci) {
        if (block.getBlock() != Blocks.POWDER_SNOW)
            return;
        FallingBlockEntity instance = (FallingBlockEntity)((Object)this);
        var collidedEntities = instance.getWorld().getOtherEntities(instance, instance.getBoundingBox(),
                entity -> entity instanceof LivingEntity);
        for (var entity : collidedEntities) {
            ((FreezingModifier)entity).fallingpowdersnow$scheduleFreeze(true);
        }
    }
}
