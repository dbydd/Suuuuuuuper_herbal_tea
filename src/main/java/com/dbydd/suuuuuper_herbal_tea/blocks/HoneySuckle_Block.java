package com.dbydd.suuuuuper_herbal_tea.blocks;

import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Items;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class HoneySuckle_Block extends BushBlock {
    public HoneySuckle_Block(Properties properties) {
        super(properties.tickRandomly());
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        BlockState blockState = worldIn.getBlockState(pos.offset(Direction.DOWN));
        if (blockState.isAir(worldIn, pos) || blockState.getBlock() instanceof FlowingFluidBlock) {
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
        super.randomTick(state, worldIn, pos, random);
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!worldIn.isRemote()) {
            ItemEntity itemEntity = new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Registered_Items.HONEY_SUCKLE_ITEM));
            worldIn.addEntity(itemEntity);
        }
        super.onBlockHarvested(worldIn, pos, state, player);
    }

    @Override
    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        BlockPos blockPos = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
        BlockState blockState = worldIn.getBlockState(blockPos);
        if(blockState.isAir(worldIn, blockPos) || blockState.getBlock() instanceof FlowingFluidBlock || blockState.getBlock() instanceof BushBlock) {
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
        super.onBlockAdded(state, worldIn, pos, oldState, isMoving);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockState blockState = worldIn.getBlockState(pos.offset(Direction.DOWN));
        return !(blockState.isAir(worldIn, pos) || blockState.getBlock() instanceof FlowingFluidBlock || blockState.getBlock() instanceof BushBlock);
    }

    @Override
    public void onNeighborChange(BlockState state, IWorldReader world, BlockPos pos, BlockPos neighbor) {
        if(!world.isRemote()) {
            if (world.getBlockState(pos.offset(Direction.DOWN)).isAir(world, pos.offset(Direction.DOWN))) {
                if (world instanceof World) {
                    ((World) world).setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                }
            }
        }
    }
}
