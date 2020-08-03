package com.dbydd.suuuuuper_herbal_tea.worldgen.tea_resource_gen;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;

import java.util.Objects;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TeaResourceGen_Placement extends Placement<NoPlacementConfig> {
    public TeaResourceGen_Placement(Function<Dynamic<?>, ? extends NoPlacementConfig> configFactoryIn) {
        super(configFactoryIn);
    }

    @Override
    public Stream<BlockPos> getPositions(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generatorIn, Random random, NoPlacementConfig configIn, BlockPos pos) {
        int i = random.nextInt(4);
        return IntStream.range(0, i).mapToObj(c -> {
            int j = random.nextInt(16) + pos.getX();
            int k = random.nextInt(16) + pos.getZ();
            int l = worldIn.getHeight(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, j, k);
            if (l > 0) {
                BlockPos blockPos = new BlockPos(j, l-1, k);
                BlockState blockState = worldIn.getBlockState(blockPos);
                if (!blockState.isAir(worldIn, blockPos) && !(blockState.getBlock() instanceof BushBlock)) {
                    return blockPos;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }).filter(Objects::nonNull);
    }
}
