package com.dbydd.suuuuuper_herbal_tea.items;

import com.dbydd.suuuuuper_herbal_tea.Suuuuuuuper_herbal_tea;
import com.dbydd.suuuuuper_herbal_tea.blocks.Big_Black_Pot;
import com.dbydd.suuuuuper_herbal_tea.blocks.Earth_Stovetop;
import com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys.TileBig_Black_Pot;
import com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys.TileEarth_Stovetop;
import com.dbydd.suuuuuper_herbal_tea.utils.IntegerContainer;
import net.minecraft.block.Block;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ToolItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class BigSpoon extends ItemBase {

    public BigSpoon() {
        super(new Properties().maxStackSize(1).group(Suuuuuuuper_herbal_tea.TAB), "spoon");
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos targetPos = context.getPos();
        ItemStack spoon = context.getItem();
        CompoundNBT nbt = spoon.getTag();
        ItemStackHandler effects = new ItemStackHandler();
        ItemStackHandler currentEffects = new ItemStackHandler();
        Block targetBlock = world.getBlockState(targetPos).getBlock();

        if (targetBlock instanceof Big_Black_Pot) {
            TileBig_Black_Pot black_pot = (TileBig_Black_Pot) world.getTileEntity(targetPos);
            FluidTank tank = new FluidTank(200);
            tank = tank.readFromNBT(nbt.getCompound("tank"));
            currentEffects.deserializeNBT(nbt.getCompound("effects"));
            effects.deserializeNBT(black_pot.serializeNBT().getCompound("effects"));
            IFluidHandler iFluidHandler = black_pot.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).orElseGet(() -> new FluidTank(2000));
            if (tank.getFluid().getFluid() == iFluidHandler.getFluidInTank(0).getFluid() && isItemHanderAContainsB(currentEffects, effects)) {
                tank.fill(iFluidHandler.drain(tank.getSpace(), IFluidHandler.FluidAction.EXECUTE), IFluidHandler.FluidAction.EXECUTE);
                black_pot.markDirty();
                nbt.put("tank", tank.writeToNBT(new CompoundNBT()));
                updateItemStackNBT(nbt);
                playSound(context.getPlayer());
                return ActionResultType.SUCCESS;
            }
        } else if (targetBlock instanceof Earth_Stovetop) {
            TileEarth_Stovetop stovetop = (TileEarth_Stovetop) world.getTileEntity(targetPos);
            if (stovetop.hasPot()) {
                FluidTank tank = new FluidTank(200);
                tank = tank.readFromNBT(nbt.getCompound("tank"));
                currentEffects.deserializeNBT(nbt.getCompound("effects"));
                effects.deserializeNBT(stovetop.serializeNBT().getCompound("effects"));
                IFluidHandler iFluidHandler = stovetop.getTank();
                if (tank.getFluid().getFluid() == iFluidHandler.getFluidInTank(0).getFluid() && isItemHanderAContainsB(currentEffects, effects)) {
                    tank.fill(iFluidHandler.drain(tank.getSpace(), IFluidHandler.FluidAction.EXECUTE), IFluidHandler.FluidAction.EXECUTE);
                    stovetop.markDirty();
                    nbt.put("tank", tank.writeToNBT(new CompoundNBT()));
                    updateItemStackNBT(nbt);
                    playSound(context.getPlayer());
                    return ActionResultType.SUCCESS;
                }
            }
        }

        return ActionResultType.PASS;
    }

    private boolean isItemHanderAContainsB(ItemStackHandler A, ItemStackHandler B) {
        if (A.getSlots() == B.getSlots()) {
            ArrayList<Item> ItemsInA = new ArrayList<>();
            ArrayList<Item> ItemsInB = new ArrayList<>();
            for (int i = 0; i < A.getSlots(); i++) {
                ItemsInA.add(A.getStackInSlot(i).getItem());
                ItemsInB.add(B.getStackInSlot(i).getItem());
            }
            return ItemsInA.containsAll(ItemsInB);
        }
        return false;
    }

    private void playSound(PlayerEntity playerIn){
        SoundEvent soundevent = SoundEvents.ITEM_BUCKET_FILL;
        playerIn.playSound(soundevent, 1.0F, 1.0F);
    }
}
