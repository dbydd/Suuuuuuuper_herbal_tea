package com.dbydd.suuuuuper_herbal_tea.blocks;

import com.dbydd.suuuuuper_herbal_tea.registeried_lists.RenderTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class HoneySuckle_Block extends BushBlock {
    public HoneySuckle_Block(Properties properties) {
        super(properties.tickRandomly());
        RenderTypes.blockRenderTypeMap.put(this, RenderType.getTranslucent());
    }
    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if(worldIn.getBlockState(new BlockPos(pos.getX(), pos.getY()-1,pos.getZ())).getBlock() == Blocks.AIR){
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
        super.randomTick(state, worldIn, pos, random);
    }
}
