package com.dbydd.suuuuuper_herbal_tea.Dimenisions.dim02_tea_villa;

import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;

import java.util.function.BiFunction;

public class Tea_Villa_Mod_Dimension extends ModDimension {
    @Override
    public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
        return Tea_Villa_Dimension::new;
    }
}
