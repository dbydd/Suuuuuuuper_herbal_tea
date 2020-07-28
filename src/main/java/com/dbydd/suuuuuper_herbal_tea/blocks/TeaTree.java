package com.dbydd.suuuuuper_herbal_tea.blocks;

import com.dbydd.suuuuuper_herbal_tea.items.Tea_Leaves;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Biomes;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Blocks;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Items;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.entity.item.ItemEntity;
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
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.*;

public class TeaTree extends BlockBase implements IGrowable {
    public static final IntegerProperty GROW_TIER = IntegerProperty.create("grow_tier", 0, 5);
    protected static final Properties default_properties = Properties.create(Material.PLANTS).hardnessAndResistance(3.0f).tickRandomly().notSolid().doesNotBlockMovement().sound(SoundType.PLANT);

    public TeaTree() {
        super(default_properties, "tea_tree", RenderType.getCutoutMipped());
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
//        float wet_value = (float) Math.sqrt((biomeIn.getDownfall() * biomeIn.getTemperature(pos)) / 2) / 2;  //useless
        if (rand.nextFloat() <= 0.35 && state.get(GROW_TIER) < 5)
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

                int count = worldIn.rand.nextInt(5);
                Biome biome = worldIn.getBiome(pos);
                if (Arrays.asList(Biomes.OCEAN, Biomes.COLD_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.DEEP_FROZEN_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, Biomes.DEEP_OCEAN, Biomes.DEEP_WARM_OCEAN, Biomes.FROZEN_OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.WARM_OCEAN, Biomes.BEACH).contains(biome)) {
                    ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Registered_Items.OCEAN_TEA_LEAVE, count));
                } else if (Arrays.asList(Biomes.SNOWY_TAIGA, Biomes.SNOWY_TUNDRA, Biomes.SNOWY_BEACH, Biomes.SNOWY_MOUNTAINS, Biomes.SNOWY_TAIGA_HILLS).contains(biome)) {
                    ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Registered_Items.SNOW_TEA_LEAVE, count));
                } else if (Arrays.asList(Biomes.MOUNTAIN_EDGE, Biomes.MOUNTAINS, Biomes.SNOWY_MOUNTAINS, Biomes.GRAVELLY_MOUNTAINS, Biomes.MODIFIED_GRAVELLY_MOUNTAINS, Biomes.SNOWY_TAIGA_MOUNTAINS, Biomes.TAIGA_MOUNTAINS, Biomes.WOODED_MOUNTAINS).contains(biome)) {
                    ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Registered_Items.MOUNTAIN_TEA_LEAVE, count));
                } else if (Arrays.asList(Biomes.PLAINS, Biomes.SUNFLOWER_PLAINS).contains(biome)) {
                    ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Registered_Items.PLAIN_TEA_LEAVE, count));
                } else if (Arrays.asList(Biomes.JUNGLE, Biomes.JUNGLE_EDGE, Biomes.JUNGLE_HILLS, Biomes.BAMBOO_JUNGLE, Biomes.BAMBOO_JUNGLE_HILLS, Biomes.MODIFIED_JUNGLE, Biomes.MODIFIED_JUNGLE_EDGE).contains(biome)) {
                    ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Registered_Items.JUNGLE_TEA_LEAVE, count));
                } else if (Arrays.asList(Biomes.DESERT, Biomes.DESERT_HILLS, Biomes.DESERT_LAKES).contains(biome)) {
                    ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Registered_Items.DESERT_TEA_LEAVE, count));
                } else if (Arrays.asList(Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU, Biomes.SHATTERED_SAVANNA, Biomes.SHATTERED_SAVANNA_PLATEAU).contains(biome)) {
                    ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Registered_Items.TROPICAL_TEA_LEAVE, count));
                } else if (Arrays.asList(Biomes.BADLANDS, Biomes.BADLANDS_PLATEAU, Biomes.ERODED_BADLANDS, Biomes.MODIFIED_BADLANDS_PLATEAU, Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU, Biomes.WOODED_BADLANDS_PLATEAU).contains(biome)) {
                    ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Registered_Items.CLAY_TEA_LEAVE, count));
                } else if (biome == Registered_Biomes.WORLD_TEA_TREE_BIOME.get()) {
                    ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Registered_Items.WORLD_TEA_TREE_TEA_LEAVE, count));
                } else {
                    ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Registered_Items.PLAIN_TEA_LEAVE, count));
                }
                worldIn.setBlockState(pos, state.with(GROW_TIER, state.get(GROW_TIER) - 1));
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if(worldIn.getBlockState(new BlockPos(pos.getX(), pos.getY()-1,pos.getZ())).getBlock() == Blocks.AIR){
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
        super.randomTick(state, worldIn, pos, random);
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        if(!worldIn.isRemote()) {
            ItemEntity itemEntity = new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Registered_Blocks.TEA_TREE));
            worldIn.addEntity(itemEntity);
        }
        super.onBlockHarvested(worldIn, pos, state, player);
    }
}
