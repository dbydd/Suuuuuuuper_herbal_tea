package com.dbydd.suuuuuper_herbal_tea.items;

import com.dbydd.suuuuuper_herbal_tea.Suuuuuuuper_herbal_tea;
import com.dbydd.suuuuuper_herbal_tea.blocks.Big_Black_Pot;
import com.dbydd.suuuuuper_herbal_tea.blocks.BlockTeaCup;
import com.dbydd.suuuuuper_herbal_tea.blocks.Earth_Stovetop;
import com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys.TileBig_Black_Pot;
import com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys.TileEarth_Stovetop;
import com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys.TileTeaCup;
import com.dbydd.suuuuuper_herbal_tea.utils.IResourceItemHandler;
import com.dbydd.suuuuuper_herbal_tea.utils.IntegerContainer;
import net.minecraft.block.Block;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
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
        this.addPropertyOverride(new ResourceLocation("isempty"), (p_call_1_, p_call_2_, p_call_3_) -> {
            CompoundNBT spoonresources = p_call_1_.getChildTag("spoonresources");
            if (spoonresources != null){
                return spoonresources.getBoolean("isempty")?0:1;
            }
            return 0;
        });
    }

    public static CompoundNBT getEmptySpoon() {
        CompoundNBT nbt = new CompoundNBT();
        IResourceItemHandler spoonhandler = new IResourceItemHandler(9);
        FluidTank spoontank = new FluidTank(200);
        nbt.put("tank", spoontank.writeToNBT(new CompoundNBT()));
        nbt.put("effects", spoonhandler.serializeNBT());
        nbt.putBoolean("isempty", true);
        return nbt;
    }

}
