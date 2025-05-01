package net.adam.tutorialmod.entity.custom;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Dynamic;
import net.adam.tutorialmod.entity.ModEntities;
import net.adam.tutorialmod.item.ModItems;
import net.adam.tutorialmod.sound.ModSounds;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PhrogEntity extends AnimalEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState longJumpAnimationState = new AnimationState();

    private int idleAnimationTimeout = 0;

    public PhrogEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));

        this.goalSelector.add(1, new AnimalMateGoal(this, 1.15D));
        this.goalSelector.add(2, new TemptGoal(this, 1.25D, Ingredient.ofItems(ModItems.CANCEROUS_REACTOR), false));

        this.goalSelector.add(3, new FollowParentGoal(this, 1.1D));

        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 4.0F));
        this.goalSelector.add(6, new LookAroundGoal(this));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 18)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.1)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 20);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 20;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    public void onTrackedDataSet(TrackedData<?> data) {
        if (POSE.equals(data)) {
            EntityPose entityPose = this.getPose();
            if (entityPose == EntityPose.LONG_JUMPING) {
                this.longJumpAnimationState.start(this.age);
            } else {
                this.longJumpAnimationState.stop();
            }
        }

        super.onTrackedDataSet(data);
    }

    protected void updateLimbs(float posDelta) {
        float f;
        if (this.longJumpAnimationState.isRunning()) {
            f = 0.0F;
        } else {
            f = Math.min(posDelta * 25.0F, 1.0F);
        }

        this.limbAnimator.updateLimbs(f, 0.4F);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getWorld().isClient()) {
            this.setupAnimationStates();
        }
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(ModItems.CANCEROUS_REACTOR);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.PHROG.create(world);
    }

//    @Override
//    protected @Nullable SoundEvent getAmbientSound() {
//        return ModSounds.MANTIS_AMBIENT_SOUND;
//    }
//
//    @Override
//    protected @Nullable SoundEvent getHurtSound(DamageSource source) {
//        return SoundEvents.ENTITY_ALLAY_HURT;
//    }
//
//    @Override
//    protected @Nullable SoundEvent getDeathSound() {
//        return ModSounds.MANTIS_DEATH_SOUND;
//    }
}