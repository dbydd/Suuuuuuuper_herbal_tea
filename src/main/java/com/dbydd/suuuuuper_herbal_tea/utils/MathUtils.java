package com.dbydd.suuuuuper_herbal_tea.utils;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class MathUtils {
    public static boolean inCircle(int x, int y, double range) {
        return Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(range, 2);
    }


    public static class lineFunction3d {
        private final BlockPos m0;
        private final BlockPos target;

        public lineFunction3d(BlockPos m0, BlockPos target) {
            this.m0 = m0;
            this.target = target;
        }

        public boolean isInLine(BlockPos pos) {
            return ((pos.getX() - m0.getX()) / target.getX()) == ((pos.getY() - m0.getY()) / target.getY()) &&
                    ((pos.getX() - m0.getX()) / target.getX()) == ((pos.getZ() - m0.getZ()) / target.getZ()) &&
                    ((pos.getY() - m0.getY()) / target.getY()) == ((pos.getZ() - m0.getZ()) / target.getZ());
        }

        public Vec3d getTargetVec() {
            return new Vec3d(m0.getX() - target.getX(), m0.getY() - target.getY(), m0.getZ() - target.getZ());
        }

    }

}
