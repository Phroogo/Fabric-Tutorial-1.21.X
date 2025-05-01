package net.adam.tutorialmod.datagen;

import net.adam.tutorialmod.TutorialMod;
import net.adam.tutorialmod.block.ModBlocks;
import net.adam.tutorialmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.recipe.SmithingTransformRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.recipe.input.SmithingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        List<ItemConvertible> PINK_GARNET_SMELTABLES = List.of(ModItems.RAW_PINK_GARNET, ModBlocks.PINK_GARNET_ORE,
                ModBlocks.PINK_GARNET_DEEPSLATE_ORE);

        offerSmelting(exporter, PINK_GARNET_SMELTABLES, RecipeCategory.MISC, ModItems.PINK_GARNET, 0.25f, 200, "pink_garnet");
        offerBlasting(exporter, PINK_GARNET_SMELTABLES, RecipeCategory.MISC, ModItems.PINK_GARNET, 0.25f, 100, "pink_garnet");

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.PINK_GARNET_BLOCK)
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .input('R', ModItems.PINK_GARNET)
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PINK_GARNET, 9)
                .input(ModBlocks.PINK_GARNET_BLOCK)
                .criterion(hasItem(ModBlocks.PINK_GARNET_BLOCK), conditionsFromItem(ModBlocks.PINK_GARNET_BLOCK))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.RAW_PINK_GARNET_BLOCK)
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .input('R', ModItems.RAW_PINK_GARNET)
                .criterion(hasItem(ModItems.RAW_PINK_GARNET), conditionsFromItem(ModItems.RAW_PINK_GARNET))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_PINK_GARNET, 9)
                .input(ModBlocks.RAW_PINK_GARNET_BLOCK)
                .criterion(hasItem(ModBlocks.RAW_PINK_GARNET_BLOCK), conditionsFromItem(ModBlocks.RAW_PINK_GARNET_BLOCK))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_PINK_GARNET, 32)
                .input(ModBlocks.MAGIC_BLOCK)
                .criterion(hasItem(ModBlocks.MAGIC_BLOCK), conditionsFromItem(ModBlocks.MAGIC_BLOCK))
                .offerTo(exporter, Identifier.of(TutorialMod.MOD_ID, "raw_pink_garnet_from_magic_block"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.PINK_GARNET_STAIRS, 4)
                .pattern("R  ")
                .pattern("RR ")
                .pattern("RRR")
                .input('R', ModItems.PINK_GARNET)
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.PINK_GARNET_SLAB, 6)
                .pattern("   ")
                .pattern("   ")
                .pattern("RRR")
                .input('R', ModItems.PINK_GARNET)
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.PINK_GARNET_BUTTON)
                .input(ModItems.PINK_GARNET)
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.PINK_GARNET_PRESSURE_PLATE)
                .pattern("   ")
                .pattern("RR ")
                .pattern("   ")
                .input('R', ModItems.PINK_GARNET)
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.PINK_GARNET_FENCE, 3)
                .pattern("   ")
                .pattern("RSR")
                .pattern("RSR")
                .input('R', ModItems.PINK_GARNET)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.PINK_GARNET_FENCE_GATE)
                .pattern("   ")
                .pattern("SRS")
                .pattern("SRS")
                .input('R', ModItems.PINK_GARNET)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.PINK_GARNET_WALL, 6)
                .pattern("   ")
                .pattern("RRR")
                .pattern("RRR")
                .input('R', ModItems.PINK_GARNET)
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.PINK_GARNET_DOOR, 3)
                .pattern("RR ")
                .pattern("RR ")
                .pattern("RR ")
                .input('R', ModItems.PINK_GARNET)
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.PINK_GARNET_TRAPDOOR)
                .pattern("   ")
                .pattern("RR ")
                .pattern("RR ")
                .input('R', ModItems.PINK_GARNET)
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.PINK_GARNET_LAMP)
                .pattern(" R ")
                .pattern("RGR")
                .pattern(" R ")
                .input('R', ModItems.PINK_GARNET)
                .input('G', Blocks.GLOWSTONE)
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.CHISEL)
                .pattern(" T ")
                .pattern(" S ")
                .pattern(" S ")
                .input('T', Items.SMOOTH_STONE)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.PINK_GARNET_SWORD)
                .pattern(" G ")
                .pattern(" G ")
                .pattern(" S ")
                .input('G', ModItems.PINK_GARNET)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.PINK_GARNET_PICKAXE)
                .pattern("GGG")
                .pattern(" S ")
                .pattern(" S ")
                .input('G', ModItems.PINK_GARNET)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.PINK_GARNET_SHOVEL)
                .pattern(" G ")
                .pattern(" S ")
                .pattern(" S ")
                .input('G', ModItems.PINK_GARNET)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.PINK_GARNET_AXE)
                .pattern(" GG")
                .pattern(" SG")
                .pattern(" S ")
                .input('G', ModItems.PINK_GARNET)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.PINK_GARNET_HOE)
                .pattern(" GG")
                .pattern(" S ")
                .pattern(" S ")
                .input('G', ModItems.PINK_GARNET)
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.PINK_GARNET_HAMMER)
                .pattern("GGG")
                .pattern("GSG")
                .pattern(" S ")
                .input('G', ModBlocks.PINK_GARNET_BLOCK)
                .input('S', Items.STICK)
                .criterion(hasItem(ModBlocks.PINK_GARNET_BLOCK), conditionsFromItem(ModBlocks.PINK_GARNET_BLOCK))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PINK_GARNET_HELMET)
                .pattern("GGG")
                .pattern("G G")
                .pattern("   ")
                .input('G', ModItems.PINK_GARNET)
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PINK_GARNET_CHESTPLATE)
                .pattern("G G")
                .pattern("GGG")
                .pattern("GGG")
                .input('G', ModItems.PINK_GARNET)
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PINK_GARNET_LEGGINGS)
                .pattern("GGG")
                .pattern("G G")
                .pattern("G G")
                .input('G', ModItems.PINK_GARNET)
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PINK_GARNET_BOOTS)
                .pattern("   ")
                .pattern("G G")
                .pattern("G G")
                .input('G', ModItems.PINK_GARNET)
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SLIME_HELMET)
                .pattern("SSS")
                .pattern("S S")
                .pattern("   ")
                .input('S', Items.SLIME_BALL)
                .criterion(hasItem(Items.SLIME_BALL), conditionsFromItem(Items.SLIME_BALL))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SLIME_CHESTPLATE)
                .pattern("S S")
                .pattern("SSS")
                .pattern("SSS")
                .input('S', Items.SLIME_BALL)
                .criterion(hasItem(Items.SLIME_BALL), conditionsFromItem(Items.SLIME_BALL))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SLIME_LEGGINGS)
                .pattern("SSS")
                .pattern("S S")
                .pattern("S S")
                .input('S', Items.SLIME_BALL)
                .criterion(hasItem(Items.SLIME_BALL), conditionsFromItem(Items.SLIME_BALL))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SLIME_BOOTS)
                .pattern("   ")
                .pattern("S S")
                .pattern("S S")
                .input('S', Items.SLIME_BALL)
                .criterion(hasItem(Items.SLIME_BALL), conditionsFromItem(Items.SLIME_BALL))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COPPERMAN_HELMET)
                .pattern("CCC")
                .pattern("C C")
                .pattern("   ")
                .input('C', Blocks.COPPER_BLOCK)
                .criterion(hasItem(Blocks.COPPER_BLOCK), conditionsFromItem(Blocks.COPPER_BLOCK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COPPERMAN_CHESTPLATE)
                .pattern("C C")
                .pattern("CCC")
                .pattern("CCC")
                .input('C', Blocks.EXPOSED_COPPER)
                .criterion(hasItem(Blocks.EXPOSED_COPPER), conditionsFromItem(Blocks.EXPOSED_COPPER))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COPPERMAN_LEGGINGS)
                .pattern("CCC")
                .pattern("C C")
                .pattern("C C")
                .input('C', Blocks.WEATHERED_COPPER)
                .criterion(hasItem(Blocks.WEATHERED_COPPER), conditionsFromItem(Blocks.WEATHERED_COPPER))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COPPERMAN_BOOTS)
                .pattern("   ")
                .pattern("C C")
                .pattern("C C")
                .input('C', Blocks.OXIDIZED_COPPER)
                .criterion(hasItem(Blocks.OXIDIZED_COPPER), conditionsFromItem(Blocks.OXIDIZED_COPPER))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PINK_GARNET_HORSE_ARMOR)
                .pattern("G G")
                .pattern("GGG")
                .pattern("G G")
                .input('G', ModItems.PINK_GARNET)
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter);

        offerSmithingTrimRecipe(exporter, ModItems.COOL_SMITHING_TEMPLATE, Identifier.of(TutorialMod.MOD_ID, "cool"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PINK_GARNET_BOW)
                .pattern(" GS")
                .pattern("G S")
                .pattern(" GS")
                .input('G', ModItems.PINK_GARNET)
                .input('S', Items.STRING)
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.WHIP)
                .pattern("  L")
                .pattern(" L ")
                .pattern("N  ")
                .input('L', Items.LEATHER)
                .input('N', Items.NETHERITE_INGOT)
                .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CANCEROUS_REACTOR)
                .pattern("PPP")
                .pattern("PPP")
                .pattern("PPP")
                .input('P', Items.POISONOUS_POTATO)
                .criterion(hasItem(Items.POISONOUS_POTATO), conditionsFromItem(Items.POISONOUS_POTATO))
                .offerTo(exporter);

        SmithingTransformRecipeJsonBuilder.create(Ingredient.ofItems(Items.AIR),  Ingredient.ofItems(ModItems.COPPERMAN_HELMET), Ingredient.ofItems(ModItems.CANCEROUS_REACTOR), RecipeCategory.MISC, ModItems.ACTIVATED_COPPERMAN_HELMET)
                .criterion(hasItem(ModItems.CANCEROUS_REACTOR), conditionsFromItem(ModItems.CANCEROUS_REACTOR))
                .offerTo(exporter, Identifier.of(TutorialMod.MOD_ID, "activated_copperman_helmet"));
        SmithingTransformRecipeJsonBuilder.create(Ingredient.ofItems(Items.AIR),  Ingredient.ofItems(ModItems.COPPERMAN_CHESTPLATE), Ingredient.ofItems(ModItems.CANCEROUS_REACTOR), RecipeCategory.MISC, ModItems.ACTIVATED_COPPERMAN_CHESTPLATE)
                .criterion(hasItem(ModItems.CANCEROUS_REACTOR), conditionsFromItem(ModItems.CANCEROUS_REACTOR))
        .offerTo(exporter, Identifier.of(TutorialMod.MOD_ID, "activated_copperman_chestplate"));
        SmithingTransformRecipeJsonBuilder.create(Ingredient.ofItems(Items.AIR),  Ingredient.ofItems(ModItems.COPPERMAN_LEGGINGS), Ingredient.ofItems(ModItems.CANCEROUS_REACTOR), RecipeCategory.MISC, ModItems.ACTIVATED_COPPERMAN_LEGGINGS)
                .criterion(hasItem(ModItems.CANCEROUS_REACTOR), conditionsFromItem(ModItems.CANCEROUS_REACTOR))
                .offerTo(exporter, Identifier.of(TutorialMod.MOD_ID, "activated_copperman_leggings"));
        SmithingTransformRecipeJsonBuilder.create(Ingredient.ofItems(Items.AIR),  Ingredient.ofItems(ModItems.COPPERMAN_BOOTS), Ingredient.ofItems(ModItems.CANCEROUS_REACTOR), RecipeCategory.MISC, ModItems.ACTIVATED_COPPERMAN_BOOTS)
                .criterion(hasItem(ModItems.CANCEROUS_REACTOR), conditionsFromItem(ModItems.CANCEROUS_REACTOR))
                .offerTo(exporter, Identifier.of(TutorialMod.MOD_ID, "activated_copperman_boots"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.DRIFTWOOD_PLANKS, 4)
                .input(ModBlocks.DRIFTWOOD_LOG)
                .criterion(hasItem(ModBlocks.DRIFTWOOD_LOG), conditionsFromItem(ModBlocks.DRIFTWOOD_LOG))
                .offerTo(exporter);

    }
}