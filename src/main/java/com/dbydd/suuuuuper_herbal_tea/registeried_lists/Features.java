package com.dbydd.suuuuuper_herbal_tea.registeried_lists;

import com.dbydd.suuuuuper_herbal_tea.Suuuuuuuper_herbal_tea;
import com.dbydd.suuuuuper_herbal_tea.biomes.Gazebo;
import com.dbydd.suuuuuper_herbal_tea.biomes.GazeboPiece;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Features {
    private static final DeferredRegister<Feature<?>> FEATURES = Suuuuuuuper_herbal_tea.FEATURES_REGISTER;

    public static RegistryObject<Structure<NoFeatureConfig>> test = FEATURES.register("house", () ->
            new Gazebo(NoFeatureConfig::deserialize)
    );

    public static IStructurePieceType diamondHouseStructurePieceType;

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        diamondHouseStructurePieceType = Registry.register(Registry.STRUCTURE_PIECE, "house", GazeboPiece::new);
        for (Biome biome : ForgeRegistries.BIOMES) {
            biome.addStructure(test.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
            biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, test.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
        }
    }

    public static void init() {

    }
}
