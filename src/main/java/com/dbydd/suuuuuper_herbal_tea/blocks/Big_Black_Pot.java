package com.dbydd.suuuuuper_herbal_tea.blocks;

import com.dbydd.suuuuuper_herbal_tea.Suuuuuuuper_herbal_tea;
import com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys.TileBig_Black_Pot;
import com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys.TileEarth_Stovetop;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.BlockRenderTypes;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Block;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nullable;

public class Big_Black_Pot extends Block {
    public static final String name = "big_black_pot";
    public static final RegistryObject<TileEntityType<TileBig_Black_Pot>> registryObject = (RegistryObject<TileEntityType<TileBig_Black_Pot>>) Suuuuuuuper_herbal_tea.RegistryTileentityType(name, TileEntityType.Builder.create(TileBig_Black_Pot::new, Registered_Block.BIG_BLACK_POT).build(null));

    public Big_Black_Pot() {
        super(Properties.create(Material.IRON).hardnessAndResistance(4.5f).harvestLevel(2).harvestTool(ToolType.PICKAXE));
        BlockRenderTypes.blockRenderTypeMap.put(this, RenderType.getTranslucent());
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
        if(tileEntity instanceof TileBig_Black_Pot){
            TileBig_Black_Pot pot = (TileBig_Black_Pot) tileEntity;
            if(!worldIn.isRemote){
                CompoundNBT nbt = pot.write(new CompoundNBT());
                if (!nbt.isEmpty()) {
                    stack.setTagInfo("BlockEntityTag", nbt);
                }
            }
        }

        super.harvestBlock(worldIn, player, pos, state, te, stack);
    }
}
