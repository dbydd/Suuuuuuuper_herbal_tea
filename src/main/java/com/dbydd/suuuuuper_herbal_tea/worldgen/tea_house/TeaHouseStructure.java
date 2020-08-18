package com.dbydd.suuuuuper_herbal_tea.worldgen.tea_house;

import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Biomes;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;
import java.util.function.Function;

public class TeaHouseStructure extends Structure<NoFeatureConfig> {
    public TeaHouseStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
        super(configFactoryIn);
    }

    @Override
    public boolean canBeGenerated(BiomeManager biomeManagerIn, ChunkGenerator<?> generatorIn, Random randIn, int chunkX, int chunkZ, Biome biomeIn) {
        if(biomeIn == Registered_Biomes.TEA_VILLA.get()){
            return randIn.nextFloat() <= 0.25;
        }
        return randIn.nextFloat() <= 0.002;
    }

    @Override
    public IStartFactory getStartFactory() {
        return Start::new;
    }

    @Override
    public String getStructureName() {
        return "tea_house";
    }

    @Override
    public int getSize() {
        return 3;
    }

    public static class Start extends StructureStart {
        public Start(Structure<?> structure, int chunkPosX, int chunkPosZ, MutableBoundingBox bounds, int references, long seed) {
            super(structure, chunkPosX, chunkPosZ, bounds, references, seed);
        }

        @Override
        public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn) {
            TeaHouseStructurePiece teaHouseStructurePiece = new TeaHouseStructurePiece(templateManagerIn, new BlockPos(chunkX*16, generator.func_222531_c(chunkX, chunkZ, Heightmap.Type.WORLD_SURFACE_WG), chunkZ*16), rand);
            this.components.add(teaHouseStructurePiece);
            this.recalculateStructureSize();
        }
    }
}
