package com.dbydd.suuuuuper_herbal_tea.utils;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import java.util.Map;
import java.util.Random;

public class StructureUtils {
    public static void PlaceCircle(IWorld world, int range, BlockPos beginpos,Map<Double, BlockState> map, Random random) {
        for (int x = -range; x <= range; x++) {
            for (int z = -range; z <= range; z++) {
                if (MathUtils.inCircle(x, z, range)) {
                    world.setBlockState(new BlockPos(beginpos.getX() + x, beginpos.getY(), beginpos.getZ() + z), RandomUtils.outputRandmonBlockByList(random, map), 2);
                }
            }
        }

    }

    public static void PlaceCone(IWorld world, int beginrange, BlockPos beginpos, int height, Map<Double, BlockState> map, Random random) {
        int k = ((beginrange * height) % 7)+1;
        for (int i = 0, range = beginrange; i <= height; i++) {
            if ((i+1) % k == 0) range--;
            PlaceCircle(world, range, new BlockPos(beginpos.getX(), beginpos.getY() + i, beginpos.getZ()), map, random);
        }

    }
}
