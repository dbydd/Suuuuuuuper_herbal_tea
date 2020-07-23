package com.dbydd.suuuuuper_herbal_tea.utils;

import com.dbydd.suuuuuper_herbal_tea.interfaces.ITeaEffects;
import com.dbydd.suuuuuper_herbal_tea.interfaces.ITeaResource;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import java.util.function.BiConsumer;

public class IResourceItemHandler extends ItemStackHandler {

    public IResourceItemHandler(int i) {
        super(i);
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if (stack.getItem() instanceof ITeaResource) {
            return super.insertItem(slot, stack, simulate);
        }
        return stack;
    }

    public void useResources(ItemStack stack, World worldIn, LivingEntity entityLiving){
        int slots = this.getSlots();
        for(int i = 0;i<slots;i++){
            ItemStack stackInSlot = this.getStackInSlot(i);
            if(!stackInSlot.isEmpty()) {
                ITeaResource resource = (ITeaResource) stackInSlot.getItem();
                ITeaEffects effects = resource.generateEffects();
                effects.generate(worldIn, (PlayerEntity) entityLiving, stackInSlot.getCount());
            }
        }
    }

}
