package com.dbydd.suuuuuper_herbal_tea.blocks;

import com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys.TileEarth_Stovetop;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.RenderTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.List;

public class Earth_Stovetop extends BlockBase {
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static final VoxelShape shape_withpot = Block.makeCuboidShape(0, 0, 0, 16, 14, 16);
    public static final VoxelShape shape = Block.makeCuboidShape(0, 0, 0, 16, 9, 16);

    public Earth_Stovetop() {
        super(Properties.create(Material.ROCK).notSolid().hardnessAndResistance(3, 1.5f).harvestLevel(1).harvestTool(ToolType.PICKAXE), "earth_stovetop", RenderType.getTranslucent());
        RenderTypes.blockRenderTypeMap.put(this, RenderType.getTranslucent());
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if(tileEntity != null)
            return ((TileEarth_Stovetop)tileEntity).hasBlackPot()?shape_withpot:shape;
        return shape;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if(tileEntity != null)
        return ((TileEarth_Stovetop)tileEntity).hasBlackPot()?shape_withpot:shape;
        return shape;
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if(tileEntity != null)
            return ((TileEarth_Stovetop)tileEntity).isIsburning()?12:0;
        return 0;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        super.fillStateContainer(builder);
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        if(!worldIn.isRemote()) {
            ItemStackHandler fuel_ash_handler = ((TileEarth_Stovetop) worldIn.getTileEntity(pos)).getFuel_ash_Handler();
            ItemHandlerHelper.giveItemToPlayer(player,fuel_ash_handler.extractItem(0,fuel_ash_handler.getStackInSlot(0).getCount(), false));
        }
        super.onBlockHarvested(worldIn, pos, state, player);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.with(FACING, Direction.fromAngle(placer.getHorizontalFacing().getHorizontalAngle()-90)));
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEarth_Stovetop();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ((TileEarth_Stovetop) worldIn.getTileEntity(pos)).onBlockActived(worldIn, pos, player, handIn, hit);
        return ActionResultType.SUCCESS;

    }

    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("earth_stovetop_tooltip"));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
