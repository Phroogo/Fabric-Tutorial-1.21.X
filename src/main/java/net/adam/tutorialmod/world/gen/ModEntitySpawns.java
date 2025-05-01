package net.adam.tutorialmod.world.gen;

import com.sun.jna.StructureWriteContext;
import net.adam.tutorialmod.entity.ModEntities;
import net.adam.tutorialmod.entity.custom.MantisEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureSpawns;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureKeys;

public class ModEntitySpawns {

    public static void addSpawns() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.PLAINS, BiomeKeys.CHERRY_GROVE),
                SpawnGroup.CREATURE, ModEntities.MANTIS, 30, 1, 2);

        SpawnRestriction.register(ModEntities.MANTIS, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.SWAMP, BiomeKeys.MANGROVE_SWAMP, BiomeKeys.BAMBOO_JUNGLE, BiomeKeys.PLAINS),
                SpawnGroup.CREATURE, ModEntities.PHROG, 30, 1, 1);

        SpawnRestriction.register(ModEntities.PHROG, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
    }
}
