package net.adam.tutorialmod.item;

import net.adam.tutorialmod.TutorialMod;
import net.adam.tutorialmod.block.ModBlocks;
import net.adam.tutorialmod.entity.ModEntities;
import net.adam.tutorialmod.item.custom.*;
import net.adam.tutorialmod.sound.ModSounds;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlag;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModItems {

    public static final Item PINK_GARNET = registerItem("pink_garnet", new Item(new Item.Settings()));
    public static final Item RAW_PINK_GARNET = registerItem("raw_pink_garnet", new Item(new Item.Settings()));

    public static final Item CHISEL = registerItem("chisel", new ChiselItem((new Item.Settings().maxDamage(32))));

    public static final Item CAULIFLOWER = registerItem("cauliflower", new Item(new Item.Settings().food(ModFoodComponents.CAULIFLOWER)){
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable(("tooltip.tutorialmod.cauliflower")));
            super.appendTooltip(stack, context, tooltip, type);
        }
    });

    public static final Item STARLIGHT_ASHES = registerItem("starlight_ashes", new Item(new Item.Settings()));

    public static final  Item PINK_GARNET_SWORD = registerItem("pink_garnet_sword",
            new SwordItem(ModToolMaterials.PINK_GARNET, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.PINK_GARNET, 3, -2.4f))));
    public static final  Item PINK_GARNET_PICKAXE = registerItem("pink_garnet_pickaxe",
            new PickaxeItem(ModToolMaterials.PINK_GARNET, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.PINK_GARNET, 1, -2.8f))));
    public static final  Item PINK_GARNET_SHOVEL = registerItem("pink_garnet_shovel",
            new ShovelItem(ModToolMaterials.PINK_GARNET, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.PINK_GARNET, 1.5f, -3.0f))));
    public static final  Item PINK_GARNET_AXE = registerItem("pink_garnet_axe",
            new AxeItem(ModToolMaterials.PINK_GARNET, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.PINK_GARNET, 6, -3.2f))));
    public static final  Item PINK_GARNET_HOE = registerItem("pink_garnet_hoe",
            new HoeItem(ModToolMaterials.PINK_GARNET, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.PINK_GARNET, 0, -3.0f))));
    public static final Item PINK_GARNET_HAMMER = registerItem("pink_garnet_hammer",
            new HammerItem(ModToolMaterials.PINK_GARNET, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.PINK_GARNET, 7, -3.4f))));

    public static final Item PINK_GARNET_HELMET = registerItem("pink_garnet_helmet",
            new ModArmorItem(ModArmorMaterials.PINK_GARNET_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()
                    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(35))));
    public static final Item PINK_GARNET_CHESTPLATE = registerItem("pink_garnet_chestplate",
            new ArmorItem(ModArmorMaterials.PINK_GARNET_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings()
                    .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(35))));
    public static final Item PINK_GARNET_LEGGINGS = registerItem("pink_garnet_leggings",
            new ArmorItem(ModArmorMaterials.PINK_GARNET_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(35))));
    public static final Item PINK_GARNET_BOOTS = registerItem("pink_garnet_boots",
            new ArmorItem(ModArmorMaterials.PINK_GARNET_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(35))));

    public static final Item SLIME_HELMET = registerItem("slime_helmet",
            new ModArmorItem(ModArmorMaterials.SLIME_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()
                    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(17))));
    public static final Item SLIME_CHESTPLATE = registerItem("slime_chestplate",
            new ArmorItem(ModArmorMaterials.SLIME_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings()
                    .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(17))));
    public static final Item SLIME_LEGGINGS = registerItem("slime_leggings",
            new ArmorItem(ModArmorMaterials.SLIME_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(17))));
    public static final Item SLIME_BOOTS = registerItem("slime_boots",
            new ArmorItem(ModArmorMaterials.SLIME_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(17))));

    public static final Item COPPERMAN_HELMET = registerItem("copperman_helmet",
            new ModArmorItem(ModArmorMaterials.COPPER_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()
                    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(25))));
    public static final Item COPPERMAN_CHESTPLATE = registerItem("copperman_chestplate",
            new ArmorItem(ModArmorMaterials.COPPER_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings()
                    .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(25))));
    public static final Item COPPERMAN_LEGGINGS = registerItem("copperman_leggings",
            new ArmorItem(ModArmorMaterials.COPPER_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(25))));
    public static final Item COPPERMAN_BOOTS = registerItem("copperman_boots",
            new ArmorItem(ModArmorMaterials.COPPER_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(25))));

    public static final Item ACTIVATED_COPPERMAN_HELMET = registerItem("activated_copperman_helmet",
            new ModArmorItem(ModArmorMaterials.ACTIVATED_COPPER_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()
                    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(25))));
    public static final Item ACTIVATED_COPPERMAN_CHESTPLATE = registerItem("activated_copperman_chestplate",
            new ArmorItem(ModArmorMaterials.ACTIVATED_COPPER_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings()
                    .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(25))));
    public static final Item ACTIVATED_COPPERMAN_LEGGINGS = registerItem("activated_copperman_leggings",
            new ArmorItem(ModArmorMaterials.ACTIVATED_COPPER_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(25))));
    public static final Item ACTIVATED_COPPERMAN_BOOTS = registerItem("activated_copperman_boots",
            new ArmorItem(ModArmorMaterials.ACTIVATED_COPPER_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(25))));

    public static final Item PINK_GARNET_HORSE_ARMOR = registerItem("pink_garnet_horse_armor",
            new AnimalArmorItem(ModArmorMaterials.PINK_GARNET_ARMOR_MATERIAL, AnimalArmorItem.Type.EQUESTRIAN, false, new Item.Settings().maxCount(1)));

   public static final Item COOL_SMITHING_TEMPLATE = registerItem("cool_smithing_template",
           SmithingTemplateItem.of(Identifier.of(TutorialMod.MOD_ID, "cool"), FeatureFlags.VANILLA));

   public static final Item PINK_GARNET_BOW =  registerItem("pink_garnet_bow",
           new BowItem(new Item.Settings().maxDamage(761)));

   public static final Item BAR_BRAWL_MUSIC_DISC = registerItem("bar_brawl_music_disc",
           new Item(new Item.Settings().jukeboxPlayable(ModSounds.BAR_BRAWL_KEY).maxCount(1)));

   public static final Item CAULIFLOWER_SEEDS = registerItem("cauliflower_seeds",
           new AliasedBlockItem(ModBlocks.CAULIFLOWER_CROP, new Item.Settings()));

   public static final Item HONEY_BERRIES = registerItem("honey_berries",
           new AliasedBlockItem(ModBlocks.HONEY_BERRY_BUSH, new Item.Settings().food(ModFoodComponents.HONEY_BERRY)));

   public static final Item WHIP = registerItem("whip",
           new WhipItem(new Item.Settings().maxCount(1).maxDamage(332)));

   public static final Item CANCEROUS_REACTOR = registerItem("cancerous_reactor",
           new Item(new Item.Settings().food(ModFoodComponents.CANCEROUS_REACTOR)) {
               @Override
               public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
                   tooltip.add(Text.translatable(("tooltip.tutorialmod.cancerous_reactor")));
                   super.appendTooltip(stack, context, tooltip, type);
               }
           });

   public static final Item MANTIS_SPAWN_EGG = registerItem("mantis_spawn_egg",
           new SpawnEggItem(ModEntities.MANTIS, 0x9dc783, 0xbfaf5f, new Item.Settings()));
   public static final Item PHROG_SPAWN_EGG = registerItem("phrog_spawn_egg",
           new SpawnEggItem(ModEntities.PHROG, 0x1b6ab2, 0x26ebfb, new Item.Settings()));

   public static final Item TOMAHAWK = registerItem("tomahawk",
           new TomahawkItem(new Item.Settings().maxCount(16)));

   private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(TutorialMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        TutorialMod.LOGGER.info("Registering Mod Items for " + TutorialMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(PINK_GARNET);
            entries.add(RAW_PINK_GARNET);
        });
    }
}
