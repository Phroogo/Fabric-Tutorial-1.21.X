package net.adam.tutorialmod.item;

import net.adam.tutorialmod.TutorialMod;
import net.adam.tutorialmod.util.ModTags;
import net.minecraft.block.Blocks;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.MinecartItem;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {

    public static final RegistryEntry<ArmorMaterial> PINK_GARNET_ARMOR_MATERIAL = registerArmorMaterial("pink_garnet",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 2);
                map.put(ArmorItem.Type.LEGGINGS, 5);
                map.put(ArmorItem.Type.CHESTPLATE, 6);
                map.put(ArmorItem.Type.HELMET, 3);
                map.put(ArmorItem.Type.BODY, 10);
            }), 25, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, () -> Ingredient.ofItems(ModItems.PINK_GARNET),
                List.of(new ArmorMaterial.Layer(Identifier.of(TutorialMod.MOD_ID, "pink_garnet"))),1F, 0));

    public static final RegistryEntry<ArmorMaterial> SLIME_ARMOR_MATERIAL = registerArmorMaterial("slime_armor_material",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 1);
                map.put(ArmorItem.Type.LEGGINGS, 3);
                map.put(ArmorItem.Type.CHESTPLATE, 4);
                map.put(ArmorItem.Type.HELMET, 2);
                map.put(ArmorItem.Type.BODY, 6);
            }), 17, SoundEvents.BLOCK_NOTE_BLOCK_PLING, () -> Ingredient.ofItems(Items.SLIME_BALL),
                    List.of(new ArmorMaterial.Layer(Identifier.of(TutorialMod.MOD_ID, "slimeball"))),0F, 0));

    public static final RegistryEntry<ArmorMaterial> COPPER_ARMOR_MATERIAL = registerArmorMaterial("copper_armor_material",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 2);
                map.put(ArmorItem.Type.LEGGINGS, 5);
                map.put(ArmorItem.Type.CHESTPLATE, 6);
                map.put(ArmorItem.Type.HELMET, 3);
                map.put(ArmorItem.Type.BODY, 8);
            }), 15, SoundEvents.ITEM_ARMOR_EQUIP_IRON, () -> Ingredient.ofItems(Blocks.COPPER_BLOCK),
                    List.of(new ArmorMaterial.Layer(Identifier.of(TutorialMod.MOD_ID, "copper"))),0F, 0));

    public static final RegistryEntry<ArmorMaterial> ACTIVATED_COPPER_ARMOR_MATERIAL = registerArmorMaterial("activated_copper_armor_material",
            () -> new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 2);
                map.put(ArmorItem.Type.LEGGINGS, 5);
                map.put(ArmorItem.Type.CHESTPLATE, 6);
                map.put(ArmorItem.Type.HELMET, 3);
                map.put(ArmorItem.Type.BODY, 8);
            }), 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, () -> Ingredient.ofItems(Blocks.COPPER_BLOCK),
                    List.of(new ArmorMaterial.Layer(Identifier.of(TutorialMod.MOD_ID, "activated_copper"))),0F, 0));

    public static RegistryEntry<ArmorMaterial> registerArmorMaterial(String name, Supplier<ArmorMaterial> material) {
        return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(TutorialMod.MOD_ID, name), material.get());
    }
}
