package com.dbydd.suuuuuper_herbal_tea.worldgen.tea_resource_gen;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

public class TeaVillaResourceGenPlacement extends Placement<NoPlacementConfig> {
    public TeaVillaResourceGenPlacement(Function<Dynamic<?>, ? extends NoPlacementConfig> configFactoryIn) {
        super(configFactoryIn);
    }

    @Override
    public Stream<BlockPos> getPositions(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generatorIn, Random random, NoPlacementConfig configIn, BlockPos pos) {
        int seaLevel = worldIn.getSeaLevel();
        int height = worldIn.getHeight();
        List<BlockPos> list = new ArrayList<>();
        for (int i = seaLevel; i <= height; i++) {
            BlockPos blockPos = new BlockPos(pos.getX(), i, pos.getZ());
            if (worldIn.getBlockState(blockPos).isAir(worldIn, blockPos) && worldIn.getBlockState(blockPos.offset(Direction.DOWN)).getMaterial() == Material.EARTH) list.add(blockPos);
        }
        return list.stream();
    }

    @Override
    protected <FC extends IFeatureConfig, F extends Feature<FC>> boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> p_214998_2_, Random random, BlockPos pos, NoPlacementConfig p_214998_5_, ConfiguredFeature<FC, F> p_214998_6_) {
       return p_214998_6_.place(worldIn, p_214998_2_, random, pos);
    }
}
