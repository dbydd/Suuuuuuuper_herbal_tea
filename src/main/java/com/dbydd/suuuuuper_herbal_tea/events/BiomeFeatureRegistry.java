package com.dbydd.suuuuuper_herbal_tea.events;

import com.dbydd.suuuuuper_herbal_tea.registeried_lists.RegisteredDecorators;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.RegisteredFeatures;
import com.dbydd.suuuuuper_herbal_tea.worldgen.TeaVillaBlockPlacer;
import com.dbydd.suuuuuper_herbal_tea.worldgen.TeaVillaStateProvider;
import com.dbydd.suuuuuper_herbal_tea.worldgen.biomes.TeaVillaBiome;
import com.dbydd.suuuuuper_herbal_tea.worldgen.biomes.WorldTeaTreeBiome;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BiomeFeatureRegistry {
//    public static IStructurePieceType teaHouseStructurePieceType;

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
//        teaHouseStructurePieceType = Registry.register(Registry.STRUCTURE_PIECE, "tea_house", TeaHouseStructurePiece::new);

        for (Biome biome : ForgeRegistries.BIOMES) {
            if (!(biome instanceof WorldTeaTreeBiome)) {
                List<Biome> biomeDenies = new ArrayList<>(ForgeRegistries.BIOMES.getValues().stream().filter(biome1 -> {
                    String path = biome1.getRegistryName().getPath();
                    return path.contains("ocean") || path.contains("nether") || path.contains("end") || path.contains("river");
                }).collect(Collectors.toList()));
                if (!biomeDenies.contains(biome)) {
//                    biome.addStructure(RegisteredFeatures.TEA_HOUSE_FEATURE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
//                    biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, RegisteredFeatures.TEA_HOUSE_FEATURE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
                    biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, RegisteredFeatures.TEA_RESOURCE_GENERATION_OVERWORLD.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(RegisteredDecorators.TEA_REOURCE_DECORATOR.get().configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
                }
            }
            if (biome instanceof TeaVillaBiome) {
                biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, RegisteredFeatures.TEA_VILLA_RESOURCE_GEN.get().withConfiguration(new BlockClusterFeatureConfig.Builder(new TeaVillaStateProvider(), new TeaVillaBlockPlacer()).build()).withPlacement(RegisteredDecorators.TEA_VILLA_RESOURCE_GEN_DECORATOR.get().configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
            }
        }
    }
}
