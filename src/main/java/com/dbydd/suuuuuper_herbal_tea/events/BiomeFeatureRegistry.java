package com.dbydd.suuuuuper_herbal_tea.events;

import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Decorators;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Features;
import com.dbydd.suuuuuper_herbal_tea.worldgen.TeaVillaBlockPlacer;
import com.dbydd.suuuuuper_herbal_tea.worldgen.TeaVillaStateProvider;
import com.dbydd.suuuuuper_herbal_tea.worldgen.biomes.Tea_Villa_Biome;
import com.dbydd.suuuuuper_herbal_tea.worldgen.biomes.World_Tea_Tree_Biome;
import com.dbydd.suuuuuper_herbal_tea.worldgen.tea_house.TeaHouseStructurePiece;
import com.dbydd.suuuuuper_herbal_tea.worldgen.tea_resource_gen.Tea_Villa_Resource_GenFeature;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.NetherBiome;
import net.minecraft.world.biome.OceanBiome;
import net.minecraft.world.biome.TheEndBiome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.placement.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BiomeFeatureRegistry {
    public static IStructurePieceType teaHouseStructurePieceType;

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        teaHouseStructurePieceType = Registry.register(Registry.STRUCTURE_PIECE, "tea_house", TeaHouseStructurePiece::new);

        for (Biome biome : ForgeRegistries.BIOMES) {
            if (!(biome instanceof World_Tea_Tree_Biome)) {
                List<Biome> biomeDenies = new ArrayList<>(ForgeRegistries.BIOMES.getValues().stream().filter(biome1 -> {
                    String path = biome1.getRegistryName().getPath();
                    return path.contains("ocean") || path.contains("nether") || path.contains("end");
                }).collect(Collectors.toList()));
                if (!biomeDenies.contains(biome)) {
                    biome.addStructure(Registered_Features.TEA_HOUSE_FEATURE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
                    biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Registered_Features.TEA_HOUSE_FEATURE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
                    biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Registered_Features.TEA_RESOURCE_GENERATION_OVERWORLD.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Registered_Decorators.TEA_REOURCE_DECORATOR.get().configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
                }
            }
            if (biome instanceof Tea_Villa_Biome) {
                biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Registered_Features.TEA_VILLA_RESOURCE_GEN.get().withConfiguration(new BlockClusterFeatureConfig.Builder(new TeaVillaStateProvider(), new TeaVillaBlockPlacer()).build()).withPlacement(Registered_Decorators.TEA_VILLA_RESOURCE_GEN_DECORATOR.get().configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
            }
        }
    }
}
