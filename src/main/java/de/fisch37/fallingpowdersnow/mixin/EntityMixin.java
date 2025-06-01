package de.fisch37.fallingpowdersnow.mixin;

import de.fisch37.fallingpowdersnow.FreezingModifier;
import net.minecraft.entity.Entity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin implements FreezingModifier {
    @Unique
    private boolean freezeScheduleValue;
    @Unique
    private boolean freezeIsScheduled;

    @Override
    public void fallingpowdersnow$scheduleFreeze(boolean freeze) {
        freezeScheduleValue = freeze;
        freezeIsScheduled = true;
    }

    @Inject(method = "baseTick",
            at = @At(value = "FIELD", opcode = Opcodes.PUTFIELD,
                    target = "Lnet/minecraft/entity/Entity;inPowderSnow:Z",
                    shift = At.Shift.AFTER
            ))
    private void inPowderSnowUpdateModify(CallbackInfo ci) {
        if (freezeIsScheduled) {
            freezeIsScheduled = false;
            ((Entity)((Object)this)).inPowderSnow = freezeScheduleValue;
        }
    }
}
