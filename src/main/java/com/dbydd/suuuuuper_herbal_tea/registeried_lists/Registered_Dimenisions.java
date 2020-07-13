package com.dbydd.suuuuuper_herbal_tea.registeried_lists;

import com.dbydd.suuuuuper_herbal_tea.Suuuuuuuper_herbal_tea;
import com.dbydd.suuuuuper_herbal_tea.Dimenisions.dim01_world_tea_tree.World_Tea_Tree_Mod_Dimenision;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.RegistryObject;

public class Registered_Dimenisions {
    public static final RegistryObject<ModDimension> world_tea_tree = Suuuuuuuper_herbal_tea.RegisteryDimenision("world_tea_tree", new World_Tea_Tree_Mod_Dimenision());

    public static void init(){

    }
}
