package com.dbydd.suuuuuper_herbal_tea.biomes;

import com.dbydd.suuuuuper_herbal_tea.Suuuuuuuper_herbal_tea;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraftforge.common.ForgeHooks;

import java.util.Random;
import java.util.function.Function;

public class Gazebo extends Structure<NoFeatureConfig> {


    public Gazebo(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
        super(configFactoryIn);
    }

    @Override
    public boolean canBeGenerated(BiomeManager biomeManagerIn, ChunkGenerator<?> generatorIn, Random randIn, int chunkX, int chunkZ, Biome biomeIn) {
//        if (biomeIn instanceof Tea_Villa) {
//            if (randIn.nextFloat() < 0.1) {
                return true;
//            }
//        }
//        return false;
    }

    @Override
    public IStartFactory getStartFactory() {
        return Start::new;
    }

    @Override
    public String getStructureName() {
        return Suuuuuuuper_herbal_tea.NAME + "_Gazebo";
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
            this.components.add(new GazeboPiece(templateManagerIn, new ResourceLocation(Suuuuuuuper_herbal_tea.NAME, "structures/" + "test"), Rotation.randomRotation(new Random(chunkX+chunkZ)),new BlockPos(chunkX*16, 100, chunkZ*16)));
        }
    }
}
