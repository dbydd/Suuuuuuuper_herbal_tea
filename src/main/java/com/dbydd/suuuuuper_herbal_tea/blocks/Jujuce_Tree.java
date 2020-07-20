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
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.items.ItemHandlerHelper;
import org.lwjgl.system.CallbackI;

import java.util.Random;

public class Jujuce_Tree extends BlockBase implements IGrowable {

    protected static final IntegerProperty GROW_TIER = IntegerProperty.create("grow_tier", 0, 5);
    protected static final Properties default_properties = Properties.create(Material.PLANTS).hardnessAndResistance(3.0f).tickRandomly().notSolid().doesNotBlockMovement().sound(SoundType.PLANT);

    public Jujuce_Tree() {
        super(default_properties, "jujuce_tree", RenderType.getTranslucent());
        this.setDefaultState(this.stateContainer.getBaseState().with(GROW_TIER, 0));
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
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        super.tick(state, worldIn, pos, rand);
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
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        if (rand.nextFloat() <= 0.35 && state.get(GROW_TIER) < 5)
            worldIn.setBlockState(pos, state.with(GROW_TIER, state.get(GROW_TIER) + 1));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        Integer tier = state.get(GROW_TIER);
        if (tier > 3) {
            Random random = worldIn.getRandom();
            int count = random.nextInt(4);
            if (tier > 4) {
                ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Registered_Items.REDDATE, count));
            } else {
                ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Registered_Items.GREENDATE, count));
            }
            worldIn.setBlockState(pos, state.with(GROW_TIER, state.get(GROW_TIER) - 1));
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }
}
