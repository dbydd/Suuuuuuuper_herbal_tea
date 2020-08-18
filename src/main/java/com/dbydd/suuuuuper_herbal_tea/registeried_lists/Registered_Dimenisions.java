package com.dbydd.suuuuuper_herbal_tea.registeried_lists;

import com.dbydd.suuuuuper_herbal_tea.Dimenisions.dim02_tea_villa.Tea_Villa_Dimension;
import com.dbydd.suuuuuper_herbal_tea.Dimenisions.dim02_tea_villa.Tea_Villa_Mod_Dimension;
import com.dbydd.suuuuuper_herbal_tea.Suuuuuuuper_herbal_tea;
import com.dbydd.suuuuuper_herbal_tea.Dimenisions.dim01_world_tea_tree.World_Tea_Tree_Mod_Dimenision;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.RegistryObject;

public class Registered_Dimenisions {
    public static final RegistryObject<ModDimension> WORLD_TEA_TREE = Suuuuuuuper_herbal_tea.RegisteryDimenision("world_tea_tree", new World_Tea_Tree_Mod_Dimenision());
    public static final RegistryObject<ModDimension> TEA_VILLA = Suuuuuuuper_herbal_tea.RegisteryDimenision("tea_villa", new Tea_Villa_Mod_Dimension());
    public static void init(){

    }
}
