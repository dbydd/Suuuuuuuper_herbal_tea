//package com.dbydd.suuuuuper_herbal_tea.worldgen.tea_house;
//
//import com.mojang.datafixers.Dynamic;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.MutableBoundingBox;
//import net.minecraft.world.biome.Biome;
//import net.minecraft.world.biome.BiomeManager;
//import net.minecraft.world.gen.ChunkGenerator;
//import net.minecraft.world.gen.Heightmap;
//import net.minecraft.world.gen.feature.NoFeatureConfig;
//import net.minecraft.world.gen.feature.structure.Structure;
//import net.minecraft.world.gen.feature.structure.StructureStart;
//import net.minecraft.world.gen.feature.template.TemplateManager;
//
//import java.util.Random;
//import java.util.function.Function;
//
//public class TeaHouseStructure extends Structure<NoFeatureConfig> {
//    public TeaHouseStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
//        super(configFactoryIn);
//    }
//
//    @Override
//    public boolean canBeGenerated(BiomeManager biomeManagerIn, ChunkGenerator<?> generatorIn, Random randIn, int chunkX, int chunkZ, Biome biomeIn) {
//        return randIn.nextFloat() <= 0.002;
//    }
//
//    @Override
//    public IStartFactory getStartFactory() {
//        return Start::new;
//    }
//
//    @Override
//    public String getStructureName() {
//        return "tea_house";
//    }
//
//    @Override
//    public int getSize() {
//        return 3;
//    }
//
//    public static class Start extends StructureStart {
//        public Start(Structure<?> structure, int chunkPosX, int chunkPosZ, MutableBoundingBox bounds, int references, long seed) {
//            super(structure, chunkPosX, chunkPosZ, bounds, references, seed);
//        }
//
//        @Override
//        public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn) {
//            int beginx = chunkX * 16;
//            int beginz = chunkZ * 16;
//            int tempY = 256;
//            for (int i = 0; i <= 32; i++) {
//                for (int j = 0; j <= 32; j++) {
//                    int tempY2 = generator.func_222531_c(beginx + i, beginz + j, Heightmap.Type.WORLD_SURFACE_WG);
//                    if (tempY > tempY2) tempY = tempY2;
//                }
//            }
//            TeaHouseStructurePiece teaHouseStructurePiece = new TeaHouseStructurePiece(templateManagerIn, new BlockPos(chunkX * 16, tempY, chunkZ * 16), rand);
//            this.components.add(teaHouseStructurePiece);
//            recalculateStructureSize();
//        }
//    }
//}
