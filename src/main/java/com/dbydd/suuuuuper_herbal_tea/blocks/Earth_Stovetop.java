package com.dbydd.suuuuuper_herbal_tea.blocks;

import com.dbydd.suuuuuper_herbal_tea.Suuuuuuuper_herbal_tea;
import com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys.TileEarth_Stovetop;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nullable;

public class Earth_Stovetop extends BlockBase {
    public static final String name = "earth_stovetop";
    public static final RegistryObject<TileEntityType<TileEarth_Stovetop>> registryObject = (RegistryObject<TileEntityType<TileEarth_Stovetop>>) Suuuuuuuper_herbal_tea.RegistryTileentityType(name, TileEntityType.Builder.create(TileEarth_Stovetop::new, Registered_Block.EARTH_STOVETOP).build(null));

    public Earth_Stovetop() {
        super(Properties.create(Material.ROCK).doesNotBlockMovement().notSolid().hardnessAndResistance(3, 1.5f).harvestLevel(1).harvestTool(ToolType.PICKAXE), name, RenderType.getTranslucent());
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
        ((TileEarth_Stovetop)worldIn.getTileEntity(pos)).onBlockActived( worldIn,pos, player, handIn, hit);
        return ActionResultType.SUCCESS;
    }
}
