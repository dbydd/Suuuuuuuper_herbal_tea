package com.dbydd.suuuuuper_herbal_tea.registeried_lists;

import com.dbydd.suuuuuper_herbal_tea.Suuuuuuuper_herbal_tea;
import com.dbydd.suuuuuper_herbal_tea.worldgen.TeaVillaBlockPlacer;
import com.dbydd.suuuuuper_herbal_tea.worldgen.TeaVillaStateProvider;
import com.dbydd.suuuuuper_herbal_tea.worldgen.tea_resource_gen.TeaResourceGenFeature;
import com.dbydd.suuuuuper_herbal_tea.worldgen.tea_resource_gen.Tea_Villa_Resource_GenFeature;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registered_Features {
    public static final DeferredRegister<Feature<?>> FEATURES_REGISTER = new DeferredRegister<>(ForgeRegistries.FEATURES, Suuuuuuuper_herbal_tea.NAME);

//    public static RegistryObject<Structure<NoFeatureConfig>> TEA_HOUSE_FEATURE = FEATURES_REGISTER.register("tea_house", () -> new TeaHouseStructure(NoFeatureConfig::deserialize));
    public static RegistryObject<TeaResourceGenFeature> TEA_RESOURCE_GENERATION_OVERWORLD = FEATURES_REGISTER.register("tea_resources", TeaResourceGenFeature::new);
    public static RegistryObject<Tea_Villa_Resource_GenFeature> TEA_VILLA_RESOURCE_GEN = FEATURES_REGISTER.register("tea_villa_resources", ()->new Tea_Villa_Resource_GenFeature(dynamic -> new BlockClusterFeatureConfig.Builder(new TeaVillaStateProvider(), new TeaVillaBlockPlacer()).build()));

    public static void init() {
    }
}
