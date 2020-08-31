package com.dbydd.suuuuuper_herbal_tea.items;

import com.dbydd.suuuuuper_herbal_tea.SuuuuuuuperHerbalTea;
import com.dbydd.suuuuuper_herbal_tea.utils.IResourceItemHandler;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class BigSpoon extends ItemBase {

    public BigSpoon() {
        super(new Properties().maxStackSize(1).group(SuuuuuuuperHerbalTea.TAB), "spoon");
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
