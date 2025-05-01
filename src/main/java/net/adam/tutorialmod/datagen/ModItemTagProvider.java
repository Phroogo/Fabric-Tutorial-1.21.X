package net.adam.tutorialmod.datagen;

import net.adam.tutorialmod.block.ModBlocks;
import net.adam.tutorialmod.item.ModItems;
import net.adam.tutorialmod.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(ModItems.PINK_GARNET)
                .add(ModItems.RAW_PINK_GARNET)
                .add(Items.COAL)
                .add(Items.STICK)
                .add(Items.APPLE);

        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(ModItems.PINK_GARNET_SWORD);
        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add(ModItems.PINK_GARNET_PICKAXE);
        getOrCreateTagBuilder(ItemTags.SHOVELS)
                .add(ModItems.PINK_GARNET_SHOVEL);
        getOrCreateTagBuilder(ItemTags.AXES)
                .add(ModItems.PINK_GARNET_AXE);
        getOrCreateTagBuilder(ItemTags.HOES)
                .add(ModItems.PINK_GARNET_HOE);

        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.PINK_GARNET_HELMET)
                .add(ModItems.PINK_GARNET_CHESTPLATE)
                .add(ModItems.PINK_GARNET_LEGGINGS)
                .add(ModItems.PINK_GARNET_BOOTS)
                .add(ModItems.SLIME_HELMET)
                .add(ModItems.SLIME_CHESTPLATE)
                .add(ModItems.SLIME_LEGGINGS)
                .add(ModItems.SLIME_BOOTS)
                .add(ModItems.COPPERMAN_HELMET)
                .add(ModItems.COPPERMAN_CHESTPLATE)
                .add(ModItems.COPPERMAN_LEGGINGS)
                .add(ModItems.COPPERMAN_BOOTS)
                .add(ModItems.ACTIVATED_COPPERMAN_HELMET)
                .add(ModItems.ACTIVATED_COPPERMAN_CHESTPLATE)
                .add(ModItems.ACTIVATED_COPPERMAN_LEGGINGS)
                .add(ModItems.ACTIVATED_COPPERMAN_BOOTS);

        getOrCreateTagBuilder(ItemTags.TRIM_MATERIALS)
                .add(ModItems.PINK_GARNET);
        getOrCreateTagBuilder(ItemTags.TRIM_TEMPLATES)
                .add(ModItems.COOL_SMITHING_TEMPLATE);

        getOrCreateTagBuilder(ModTags.Items.WHIPS).add(ModItems.WHIP);

        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.DRIFTWOOD_LOG.asItem())
                .add(ModBlocks.DRIFTWOOD_WOOD.asItem())
                .add(ModBlocks.STRIPPED_DRIFTWOOD_LOG.asItem())
                .add(ModBlocks.STRIPPED_DRIFTWOOD_WOOD.asItem());

        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(ModBlocks.DRIFTWOOD_PLANKS.asItem());    }
}