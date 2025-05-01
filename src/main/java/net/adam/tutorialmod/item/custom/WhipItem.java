package net.adam.tutorialmod.item.custom;

import net.adam.tutorialmod.sound.ModSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;


import java.util.List;

public class WhipItem extends Item {
    public WhipItem(Settings settings) {
        super(settings);
    }

    public static void WhipItem(){

    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {

        ItemStack itemStack = player.getStackInHand(hand);

        // Play the whip sound
        world.playSound(null, player.getX(), player.getY(), player.getZ(),
                ModSounds.WHIP_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);
        player.swingHand(hand);

        // Add particle effects for the whip trail
        if (world.isClient) {
            // Get the player's eye position and direction
            Vec3d eyePosition = player.getEyePos();
            Vec3d lookDirection = player.getRotationVec(1.0F);

            // Create a trail of particles in front of the player
            double range = 3.0;
            for (double i = 0.5; i <= range; i += 0.2) {
                Vec3d particlePos = eyePosition.add(lookDirection.multiply(i));
                world.addParticle(
                    ParticleTypes.CRIT,
                    particlePos.x, particlePos.y, particlePos.z,
                    0.0, 0.0, 0.0
                );
            }
        }

        if (!world.isClient) {
            // Define the range
            double range = 3.0;

            // Get the player's eye position and direction
            Vec3d eyePosition = player.getEyePos();
            Vec3d lookDirection = player.getRotationVec(1.0F);

            // Calculate the end position of the ray
            Vec3d endPosition = eyePosition.add(lookDirection.multiply(range));

            // Perform a raycast to detect blocks
            HitResult blockHitResult = world.raycast(new RaycastContext(
                    eyePosition, endPosition, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, player));

            // If a block is hit, adjust the end position
            if (blockHitResult.getType() == HitResult.Type.BLOCK) {
                endPosition = blockHitResult.getPos();
            }

            // Create a bounding box from the player's position to the end position
            Box box = new Box(eyePosition, endPosition).expand(1.0);

            // Find entities within the bounding box
            List<Entity> entities = world.getOtherEntities(player, box, entity -> entity instanceof LivingEntity);

            // Iterate over the entities and apply effects
            for (Entity targetEntity : entities) {
                if (targetEntity instanceof LivingEntity) {
                    LivingEntity livingEntity = (LivingEntity) targetEntity;

                    // Create a damage source using the player
                    DamageSource whipDamageSource = player.getDamageSources().playerAttack(player);

                    // Apply damage to the entity
                    livingEntity.damage(whipDamageSource, 4.0F);

                    // Apply knockback effect
                    double knockbackStrength = 0.5; // Adjust the strength as needed
                    livingEntity.addVelocity(-Math.sin(player.getYaw() * Math.PI / 180.0) * knockbackStrength, 0.1, Math.cos(player.getYaw() * Math.PI / 180.0) * knockbackStrength);

                    // Apply a status effect
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 30, 0));

                    // Damage the whip item
                    EquipmentSlot slot = hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
                    itemStack.damage(1, player, slot);
                }
            }
        }

        player.getItemCooldownManager().set(this, 20);

        return TypedActionResult.success(itemStack, world.isClient());
    }


}
