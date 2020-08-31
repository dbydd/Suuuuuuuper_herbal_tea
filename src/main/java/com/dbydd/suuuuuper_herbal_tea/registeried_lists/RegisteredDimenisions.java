package com.dbydd.suuuuuper_herbal_tea.registeried_lists;

import com.dbydd.suuuuuper_herbal_tea.SuuuuuuuperHerbalTea;
import com.dbydd.suuuuuper_herbal_tea.dimenisions.dim02_tea_villa.TeaVillaModDimension;
import com.dbydd.suuuuuper_herbal_tea.dimenisions.dim01_world_tea_tree.WorldTeaTreeModDimenision;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.RegistryObject;

public class RegisteredDimenisions {
    public static final RegistryObject<ModDimension> WORLD_TEA_TREE = SuuuuuuuperHerbalTea.RegisteryDimenision("world_tea_tree", new WorldTeaTreeModDimenision());
    public static final RegistryObject<ModDimension> TEA_VILLA = SuuuuuuuperHerbalTea.RegisteryDimenision("tea_villa", new TeaVillaModDimension());
    public static void init(){

    }
}
