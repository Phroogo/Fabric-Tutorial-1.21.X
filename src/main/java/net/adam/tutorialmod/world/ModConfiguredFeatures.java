package net.adam.tutorialmod.world;

import net.adam.tutorialmod.TutorialMod;
import net.adam.tutorialmod.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.*;

import java.sql.Blob;
import java.util.List;

public class ModConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> PINK_GARNET_ORE_KEY = registryKey("pink_garnet_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> END_PINK_GARNET_ORE_KEY = registryKey("end_pink_garnet_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> NETHER_PINK_GARNET_ORE_KEY = registryKey("nether_pink_garnet_ore");

    public static final RegistryKey<ConfiguredFeature<?, ?>> DRIFTWOOD_KEY = registryKey("driftwood");

    public static final RegistryKey<ConfiguredFeature<?, ?>> HONEY_BERRY_BUSH_KEY = registryKey("honey_berry_bush");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {

        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherReplaceables = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);
        RuleTest endReplaceables = new BlockMatchRuleTest(Blocks.END_STONE);

        List<OreFeatureConfig.Target> overworldPinkGarnetOres =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.PINK_GARNET_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.PINK_GARNET_DEEPSLATE_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> netherPinkGarnetOres =
                List.of(OreFeatureConfig.createTarget(netherReplaceables, ModBlocks.PINK_GARNET_NETHER_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> endPinkGarnetOres =
                List.of(OreFeatureConfig.createTarget(endReplaceables, ModBlocks.PINK_GARNET_END_ORE.getDefaultState()));

        register(context, PINK_GARNET_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldPinkGarnetOres, 11, 0.8F));
        register(context, NETHER_PINK_GARNET_ORE_KEY, Feature.ORE, new OreFeatureConfig(netherPinkGarnetOres, 9, 0.7F));
        register(context, END_PINK_GARNET_ORE_KEY, Feature.ORE, new OreFeatureConfig(endPinkGarnetOres, 6, 0.9F));

        register(context, DRIFTWOOD_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.DRIFTWOOD_LOG),
                new MegaJungleTrunkPlacer(10, 6, 3),

                BlockStateProvider.of(ModBlocks.DRIFTWOOD_LEAVES),
                new JungleFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 3),

                new TwoLayersFeatureSize(1, 0, 2)).dirtProvider(BlockStateProvider.of(Blocks.SAND)).build());

        register(context, HONEY_BERRY_BUSH_KEY, Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.HONEY_BERRY_BUSH.getDefaultState().with(SweetBerryBushBlock.AGE, 3))),
                        List.of(Blocks.GRASS_BLOCK)
                ));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registryKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(TutorialMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

}
