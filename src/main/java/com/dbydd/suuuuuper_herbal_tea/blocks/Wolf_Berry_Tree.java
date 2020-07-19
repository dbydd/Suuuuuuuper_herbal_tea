package com.dbydd.suuuuuper_herbal_tea.blocks;

import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Items;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.Random;

public class Wolf_Berry_Tree extends BlockBase implements IGrowable {
    protected static final IntegerProperty GROW_TIER = IntegerProperty.create("grow_tier", 0, 5);
    protected static final Properties default_properties = Properties.create(Material.PLANTS).hardnessAndResistance(3.0f).tickRandomly().notSolid().doesNotBlockMovement().sound(SoundType.PLANT);

    public Wolf_Berry_Tree(Properties properties, String name, RenderType renderType) {
        super(properties, name, renderType);
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
            ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Registered_Items.WOLFBERRY, worldIn.getRandom().nextInt(6)));
            worldIn.setBlockState(pos, state.with(GROW_TIER, state.get(GROW_TIER) - 1));
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        if (rand.nextFloat() <= 0.35 && state.get(GROW_TIER) < 8)
            worldIn.setBlockState(pos, state.with(GROW_TIER, state.get(GROW_TIER) + 1));
    }
}
