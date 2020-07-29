package com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys;

import com.dbydd.suuuuuper_herbal_tea.blocks.Earth_Stovetop;
import com.dbydd.suuuuuper_herbal_tea.items.BigSpoon;
import com.dbydd.suuuuuper_herbal_tea.items.Big_Black_Pot_Item;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Fluids;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Items;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_TileEntities;
import com.dbydd.suuuuuper_herbal_tea.utils.IResourceItemHandler;
import com.dbydd.suuuuuper_herbal_tea.utils.IntegerContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEarth_Stovetop extends TileEntity implements ITickableTileEntity {
    private boolean hasBlackPot = false;
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

    public boolean isCooking() {
        return isCooking;
    }

    public boolean isIsburning() {
        return isburning;
    }

    public ItemStackHandler getFuel_ash_Handler() {
        return fuel_ash_Handler;
    }

    public IResourceItemHandler getResources() {
        return resources;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (side == world.getBlockState(pos).get(Earth_Stovetop.FACING)) {
                return LazyOptional.of(() -> fuel_ash_Handler).cast();
            } else {
                return LazyOptional.of(() -> resources).cast();
            }
        }
        return super.getCapability(cap, side);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putBoolean("hasBlackPot", hasBlackPot);
        compound.put("fuel_and_ash", fuel_ash_Handler.serializeNBT());
        compound.putBoolean("isburning", isburning);
        compound.put("temperature", temperature.serializeNBT());
        compound.put("resources", resources.serializeNBT());
        compound.put("progress", progress.serializeNBT());
        compound.put("effects", effects.serializeNBT());
        compound.putInt("burntime", burnTime);
        compound.putInt("maxburntime", maxBurnTime);
        compound.putBoolean("iscooking", isCooking);
        compound.put("tank", tank.writeToNBT(new CompoundNBT()));
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        this.hasBlackPot = compound.getBoolean("hasBlackPot");
        this.fuel_ash_Handler.deserializeNBT(compound.getCompound("fuel_and_ash"));
        this.isburning = compound.getBoolean("isburning");
        this.temperature.deserializeNBT(compound.getCompound("temperature"));
        this.tank.readFromNBT(compound.getCompound("tank"));
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
        CompoundNBT nbt2 = new CompoundNBT();
        nbt2.put("resources", resources.serializeNBT());
        nbt2.put("tank", tank.writeToNBT(new CompoundNBT()));
        nbt2.put("effects", effects.serializeNBT());
        compoundNBT.put("BlockEntityTag", nbt2);
        return compoundNBT;
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

    public void onBlockActived(World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            ItemStack heldItem = player.getHeldItem(handIn);
            if (heldItem.isEmpty()) {
                if (player.isSneaking()) {
                    if (hasBlackPot) {
                        boolean flag = true;
                        for (int i = 0; i < resources.getSlots(); i++) {
                            ItemStack stackInSlot = resources.getStackInSlot(i);
                            if (!stackInSlot.isEmpty()) {
                                ItemHandlerHelper.giveItemToPlayer(player, resources.extractItem(i, stackInSlot.getCount(), false));
                                markDirty2();
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            ItemStack stack = new ItemStack(Registered_Items.BIG_BLACK_POT_ITEM);
                            stack.setTag(serializePotNBT());
                            ItemHandlerHelper.giveItemToPlayer(player, stack);
                            takeAwayPot();
                        }
                    }
                } else {
                    ItemHandlerHelper.giveItemToPlayer(player, fuel_ash_Handler.extractItem(0, fuel_ash_Handler.getStackInSlot(0).getCount(), false));
                    markDirty2();
                }
            } else if (heldItem.getItem() instanceof Big_Black_Pot_Item) {
                if (!hasBlackPot) {
                    CompoundNBT compound = heldItem.getChildTag("BlockEntityTag");
                    if (compound != null) {
                        this.resources.deserializeNBT(compound.getCompound("resources"));
                        this.tank.readFromNBT(compound.getCompound("tank"));
                        this.effects.deserializeNBT(compound.getCompound("effects"));
                    }
                    player.setHeldItem(handIn, ItemStack.EMPTY);
                    hasBlackPot = true;
                    markDirty2();
                }
            } else if (heldItem.getItem() instanceof BucketItem) {
                BucketItem bucket = (BucketItem) heldItem.getItem();
                Fluid fluid = bucket.getFluid();
                if ((tank.getFluid().getFluid() == Fluids.WATER.getFluid() || tank.isEmpty()) && hasBlackPot) {
                    tank.fill(new FluidStack(fluid, 1000), IFluidHandler.FluidAction.EXECUTE);
                    ItemStack result = !player.abilities.isCreativeMode ? new ItemStack(Items.BUCKET) : heldItem;
                    player.setHeldItem(handIn, result);
                    markDirty2();
                }
            } else if (heldItem.getItem() instanceof FlintAndSteelItem) {
                this.isburning = true;
                this.takeFuel();
                heldItem.setDamage(1);
                markDirty2();
            } else if (ForgeHooks.getBurnTime(heldItem) > 0) {
                if (fuel_ash_Handler.getStackInSlot(0).isEmpty() || fuel_ash_Handler.getStackInSlot(0).getItem() == heldItem.getItem()) {
                    player.setHeldItem(handIn, fuel_ash_Handler.insertItem(0, heldItem, false));
                    markDirty2();
                }
            } else if (heldItem.getItem() instanceof BigSpoon) {
                if (heldItem.getChildTag("spoonresources") == null || heldItem.getChildTag("spoonresources").getBoolean("isempty")) {
                    heldItem.setTagInfo("spoonresources", serializeSpoonTag());
                    player.setHeldItem(handIn, heldItem);
                }
            } else {
                if (hasBlackPot) {
                    ItemStack itemStack = ItemHandlerHelper.insertItem(resources, heldItem, false);
                    player.setHeldItem(handIn, itemStack);
                    markDirty2();
                }
            }
        }
    }

    @Override
    public void tick() {
        if (isburning) {
            temperature.self_add();
            burnTime++;
            if (temperature.getCurrent() > 80 && tank.getFluidAmount() >= 1000 && tank.getFluid().getFluid() == Fluids.WATER) {
                isCooking = true;
                progress.self_add();
                if (progress.atMaxValue()) {
                    finishCook();
                }
            }

            if (burnTime >= maxBurnTime) {
                isburning = false;
                if (isCooking) {
                    boolean b = takeFuel();
                    if (!b) {
                        isCooking = false;
                    } else {
                        isburning = true;
                    }
                }
            }
            markDirty2();
        } else {
            if (temperature.getCurrent() > world.getBiome(pos).getTemperature(pos)) {
                temperature.self_substract();
                markDirty2();
            } else if (temperature.getCurrent() < world.getBiome(pos).getTemperature(pos)) {
                temperature.self_add();
                markDirty2();
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
            if (burnTime != 0) {
                fuel_ash_Handler.extractItem(0, 1, false);
                this.maxBurnTime = burnTime;
                this.burnTime = 0;
                this.isburning = true;
                markDirty2();
                return true;
            }
        }
        return false;
    }

    private void finishCook() {
        float waterAmount = tank.getFluidAmount();
        float tankCapacity = tank.getCapacity();
        int value = Math.round(tankCapacity / waterAmount);
        int slots = this.resources.getSlots();
        for (int i = 0; i < slots; i++) {
            ItemStack stackInSlot = resources.getStackInSlot(i);
            int count = stackInSlot.getCount() / value;
            Item item = stackInSlot.getItem();
            effects.setStackInSlot(i, new ItemStack(item, count));
            tank.setFluid(new FluidStack(Registered_Fluids.TEA_WATER.fluid.get().getFluid(), (int) waterAmount));
        }
        this.resources = new IResourceItemHandler(9);
        this.isCooking = false;
        this.isburning = false;
        this.burnTime = 0;
        this.maxBurnTime = 0;
    }

    public void takeAwayPot() {
        this.hasBlackPot = false;
        this.effects = new IResourceItemHandler(9);
        this.resources = new IResourceItemHandler(9);
        this.tank = new FluidTank(2000);
        markDirty2();
    }

    public boolean hasBlackPot() {
        return hasBlackPot;
    }

    public FluidTank getTank() {
        return tank;
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

