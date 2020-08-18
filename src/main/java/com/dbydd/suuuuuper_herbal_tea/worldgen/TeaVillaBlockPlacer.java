package com.dbydd.suuuuuper_herbal_tea.worldgen;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockplacer.BlockPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TeaVillaBlockPlacer extends net.minecraft.world.gen.blockplacer.BlockPlacer {

    public TeaVillaBlockPlacer() {
        super(BlockPlacerType.SIMPLE_BLOCK);
    }

    @Override
    public void func_225567_a_(IWorld world, BlockPos blockPos, BlockState blockState, Random random) {
        Block block = blockState.getBlock();
        int randX = 8 + random.nextInt(8);
        int randZ = 8 + random.nextInt(8);
        double randOfNull = random.nextDouble();
        int beginX = blockPos.getX();
        int beginZ = blockPos.getZ();
        int rowCount = random.nextInt(randX);
        if (block == Blocks.GRASS_PATH) {
            for (int xAdd = 0; xAdd <= randX; xAdd++) {
                for (int zAdd = 0; zAdd <= randZ; zAdd++) {
                    int currentX = beginX + xAdd;
                    int currentZ = beginZ + zAdd;
                    int currentY = world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, currentX, currentZ);
                    BlockPos currentPos = new BlockPos(currentX, currentY - 1, currentZ);
                    BlockState blockState1 = world.getBlockState(currentPos);
                    if (canplace(blockState1)) {
                        world.setBlockState(currentPos, blockState, 3);
                    }
                }
            }
        } else {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < rowCount; i++) {
                list.add(random.nextInt(randX));
            }
            for (int xAdd = 0; xAdd <= randX; xAdd++) {
                if (random.nextDouble() <= randOfNull) {
                    continue;
                }
                for (int zAdd = 0; zAdd <= randZ; zAdd++) {
                    if (list.contains(zAdd)) continue;
                    int currentX = beginX + xAdd;
                    int currentZ = beginZ + zAdd;
                    int currentY = world.getHeight(Heightmap.Type.WORLD_SURFACE, currentX, currentZ);
                    BlockPos currentPos = new BlockPos(currentX, currentY, currentZ);
                    BlockState blockState1 = world.getBlockState(currentPos.offset(Direction.DOWN));
                    if (canplace(blockState1)) {
                        world.setBlockState(currentPos, blockState, 3);
                    }

                }
            }
        }
    }

    private boolean canplace(BlockState blockState) {
        return blockState.isSolid() && !(blockState.getBlock() instanceof FlowingFluidBlock) && (blockState.getBlock() == Blocks.DIRT || blockState.getBlock() == Blocks.COARSE_DIRT || blockState.getBlock() == Blocks.GRASS_BLOCK);
    }

    @Override
    public <T> T serialize(DynamicOps<T> p_218175_1_) {
        return (new Dynamic<>(p_218175_1_, p_218175_1_.createMap(ImmutableMap.of(p_218175_1_.createString("type"), p_218175_1_.createString(Registry.BLOCK_PLACER_TYPE.getKey(this.field_227258_a_).toString()))))).getValue();
    }
}
