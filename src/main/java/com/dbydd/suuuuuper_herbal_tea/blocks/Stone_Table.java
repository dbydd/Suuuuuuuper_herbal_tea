package com.dbydd.suuuuuper_herbal_tea.blocks;

import com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys.TileStone_Table;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.RenderTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class Stone_Table extends Block {
    public static final String name = "stone_table";
    public static final VoxelShape shape = Block.makeCuboidShape(0, 0, 0, 16, 11, 16);
//todo finish it
    public Stone_Table() {
        super(Properties.create(Material.IRON).hardnessAndResistance(4.5f).harvestLevel(2).harvestTool(ToolType.PICKAXE).notSolid());
        RenderTypes.blockRenderTypeMap.put(this, RenderType.getTranslucent());
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return shape;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return shape;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        return ((TileStone_Table)worldIn.getTileEntity(pos)).onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileStone_Table();
    }
}
