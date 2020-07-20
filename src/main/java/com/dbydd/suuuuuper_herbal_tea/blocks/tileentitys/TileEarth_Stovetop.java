package com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys;

import com.dbydd.suuuuuper_herbal_tea.blocks.Earth_Stovetop;
import com.dbydd.suuuuuper_herbal_tea.items.Big_Black_Pot_Item;
import com.dbydd.suuuuuper_herbal_tea.interfaces.ITeaResource;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Items;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_TileEntities;
import com.dbydd.suuuuuper_herbal_tea.utils.IntegerContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEarth_Stovetop extends TileEntity implements ITickableTileEntity {
    private boolean hasPot = false;
    private boolean isburning = false;
    private boolean isCooking = false;
    private int burnTime = 0;
    private int maxBurnTime = 0;
    private ItemStackHandler fuel_ash_Handler = new ItemStackHandler(2);
    private IntegerContainer temperature = new IntegerContainer(0, 100);
    private IntegerContainer progress = new IntegerContainer(0, 200);
    private FluidTank tank = new FluidTank(2000);
    private IResourceItemHandler resources = new IResourceItemHandler(9);
    private IResourceItemHandler effects = new IResourceItemHandler(9);

    public TileEarth_Stovetop() {
        super(Registered_TileEntities.TILE_EARTH_STOVETOP_TYPE.get());
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (side == world.getBlockState(pos).get(Earth_Stovetop.FACING)) {
                return LazyOptional.of(() -> fuel_ash_Handler).cast();
            } else return LazyOptional.of(() -> resources).cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putBoolean("hasPot", hasPot);
        compound.put("fuel_and_ash", fuel_ash_Handler.serializeNBT());
        compound.putBoolean("isburning", isburning);
        compound.put("temperature", temperature.serializeNBT());
        compound.put("resources", resources.serializeNBT());
        compound.put("progress", progress.serializeNBT());
        compound.put("effects", effects.serializeNBT());
        compound.putInt("burntime", burnTime);
        compound.putInt("maxburntime", maxBurnTime);
        compound.putBoolean("iscooking", isCooking);
        tank.writeToNBT(compound);
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        this.hasPot = compound.getBoolean("haspot");
        this.fuel_ash_Handler.deserializeNBT(compound.getCompound("fuel_and_ash"));
        this.isburning = compound.getBoolean("isburning");
        this.temperature.deserializeNBT(compound.getCompound("temperature"));
        this.tank.readFromNBT(compound);
        this.resources.deserializeNBT(compound.getCompound("resources"));
        this.effects.deserializeNBT(compound.getCompound("effects"));
        this.progress.deserializeNBT(compound.getCompound("progress"));
        this.burnTime = compound.getInt("burntime");
        this.maxBurnTime = compound.getInt("maxburntime");
        this.isCooking = compound.getBoolean("iscooking");
        super.read(compound);
    }

    public CompoundNBT serializePotNBT() {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.put("resources", resources.serializeNBT());
        compoundNBT.put("tank", tank.writeToNBT(new CompoundNBT()));
        compoundNBT.put("effects", effects.serializeNBT());
        return compoundNBT;
    }

    public void onBlockActived(World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack heldItem = player.getHeldItem(handIn);
        if (heldItem.isEmpty()) {
            if (player.isSneaking()) {
                if (hasPot) {
                    ItemStack stack = new ItemStack(Registered_Items.BIG_BLACK_POT_ITEM);
                    stack.setTag(serializePotNBT());
                    ItemHandlerHelper.giveItemToPlayer(player, stack);
                    takeAwayPot();
                }
            } else {
                ItemHandlerHelper.giveItemToPlayer(player, fuel_ash_Handler.getStackInSlot(1));
                markDirty();
            }
        } else if (heldItem.getItem() instanceof Big_Black_Pot_Item) {
            if (!hasPot) {
                CompoundNBT compound = heldItem.getChildTag("BlockEntityTag");
                if (compound != null) {
                    this.resources.deserializeNBT(compound.getCompound("resources"));
                    this.tank.readFromNBT(compound.getCompound("tank"));
                    this.effects.deserializeNBT(compound.getCompound("effects"));
                }
                player.setHeldItem(handIn, ItemStack.EMPTY);
                hasPot = true;
                markDirty();
            }
        } else if (heldItem.getItem() instanceof FlintAndSteelItem) {
            this.isburning = true;
            this.takeFuel();
            heldItem.setDamage(1);
            markDirty();
        } else if (ForgeHooks.getBurnTime(heldItem) > 0) {
            if (fuel_ash_Handler.getStackInSlot(0).isEmpty() || fuel_ash_Handler.getStackInSlot(0).getItem() == heldItem.getItem()) {
                player.setHeldItem(handIn, fuel_ash_Handler.insertItem(0, heldItem, false));
                markDirty();
            }
        }

    }

    @Override
    public void tick() {
        if (isburning) {
            temperature.self_add();
            burnTime++;
            if (temperature.getCurrent() > 80 && tank.getFluidAmount()>=1000 && tank.getFluid().getFluid() == Fluids.WATER) {
                isCooking = true;
                progress.self_add();
                if (progress.atMaxValue()) {

                }
            }

            if (burnTime >= maxBurnTime && isCooking) {
                boolean b = takeFuel();
                if (!b) {
                    isCooking = false;
                    isburning = false;
                }
            }
            markDirty();
        } else {
            if (temperature.getCurrent() > world.getBiome(pos).getTemperature(pos)) {
                temperature.self_substract();
                markDirty();
            } else if (temperature.getCurrent() < world.getBiome(pos).getTemperature(pos)) {
                temperature.self_add();
                markDirty();
            }
        }
    }

    private boolean takeFuel() {
        ItemStack stackInSlot = fuel_ash_Handler.getStackInSlot(0);
        if (stackInSlot.isEmpty()) {
            return false;
        } else {
            Item item = stackInSlot.getItem();
            int burnTime = ForgeHooks.getBurnTime(new ItemStack(item));
            if(burnTime != 0) {
                fuel_ash_Handler.extractItem(0, 1, false);
                this.maxBurnTime = burnTime;
                this.burnTime = 0;
                this.isburning = true;
                markDirty();
                return true;
            }
        }
        return false;
    }

    private void finishCook(){
        float waterAmount = tank.getFluidAmount();
        float tankCapacity = tank.getCapacity();
        int value = Math.round(waterAmount/tankCapacity);
        int slots = this.resources.getSlots();
        for(int i = 0;i<slots;i++){
            ItemStack stackInSlot = resources.getStackInSlot(i);
            int count = stackInSlot.getCount()/value;
            Item item = stackInSlot.getItem();
            effects.setStackInSlot(i,new ItemStack(item, count));
        }
        this.resources = new IResourceItemHandler(9);
    }

    public void takeAwayPot() {
        this.hasPot = false;
        this.effects = new IResourceItemHandler(9);
        this.resources = new IResourceItemHandler(9);
        this.tank = new FluidTank(2000);
        markDirty();
    }

    public boolean hasPot() {
        return hasPot;
    }

    public FluidTank getTank() {
        return tank;
    }
}

class IResourceItemHandler extends ItemStackHandler {
    public IResourceItemHandler(int i) {
        super(i);
    }

    public IResourceItemHandler(NonNullList<ItemStack> stacks) {
        super(stacks);
    }

    public IResourceItemHandler() {
        super();
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if (stack.getItem() instanceof ITeaResource) {
            return super.insertItem(slot, stack, simulate);
        }
        return stack;
    }
}
