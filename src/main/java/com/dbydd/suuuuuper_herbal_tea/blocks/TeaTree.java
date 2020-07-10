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

import java.util.Random;

public class TeaTree extends BlockBase implements IGrowable {
    protected static final IntegerProperty GROW_TIER = IntegerProperty.create("grow_tier", 0, 5);
    protected static final Properties default_properties = Properties.create(Material.PLANTS).hardnessAndResistance(3.0f).tickRandomly().notSolid().doesNotBlockMovement().sound(SoundType.GLASS);

    public TeaTree(String name) {
        super(default_properties, name, RenderType.getCutoutMipped());
        this.setDefaultState(this.stateContainer.getBaseState().with(GROW_TIER, 0));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(GROW_TIER);
        super.fillStateContainer(builder);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return Block.makeCuboidShape(0, 0, 0, 16, 24, 16);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return !isClient || state.get(GROW_TIER) < 5;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        Biome biomeIn = worldIn.getBiome(pos);
        float wet_value = (float) Math.sqrt(biomeIn.getDownfall() * biomeIn.getTemperature(pos));
        if (rand.nextFloat() <= wet_value && state.get(GROW_TIER) < 5)
            worldIn.setBlockState(pos, state.with(GROW_TIER, state.get(GROW_TIER) + 1));
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        grow(worldIn, rand, pos, state);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            if (state.get(GROW_TIER) > 2) {
                ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Registered_Items.TEA_LEAVE, worldIn.rand.nextInt(5)));
                worldIn.setBlockState(pos, state.with(GROW_TIER, state.get(GROW_TIER) - 1));
            }
        }
        return ActionResultType.SUCCESS;
    }
}
