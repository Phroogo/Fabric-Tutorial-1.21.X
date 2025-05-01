package net.adam.tutorialmod.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import com.mojang.serialization.MapCodec;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;


public class SlimeyEffect extends StatusEffect {

    protected SlimeyEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {

        entity.fallDistance = 0.0f;

        if(entity.horizontalCollision) {
            Vec3d initialVec = entity.getVelocity();
            Vec3d climbVec = new Vec3d(initialVec.x, 0.20, initialVec.z);
            entity.setVelocity(climbVec.multiply(0.960));
            return true;
        }
        Vec3d velocity = entity.getVelocity();

        return super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
