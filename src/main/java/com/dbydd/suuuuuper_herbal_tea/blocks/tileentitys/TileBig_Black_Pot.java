package com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys;

import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_TileEntities;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

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

    public FluidStack scooped(int amount, IFluidHandler.FluidAction action) {
        FluidStack drain = tank.drain(amount, action);
        markDirty();
        return drain;
    }
}
