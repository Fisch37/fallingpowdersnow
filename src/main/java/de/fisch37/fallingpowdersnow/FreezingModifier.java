package de.fisch37.fallingpowdersnow;

public interface FreezingModifier {
    /**
     * Schedules that (when the entity next ticks), it should be considered in powder snow. Resets each tick.
     * Should this change be applied over multiple ticks, this method must be called every single tick.
     * @param freeze The value to set the {@link net.minecraft.entity.Entity#inPowderSnow} to.
     */
    void fallingpowdersnow$scheduleFreeze(boolean freeze);
}
