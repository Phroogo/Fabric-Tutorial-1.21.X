package net.adam.tutorialmod.entity.custom;

import net.adam.tutorialmod.entity.ModEntities;
import net.adam.tutorialmod.item.ModItems;
import net.adam.tutorialmod.item.custom.WhipItem;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.entity.passive.CamelEntity.LAST_POSE_TICK;

public class BlackEntity extends TameableEntity {

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState punchAnimationState = new AnimationState();

    public final AnimationState sittingTransitionAnimationState = new AnimationState();
    public final AnimationState sittingAnimationState = new AnimationState();
    public final AnimationState standingTransitionAnimationState = new AnimationState();

    public static final TrackedData<Long> LAST_POSE_TICK =
            DataTracker.registerData(BlackEntity.class, TrackedDataHandlerRegistry.LONG);

    public BlackEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(0, new TameableEntity.TameableEscapeDangerGoal(1.5F, DamageTypeTags.PANIC_ENVIRONMENTAL_CAUSES));

        this.goalSelector.add(1, new SitGoal(this));

        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.1F, true));
        this.goalSelector.add(3, new AnimalMateGoal(this, 1.15D));
        this.goalSelector.add(4, new TemptGoal(this, 1.25D, Ingredient.ofItems(Items.MELON_SLICE), false));

        this.goalSelector.add(5, new FollowOwnerGoal(this, 1.1D, 10F, 2F));
        this.goalSelector.add(5, new FollowParentGoal(this, 1.1D));

        this.goalSelector.add(6, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 4.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));

        this.targetSelector.add(1, new TrackOwnerAttackerGoal(this));
        this.targetSelector.add(2, new AttackWithOwnerGoal(this));
        this.targetSelector.add(3, (new RevengeGoal(this, new Class[0])).setGroupRevenge(new Class[0]));
    }

    public static DefaultAttributeContainer.Builder createBlackAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.MELON_SLICE);
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.BLACK.create(world);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 20;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
        if (this.shouldUpdateSittingAnimations()) {
            this.standingTransitionAnimationState.stop();
            if (this.shouldPlaySittingTransitionAnimation()) {
                this.sittingTransitionAnimationState.startIfNotRunning(this.age);
                this.sittingAnimationState.stop();
            } else {
                this.sittingTransitionAnimationState.stop();
                this.sittingAnimationState.startIfNotRunning(this.age);
            }
        } else {
            this.sittingTransitionAnimationState.stop();
            this.sittingAnimationState.stop();
            this.standingTransitionAnimationState.setRunning(this.isChangingPose() && this.getLastPoseTickDelta() >= 0L, this.age);
        }
        if(this.isAttacking()) {
            this.punchAnimationState.startIfNotRunning(this.age);
        }
        else {
            this.punchAnimationState.stop();
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getWorld().isClient()) {
            this.setupAnimationStates();
        }
    }

    private boolean shouldPlaySittingTransitionAnimation() {
        return this.isSitting() && this.getLastPoseTickDelta() < 40L && this.getLastPoseTickDelta() >= 0L;
    }

    public boolean shouldUpdateSittingAnimations() {
        return this.getLastPoseTickDelta() < 0L != this.isSitting();
    }

    public long getLastPoseTickDelta() {
        return this.getWorld().getTime() - Math.abs(this.dataTracker.get(LAST_POSE_TICK));
    }

    public boolean isChangingPose() {
        long l = this.getLastPoseTickDelta();
        return l < (long)(this.isSitting() ? 40 : 52);
    }

    public void setLastPoseTick(long lastPoseTick) {
        this.dataTracker.set(LAST_POSE_TICK, lastPoseTick);
    }

    private void initLastPoseTick(long time) {
        this.setLastPoseTick(Math.max(0L, time - 52L - 1L));
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(LAST_POSE_TICK, 0L);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putLong("LastPoseTick", this.dataTracker.get(LAST_POSE_TICK));
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        long l = nbt.getLong("LastPoseTick");
        if (l < 0L) {
            this.setPose(EntityPose.SITTING);
        }
        this.setLastPoseTick(l);
    }
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        this.initLastPoseTick(world.toServerWorld().getTime());
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    public void toggleSitting() {
        if (this.isSitting()) {
            startStanding();
        } else {
            startSitting();
        }
    }

    public void startSitting() {
        if (this.isSitting()) {
            return;
        }
        this.playSound(SoundEvents.ENTITY_CAMEL_SIT);
        this.setPose(EntityPose.SITTING);
        this.emitGameEvent(GameEvent.ENTITY_ACTION);
        this.setLastPoseTick(-this.getWorld().getTime());

        setInSittingPose(true);
        setSitting(true);
    }

    public void startStanding() {
        if (!this.isSitting()) {
            return;
        }
        this.playSound(SoundEvents.ENTITY_CAMEL_STAND);
        this.setPose(EntityPose.STANDING);
        this.emitGameEvent(GameEvent.ENTITY_ACTION);
        this.setLastPoseTick(this.getWorld().getTime());

        setInSittingPose(false);
        setSitting(false);
    }

    public boolean isSitting() {
        return this.dataTracker.get(LAST_POSE_TICK) < 0L;
    }


    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getStackInHand(hand);
        Item item = itemstack.getItem();

        Item itemForTaming = Items.COOKED_CHICKEN;

        if(itemstack.getItem() == ModItems.WHIP && !isTamed()) {
            super.setOwner(player);
            this.navigation.recalculatePath();
            this.setTarget(null);
            this.getWorld().sendEntityStatus(this, (byte) 7);
            
            return ActionResult.SUCCESS;
        }

        if(item == itemForTaming && !isTamed()) {
            if(this.getWorld().isClient()) {
                return ActionResult.CONSUME;
            } else {
                if (!player.getAbilities().creativeMode) {
                    itemstack.decrement(1);
                }

                super.setOwner(player);
                this.navigation.recalculatePath();
                this.setTarget(null);
                this.getWorld().sendEntityStatus(this, (byte)7);

                return ActionResult.SUCCESS;
            }
        }

        if(isTamed() && hand == Hand.MAIN_HAND && item != itemForTaming && !isBreedingItem(itemstack)) {
            toggleSitting();
            return ActionResult.SUCCESS;
        }

        if (this.isTamed() && this.isBreedingItem(itemstack) && this.getHealth() < this.getMaxHealth()) {
            itemstack.decrementUnlessCreative(1, player);
            FoodComponent foodComponent = (FoodComponent)itemstack.get(DataComponentTypes.FOOD);
            float f = foodComponent != null ? (float)foodComponent.nutrition() : 1.0F;
            this.heal(2.0F * f);
            return ActionResult.success(this.getWorld().isClient());
        }

        return super.interactMob(player, hand);
    }
}
