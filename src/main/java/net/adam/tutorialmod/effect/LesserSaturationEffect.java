package net.adam.tutorialmod.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;

public class LesserSaturationEffect extends StatusEffect {

    protected LesserSaturationEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {

        if (!entity.getWorld().isClient && entity instanceof PlayerEntity playerEntity) {
            if (entity.age % 200 == 0) {
                playerEntity.getHungerManager().add(amplifier + 1, 0.1F);
            }
        }
        return super.applyUpdateEffect(entity, amplifier);
    }


    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
