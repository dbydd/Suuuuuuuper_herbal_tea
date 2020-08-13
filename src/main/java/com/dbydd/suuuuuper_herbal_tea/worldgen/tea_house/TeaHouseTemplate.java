package com.dbydd.suuuuuper_herbal_tea.worldgen.tea_house;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;

public class TeaHouseTemplate extends Template {

    @Override
    public boolean addBlocksToWorld(IWorld worldIn, BlockPos pos, PlacementSettings placementIn, int flags) {
        int height = worldIn.getHeight(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos.getX()+1, pos.getZ()+1);
        return super.addBlocksToWorld(worldIn, new BlockPos(pos.getX(), height, pos.getZ()), placementIn, flags);
    }
}
