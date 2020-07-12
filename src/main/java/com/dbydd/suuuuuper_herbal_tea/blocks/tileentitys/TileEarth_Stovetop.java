package com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys;

import com.dbydd.suuuuuper_herbal_tea.items.Big_Black_Pot_Item;
import com.dbydd.suuuuuper_herbal_tea.interfaces.ITeaResource;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_TileEntities;
import com.dbydd.suuuuuper_herbal_tea.utils.IntegerContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class TileEarth_Stovetop extends TileEntity implements ITickableTileEntity {
    private boolean hasPot = false;
    private boolean isburning = false;
    private ItemStackHandler fuel_ash_Handler = new ItemStackHandler(2);
    private IntegerContainer temperature = new IntegerContainer(0, 100);
    private IntegerContainer progress = new IntegerContainer(0, 200);
    private FluidTank tank = new FluidTank(2000);
    private IResourceItemHandler resources = new IResourceItemHandler(9);

    public TileEarth_Stovetop() {
        super(Registered_TileEntities.TILE_EARTH_STOVETOP_TYPE.get());
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putBoolean("hasPot", hasPot);
        compound.put("fuel_and_ash", fuel_ash_Handler.serializeNBT());
        compound.putBoolean("isburning", isburning);
        compound.put("temperature", temperature.serializeNBT());
        compound.put("resources", resources.serializeNBT());
        compound.put("progress", progress.serializeNBT());
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
        this.progress.deserializeNBT(compound.getCompound("progress"));
        super.read(compound);
    }

    public void onBlockActived(World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack heldItem = player.getHeldItem(handIn);
        if (heldItem.isEmpty()) {
            ItemHandlerHelper.giveItemToPlayer(player, fuel_ash_Handler.getStackInSlot(1));
            markDirty();
        } else if (heldItem.getItem() instanceof Big_Black_Pot_Item) {
            if (!hasPot) {
                this.resources.deserializeNBT(heldItem.getChildTag("BlockEntityTag").getCompound("resources"));
                this.tank.readFromNBT(heldItem.getChildTag("BlockEntityTag").getCompound("tank"));
                player.setHeldItem(handIn, ItemStack.EMPTY);
                hasPot = true;
                markDirty();
            }
        } else if (heldItem.getItem() instanceof FlintAndSteelItem) {
            this.isburning = true;
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
            if (temperature.getCurrent() > 80) {
                progress.self_add();
                if (progress.atMaxValue()) {

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
