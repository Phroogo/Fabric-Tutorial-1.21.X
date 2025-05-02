package net.adam.tutorialmod;

import net.adam.tutorialmod.block.ModBlocks;
import net.adam.tutorialmod.component.ModDataComponentTypes;
import net.adam.tutorialmod.effect.ModEffects;
import net.adam.tutorialmod.enchantment.ModEnchantmentEffects;
import net.adam.tutorialmod.entity.ModEntities;
import net.adam.tutorialmod.entity.custom.BlackEntity;
import net.adam.tutorialmod.entity.custom.MantisEntity;
import net.adam.tutorialmod.entity.custom.PhrogEntity;
import net.adam.tutorialmod.item.ModItemGroups;
import net.adam.tutorialmod.item.ModItems;
import net.adam.tutorialmod.potion.ModPotions;
import net.adam.tutorialmod.sound.ModSounds;
import net.adam.tutorialmod.util.HammerUsageEvent;
import net.adam.tutorialmod.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.item.*;
import net.minecraft.potion.Potions;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;


public class 	TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlock();

		ModDataComponentTypes.registerDataComponentTypes();
		ModSounds.registerSounds();
		ModEffects.registerEffects();

		ModPotions.registerPotions();

		ModEnchantmentEffects.registerEnchantmentEffect();

		ModWorldGeneration.generateModWorldGen();

		ModEntities.registerModEntities();

		FuelRegistry.INSTANCE.add(ModItems.STARLIGHT_ASHES, 600);

		PlayerBlockBreakEvents.BEFORE.register(new HammerUsageEvent());

		AttackEntityCallback.EVENT.register((player, world, hand, entity, entityHitResult) -> {

			if(entity instanceof SheepEntity sheepEntity && !world.isClient()) {


				ArrayList<Item> swords = new ArrayList<Item>();
				swords.add(Items.WOODEN_SWORD);
				swords.add(Items.STONE_SWORD);
				swords.add(Items.IRON_SWORD);
				swords.add(Items.GOLDEN_SWORD);
				swords.add(Items.DIAMOND_SWORD);
				swords.add(Items.NETHERITE_SWORD);
				swords.add(ModItems.PINK_GARNET_SWORD);

				ArrayList<Item> axes = new ArrayList<Item>();
				axes.add(Items.WOODEN_AXE);
				axes.add(Items.STONE_AXE);
				axes.add(Items.IRON_AXE);
				axes.add(Items.GOLDEN_AXE);
				axes.add(Items.DIAMOND_AXE);
				axes.add(Items.NETHERITE_AXE);
				axes.add(ModItems.PINK_GARNET_AXE);

				if (swords.contains(player.getMainHandStack().getItem())){
					player.sendMessage(Text.literal(player.getNameForScoreboard() + " just hit a sheep with A SWORD!"));
					player.getMainHandStack().setDamage(player.getMainHandStack().getDamage() + 99);
					player.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, 0, false, false));
					sheepEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 3, false, false));
				}
				else if (axes.contains(player.getMainHandStack().getItem())){
					player.sendMessage(Text.literal(player.getNameForScoreboard() + " just hit a sheep with AN AXE!"));
					player.getMainHandStack().setDamage(player.getMainHandStack().getDamage() + 98);
					player.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, 0, false, false));
					sheepEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 4, false, false));
				}
				else if(player.getMainHandStack().getItem() == Items.END_ROD || player.getMainHandStack().getItem() == Items.STICK) {
					player.sendMessage(Text.literal(player.getNameForScoreboard() + " just hit a sheep with A POINTY THINGY!"));
					player.getMainHandStack().decrement(1);
					player.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, 0, false, false));
					sheepEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 2, false, false));
				}
				else if (player.getMainHandStack().isEmpty()) {
					player.sendMessage(Text.literal(player.getNameForScoreboard() + " just hit a sheep with THEIR BARE HANDS!"));
					player.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, 0, false, false));
					sheepEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 0, false, false));
				}
				else {
					player.sendMessage(Text.literal(player.getNameForScoreboard() + " just hit a sheep with A MYSTERIOUS ITEM!"));
					player.getMainHandStack().decrement(1);
					player.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, 0, false, false));
					sheepEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 1, false, false));
				}

				return ActionResult.PASS;
			}

			if(entity instanceof WolfEntity wolfEntity && !world.isClient()) {


				ArrayList<Item> swords = new ArrayList<Item>();
				swords.add(Items.WOODEN_SWORD);
				swords.add(Items.STONE_SWORD);
				swords.add(Items.IRON_SWORD);
				swords.add(Items.GOLDEN_SWORD);
				swords.add(Items.DIAMOND_SWORD);
				swords.add(Items.NETHERITE_SWORD);
				swords.add(ModItems.PINK_GARNET_SWORD);

				ArrayList<Item> axes = new ArrayList<Item>();
				axes.add(Items.WOODEN_AXE);
				axes.add(Items.STONE_AXE);
				axes.add(Items.IRON_AXE);
				axes.add(Items.GOLDEN_AXE);
				axes.add(Items.DIAMOND_AXE);
				axes.add(Items.NETHERITE_AXE);
				axes.add(ModItems.PINK_GARNET_AXE);

				if (swords.contains(player.getMainHandStack().getItem())){
					player.sendMessage(Text.literal(player.getNameForScoreboard() + " just hit a puppy with A SWORD and got what they deserved!"));
					player.getMainHandStack().decrement(1);
					player.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, 1, false, false));
					wolfEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 8, false, false));
				}
				else if (axes.contains(player.getMainHandStack().getItem())){
					player.sendMessage(Text.literal(player.getNameForScoreboard() + " just hit a puppy with AN AXE and got what they deserved!"));
					player.getMainHandStack().decrement(1);
					player.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, 1, false, false));
					wolfEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 9, false, false));
				}
				else if(player.getMainHandStack().getItem() == Items.END_ROD || player.getMainHandStack().getItem() == Items.STICK) {
					player.sendMessage(Text.literal(player.getNameForScoreboard() + " just hit a puppy with A POINTY THINGY and got what they deserved!"));
					player.getMainHandStack().decrement(64);
					player.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, 1, false, false));
					wolfEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 7, false, false));
				}
				else if (player.getMainHandStack().isEmpty()) {
					player.sendMessage(Text.literal(player.getNameForScoreboard() + " just hit a puppy with THEIR BARE HANDS and got what they deserved!"));
					player.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, 1, false, false));
					wolfEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 5, false, false));
				}
				else {
					player.sendMessage(Text.literal(player.getNameForScoreboard() + " just hit a puppy with A MYSTERIOUS ITEM and got what they deserved!"));
					player.getMainHandStack().decrement(64);
					player.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, 1, false, false));
					wolfEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 6, false, false));
				}

				return ActionResult.PASS;
			}

			return ActionResult.PASS;
		});



		FabricBrewingRecipeRegistryBuilder.BUILD.register((builder -> {
			builder.registerPotionRecipe(Potions.AWKWARD, Items.SLIME_BALL, ModPotions.SLIMEY_POTION);
		}));

		CompostingChanceRegistry.INSTANCE.add(ModItems.CAULIFLOWER, 0.5f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.CAULIFLOWER_SEEDS, 0.25f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.HONEY_BERRIES, 0.1f);

		StrippableBlockRegistry.register(ModBlocks.DRIFTWOOD_LOG, ModBlocks.STRIPPED_DRIFTWOOD_LOG);
		StrippableBlockRegistry.register(ModBlocks.DRIFTWOOD_WOOD, ModBlocks.STRIPPED_DRIFTWOOD_WOOD);

		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DRIFTWOOD_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_DRIFTWOOD_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DRIFTWOOD_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_DRIFTWOOD_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DRIFTWOOD_PLANKS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.DRIFTWOOD_LEAVES, 30, 60);

		FabricDefaultAttributeRegistry.register(ModEntities.MANTIS, MantisEntity.createAttributes());

		FabricDefaultAttributeRegistry.register(ModEntities.PHROG, PhrogEntity.createAttributes());

		FabricDefaultAttributeRegistry.register(ModEntities.BLACK, BlackEntity.createBlackAttributes());
	}
}