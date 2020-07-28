package com.dbydd.suuuuuper_herbal_tea.blocks;

import com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys.TileBig_Black_Pot;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Items;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
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
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;

public class Big_Black_Pot extends Block {
    public static final String name = "big_black_pot";
    public static final VoxelShape shape = Block.makeCuboidShape(0, 0, 0, 16, 5, 16);

    public Big_Black_Pot() {
        super(Properties.create(Material.IRON).hardnessAndResistance(4.5f).harvestLevel(2).harvestTool(ToolType.PICKAXE).notSolid());
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
        if (te instanceof TileBig_Black_Pot) {
            TileBig_Black_Pot pot = (TileBig_Black_Pot) te;
            if (!worldIn.isRemote) {
                CompoundNBT nbt = pot.write(new CompoundNBT());
                ItemStack itemStack = new ItemStack(Registered_Items.BIG_BLACK_POT_ITEM);
                if (!nbt.isEmpty()) {
                    itemStack.setTagInfo("BlockEntityTag", nbt);
                }
                ItemHandlerHelper.giveItemToPlayer(player, itemStack);
            }
        }

        super.harvestBlock(worldIn, player, pos, state, te, stack);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        return ((TileBig_Black_Pot)worldIn.getTileEntity(pos)).onBlockActived(state, worldIn, pos, player, handIn, hit);
    }
}
