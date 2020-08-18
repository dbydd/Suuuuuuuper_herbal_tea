package com.dbydd.suuuuuper_herbal_tea.registeried_lists;

import com.dbydd.suuuuuper_herbal_tea.Suuuuuuuper_herbal_tea;
import com.dbydd.suuuuuper_herbal_tea.worldgen.tea_resource_gen.TeaResourceGen_Placement;
import com.dbydd.suuuuuper_herbal_tea.worldgen.tea_resource_gen.Tea_Villa_Resource_GenFeature;
import com.dbydd.suuuuuper_herbal_tea.worldgen.tea_resource_gen.Tea_Villa_Resource_Gen_Placement;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registered_Decorators {
    public static final DeferredRegister<Placement<?>> DECORATORS_REGISTER = new DeferredRegister<>(ForgeRegistries.DECORATORS, Suuuuuuuper_herbal_tea.NAME);

    public static final RegistryObject<TeaResourceGen_Placement> TEA_REOURCE_DECORATOR = DECORATORS_REGISTER.register("tea_resource_placement", () -> new TeaResourceGen_Placement(NoPlacementConfig::deserialize));
    public static final RegistryObject<Tea_Villa_Resource_Gen_Placement> TEA_VILLA_RESOURCE_GEN_DECORATOR = DECORATORS_REGISTER.register("tea_villa_placement", () -> new Tea_Villa_Resource_Gen_Placement(NoPlacementConfig::deserialize));

}
