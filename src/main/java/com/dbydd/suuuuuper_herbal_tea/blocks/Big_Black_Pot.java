package com.dbydd.suuuuuper_herbal_tea.blocks;

import com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys.TileBig_Black_Pot;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.BlockRenderTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class Big_Black_Pot extends Block {
    public static final String name = "big_black_pot";
    public static final VoxelShape shape = Block.makeCuboidShape(0, 0, 0, 16, 8, 16);

    public Big_Black_Pot() {
        super(Properties.create(Material.IRON).hardnessAndResistance(4.5f).harvestLevel(2).harvestTool(ToolType.PICKAXE));
        BlockRenderTypes.blockRenderTypeMap.put(this, RenderType.getTranslucent());
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
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileBig_Black_Pot();
    }

    @Override
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof TileBig_Black_Pot) {
            TileBig_Black_Pot pot = (TileBig_Black_Pot) tileEntity;
            if (!worldIn.isRemote) {
                CompoundNBT nbt = pot.write(new CompoundNBT());
                if (!nbt.isEmpty()) {
                    stack.setTagInfo("BlockEntityTag", nbt);
                }
            }
        }

        super.harvestBlock(worldIn, player, pos, state, te, stack);
    }
}
