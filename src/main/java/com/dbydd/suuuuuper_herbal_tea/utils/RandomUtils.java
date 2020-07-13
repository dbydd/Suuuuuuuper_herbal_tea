package com.dbydd.suuuuuper_herbal_tea.utils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomUtils {
    private static final Random random = new Random();

    public static boolean outputBooleanByChance(Random rand, double chance) {
        double d = rand.nextDouble();
        return d <= chance;
    }

    public static BlockState outputRandmonBlockByList(Random rand, Map<Double, BlockState> map) {
        Double d = rand.nextDouble();
        Double sum = 0.0d;
        int size = map.size();
        int time = 0;
        for (Map.Entry<Double, BlockState> entry : map.entrySet()) {
            sum += (Double) entry.getKey();
            if (sum >= d || time == size) {
                sum = 0.0d;
                return entry.getValue();
            } else time++;
        }
        return Blocks.AIR.getDefaultState();
    }

    public static List<Vec3d> randomVec3(int time, Random rand) {
        List<Vec3d> vec3dList = new ArrayList<>();
        for(int i = 0;i<=time;i++) {
            int u = rand.nextInt(100000);
            int v = rand.nextInt(100000);
            double theta = 2 * Math.PI * u;
            double phi = Math.acos(2 * v - 1);
            int x = (int) (Math.sin(theta) * Math.sin(phi));
            int y = (int) (Math.cos(theta) * Math.sin(phi));
            int z = (int) Math.abs( Math.cos(phi));
            vec3dList.add(new Vec3d(x,y,z));
        }
        return vec3dList;
    }

    public static int nextRandomInt() {
        return org.apache.commons.lang3.RandomUtils.nextInt();
    }

}

