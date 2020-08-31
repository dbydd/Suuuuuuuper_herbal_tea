package com.dbydd.suuuuuper_herbal_tea.blocks;

import com.dbydd.suuuuuper_herbal_tea.registeried_lists.RegisteredBlocks;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.RegisteredItems;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.Random;

public class WolfBerryTree extends BlockBase implements IGrowable {
    public static final IntegerProperty GROW_TIER = IntegerProperty.create("grow_tier", 0, 5);
    protected static final Properties default_properties = Properties.create(Material.PLANTS).hardnessAndResistance(3.0f).tickRandomly().notSolid().doesNotBlockMovement().sound(SoundType.PLANT);

    public WolfBerryTree() {
        super(default_properties, "wolf_berry_tree");
        this.setDefaultState(this.stateContainer.getBaseState().with(GROW_TIER, 0));
    }

    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(GROW_TIER);
        super.fillStateContainer(builder);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        int tier = state.get(GROW_TIER);
        VoxelShape voxelShape;
        switch (tier) {
            case 0:
                voxelShape = Block.makeCuboidShape(0, 0, 0, 16, 12, 16);
                break;
            case 1:
                voxelShape = Block.makeCuboidShape(0, 0, 0, 16, 19, 16);
                break;
            default:
                voxelShape = Block.makeCuboidShape(0, 0, 0, 16, 24, 16);
                break;
        }
        return voxelShape;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        Integer tier = state.get(GROW_TIER);
        if (tier > 4) {
            ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(RegisteredItems.WOLFBERRY, worldIn.getRandom().nextInt(6)));
            worldIn.setBlockState(pos, state.with(GROW_TIER, state.get(GROW_TIER) - 1));
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        if (rand.nextFloat() <= 0.35 && state.get(GROW_TIER) < 5)
            worldIn.setBlockState(pos, state.with(GROW_TIER, state.get(GROW_TIER) + 1));
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
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        grow(worldIn, rand, pos, state);
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        if(!worldIn.isRemote()) {
            ItemEntity itemEntity = new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(RegisteredBlocks.WOLF_BERRY_TREE));
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
