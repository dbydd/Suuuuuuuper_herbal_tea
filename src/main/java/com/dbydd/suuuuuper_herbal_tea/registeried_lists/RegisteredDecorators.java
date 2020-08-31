package com.dbydd.suuuuuper_herbal_tea.registeried_lists;

import com.dbydd.suuuuuper_herbal_tea.SuuuuuuuperHerbalTea;
import com.dbydd.suuuuuper_herbal_tea.worldgen.tea_resource_gen.TeaResourceGenPlacement;
import com.dbydd.suuuuuper_herbal_tea.worldgen.tea_resource_gen.TeaVillaResourceGenPlacement;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegisteredDecorators {
    public static final DeferredRegister<Placement<?>> DECORATORS_REGISTER = new DeferredRegister<>(ForgeRegistries.DECORATORS, SuuuuuuuperHerbalTea.NAME);

    public static final RegistryObject<TeaResourceGenPlacement> TEA_REOURCE_DECORATOR = DECORATORS_REGISTER.register("tea_resource_placement", () -> new TeaResourceGenPlacement(NoPlacementConfig::deserialize));
    public static final RegistryObject<TeaVillaResourceGenPlacement> TEA_VILLA_RESOURCE_GEN_DECORATOR = DECORATORS_REGISTER.register("tea_villa_placement", () -> new TeaVillaResourceGenPlacement(NoPlacementConfig::deserialize));

}
