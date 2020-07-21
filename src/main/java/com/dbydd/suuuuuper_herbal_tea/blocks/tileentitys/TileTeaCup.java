package com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys;

import com.dbydd.suuuuuper_herbal_tea.interfaces.IPutableItem;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Items;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_TileEntities;
import com.dbydd.suuuuuper_herbal_tea.utils.IResourceItemHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.ItemHandlerHelper;

public class TileTeaCup extends TileEntity {
    private IResourceItemHandler handler = new IResourceItemHandler(9);
    private FluidTank tank = new FluidTank(200);


    public TileTeaCup() {
        super(Registered_TileEntities.TEA_CUP.get());
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("effects", handler.serializeNBT());
        compound.put("tank", tank.writeToNBT(new CompoundNBT()));
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        handler.deserializeNBT(compound.getCompound("resources"));
        tank.readFromNBT(compound.getCompound("tank"));
        super.read(compound);
    }

    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            ItemStack heldItem = player.getHeldItem(handIn);
            if (heldItem == ItemStack.EMPTY && player.isSneaking()) {
                ItemStack itemStack = new ItemStack(Registered_Items.TEA_CUP);
                itemStack.setTagInfo("BlockEntityTag", this.write(new CompoundNBT()));
                ItemHandlerHelper.giveItemToPlayer(player, itemStack);
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        }
        return ActionResultType.PASS;
    }

    public boolean canfill() {
        return tank.getFluidAmount() == 0;
    }

    public void fill(IResourceItemHandler resources, FluidStack fluidStack){
        handler = resources;
        tank.setFluid(fluidStack);
        markDirty();
    }
}
