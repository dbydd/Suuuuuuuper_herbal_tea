package com.dbydd.suuuuuper_herbal_tea.registeried_lists;

import com.dbydd.suuuuuper_herbal_tea.Suuuuuuuper_herbal_tea;
import com.dbydd.suuuuuper_herbal_tea.worldgen.tea_resource_gen.TeaResourceGenFeature;
import com.dbydd.suuuuuper_herbal_tea.worldgen.tea_house.TeaHouseStructure;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registered_Features {
    public static final DeferredRegister<Feature<?>> FEATURES_REGISTER = new DeferredRegister<>(ForgeRegistries.FEATURES, Suuuuuuuper_herbal_tea.NAME);

    public static RegistryObject<Structure<NoFeatureConfig>> TEA_HOUSE_FEATURE = FEATURES_REGISTER.register("tea_house", () -> new TeaHouseStructure(NoFeatureConfig::deserialize));
    public static RegistryObject<TeaResourceGenFeature> TEA_RESOURCE_GENERATION_OVERWORLD = FEATURES_REGISTER.register("tea_resources", TeaResourceGenFeature::new);

    public static void init() {
    }
}
