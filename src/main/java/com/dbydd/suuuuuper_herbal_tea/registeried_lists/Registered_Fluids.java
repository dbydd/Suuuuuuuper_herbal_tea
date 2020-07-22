package com.dbydd.suuuuuper_herbal_tea.registeried_lists;

import com.dbydd.suuuuuper_herbal_tea.fluids.Fluid_Base;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.SoundEvents;

public class Registered_Fluids {

    public static final Fluid_Base TEA_WATER = new Fluid_Base("tea_water", Block.Properties.create(Material.WATER).notSolid().hardnessAndResistance(100), builder -> builder.temperature(373).sound(SoundEvents.BLOCK_WATER_AMBIENT));

    public static void init(){

    }
}
