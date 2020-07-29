package com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys;

import com.dbydd.suuuuuper_herbal_tea.items.BigSpoon;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_TileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileBig_Black_Pot extends TileEntity {
    private FluidTank tank = new FluidTank(2000);
    private ItemStackHandler resources = new ItemStackHandler(9);
    private ItemStackHandler effects = new ItemStackHandler(9);

    public TileBig_Black_Pot() {
        super(Registered_TileEntities.TilE_BIG_BLACK_POT_TYPE.get());
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return LazyOptional.of(() -> tank).cast();
        }
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return LazyOptional.of(() -> resources).cast();
        }
        return super.getCapability(cap);
    }

    @Override
    public void read(CompoundNBT compound) {
        tank.readFromNBT(compound.getCompound("tank"));
        resources.deserializeNBT(compound.getCompound("resources"));
        effects.deserializeNBT(compound.getCompound("effects"));
        super.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("tank", tank.writeToNBT(new CompoundNBT()));
        compound.put("resources", resources.serializeNBT());
        compound.put("effects",effects.serializeNBT());
        return super.write(compound);
    }

    public CompoundNBT serializeSpoonTag() {
        CompoundNBT compoundNBT = new CompoundNBT();
        FluidTank spoonTank = new FluidTank(200);
        spoonTank.setFluid(tank.drain(200, IFluidHandler.FluidAction.EXECUTE));

        markDirty2();

        compoundNBT.put("tank", spoonTank.writeToNBT(new CompoundNBT()));
        compoundNBT.put("effects", effects.serializeNBT());
        compoundNBT.putBoolean("isempty", false);
        return compoundNBT;
    }

    public ActionResultType onBlockActived(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack heldItem = player.getHeldItem(handIn);
        if(heldItem.getItem() instanceof BigSpoon){
            if (heldItem.getChildTag("spoonresources") != null || heldItem.getChildTag("spoonresources").getBoolean("isempty")) {
                heldItem.setTagInfo("spoonresources", serializeSpoonTag());
                player.setHeldItem(handIn, heldItem);
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.PASS;
    }

    public FluidTank getTank() {
        return tank;
    }

    public ItemStackHandler getResources() {
        return resources;
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(pos, 1, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        handleUpdateTag(pkt.getNbtCompound());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }

    @Override
    public void handleUpdateTag(CompoundNBT tag) {
        this.read(tag);
    }

    public void markDirty2() {
        world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 2);
        super.markDirty();
    }
}
