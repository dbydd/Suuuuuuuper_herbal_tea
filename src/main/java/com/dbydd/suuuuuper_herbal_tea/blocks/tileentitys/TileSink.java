package com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys;

import com.dbydd.suuuuuper_herbal_tea.registeried_lists.RegisteredTileEntities;
import net.minecraft.fluid.Fluids;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileSink extends TileEntity implements IFluidTank {
    public TileSink() {
        super(RegisteredTileEntities.TILE_SINK.get());
    }


    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        if(cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY){
            return LazyOptional.of(()->this).cast();
        }
        return LazyOptional.empty();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return getCapability(cap);
    }

    @Nonnull
    @Override
    public FluidStack getFluid() {
        return new FluidStack(Fluids.WATER, Integer.MAX_VALUE);
    }

    @Override
    public int getFluidAmount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getCapacity() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isFluidValid(FluidStack stack) {
        return stack.getFluid() == Fluids.WATER;
    }

    @Override
    public int fill(FluidStack resource, IFluidHandler.FluidAction action) {
        return resource.getAmount();
    }

    @Nonnull
    @Override
    public FluidStack drain(int maxDrain, IFluidHandler.FluidAction action) {
        return new FluidStack(Fluids.WATER, maxDrain);
    }

    @Nonnull
    @Override
    public FluidStack drain(FluidStack resource, IFluidHandler.FluidAction action) {
        if (resource.getFluid() == Fluids.WATER) {
            return resource;
        } else {
            return FluidStack.EMPTY;
        }
    }
}
