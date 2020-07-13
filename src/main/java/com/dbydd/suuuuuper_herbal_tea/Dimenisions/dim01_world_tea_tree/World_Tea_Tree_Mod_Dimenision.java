package com.dbydd.suuuuuper_herbal_tea.Dimenisions.dim01_world_tea_tree;

import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;

import java.util.function.BiFunction;

public class World_Tea_Tree_Mod_Dimenision extends ModDimension {
    @Override
    public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
        return World_Tea_Tree_Dimenision::new;
    }
}
