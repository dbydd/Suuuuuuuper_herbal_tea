package com.dbydd.suuuuuper_herbal_tea.blocks;

import com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys.TileStoneTable;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.RegisteredBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class Stone_Table extends BlockBase {
    public static final String name = "stone_table";
//todo tesr
    public Stone_Table() {
        super(Properties.create(Material.IRON).hardnessAndResistance(4.5f).harvestLevel(2).harvestTool(ToolType.PICKAXE).notSolid(), name);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        return ((TileStoneTable)worldIn.getTileEntity(pos)).onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileStoneTable();
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        if(!worldIn.isRemote()) {
            ItemEntity itemEntity = new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(RegisteredBlocks.STONE_TABLE));
            worldIn.addEntity(itemEntity);
        }
        super.onBlockHarvested(worldIn, pos, state, player);
    }
}
