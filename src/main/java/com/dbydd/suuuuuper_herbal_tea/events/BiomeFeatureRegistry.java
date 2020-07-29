package com.dbydd.suuuuuper_herbal_tea.events;

import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Decorators;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Features;
import com.dbydd.suuuuuper_herbal_tea.worldgen.biomes.World_Tea_Tree_Biome;
import com.dbydd.suuuuuper_herbal_tea.worldgen.tea_house.TeaHouseStructurePiece;
import mezz.jei.api.registration.IModIngredientRegistration;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BiomeFeatureRegistry {
    public static IStructurePieceType teaHouseStructurePieceType;

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        teaHouseStructurePieceType = Registry.register(Registry.STRUCTURE_PIECE, "tea_house", TeaHouseStructurePiece::new);

        for (Biome biome : ForgeRegistries.BIOMES) {
            if (!(biome instanceof World_Tea_Tree_Biome)) {
                biome.addStructure(Registered_Features.TEA_HOUSE_FEATURE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
                biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Registered_Features.TEA_HOUSE_FEATURE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
                biome.addFeature(GenerationStage.Decoration.TOP_LAYER_MODIFICATION, Registered_Features.TEA_RESOURCE_GENERATION_OVERWORLD.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Registered_Decorators.TEA_REOURCE_DECORATOR.get().configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
            }
        }
    }
}
