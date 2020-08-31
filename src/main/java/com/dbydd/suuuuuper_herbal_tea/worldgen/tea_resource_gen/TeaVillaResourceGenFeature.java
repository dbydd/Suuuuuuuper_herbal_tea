package com.dbydd.suuuuuper_herbal_tea.worldgen.tea_resource_gen;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.FlowersFeature;

import java.util.Random;
import java.util.function.Function;

public class TeaVillaResourceGenFeature extends FlowersFeature<BlockClusterFeatureConfig> {

    public TeaVillaResourceGenFeature(Function<Dynamic<?>, ? extends BlockClusterFeatureConfig> p_i49876_1_) {
        super(p_i49876_1_);
    }

    @Override
    public boolean func_225559_a_(IWorld p_225559_1_, BlockPos p_225559_2_, BlockClusterFeatureConfig p_225559_3_) {
        return true;
    }

    @Override
    public int func_225560_a_(BlockClusterFeatureConfig p_225560_1_) {
        return 150;
    }

    @Override
    public BlockPos getNearbyPos(Random p_225561_1_, BlockPos p_225561_2_, BlockClusterFeatureConfig p_225561_3_) {
        return new BlockPos(p_225561_2_.getX() + p_225561_1_.nextInt(7) - p_225561_1_.nextInt(7), p_225561_2_.getY() + 1, p_225561_2_.getZ() + p_225561_1_.nextInt(7) - p_225561_1_.nextInt(7));
    }

    @Override
    public BlockState getFlowerToPlace(Random p_225562_1_, BlockPos p_225562_2_, BlockClusterFeatureConfig p_225562_3_) {
        return p_225562_3_.stateProvider.getBlockState(p_225562_1_, p_225562_2_);
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, BlockClusterFeatureConfig config) {
        config.blockPlacer.func_225567_a_(worldIn, pos, getFlowerToPlace(rand, pos, config), rand);
        return true;
    }
}
