package com.dbydd.suuuuuper_herbal_tea.blocks;

import com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys.TileEarth_Stovetop;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.BlockRenderTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class Earth_Stovetop extends BlockBase {
    public static final BooleanProperty HAS_FIRE_WOOD = BooleanProperty.create("has_fire_wood");

    public Earth_Stovetop() {
        super(Properties.create(Material.ROCK).notSolid().hardnessAndResistance(3, 1.5f).harvestLevel(1).harvestTool(ToolType.PICKAXE), "earth_stovetop", RenderType.getTranslucent());
        BlockRenderTypes.blockRenderTypeMap.put(this, RenderType.getTranslucent());
        this.setDefaultState(this.stateContainer.getBaseState().with(HAS_FIRE_WOOD, false));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HAS_FIRE_WOOD);
        super.fillStateContainer(builder);
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
}
