package com.dbydd.suuuuuper_herbal_tea.events;

import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Features;
import com.dbydd.suuuuuper_herbal_tea.worldgen.biomes.World_Tea_Tree_Biome;
import com.dbydd.suuuuuper_herbal_tea.worldgen.tea_house.TeaHouseStructurePiece;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonSetupEvent {
    public static IStructurePieceType teaHouseStructurePieceType;

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        teaHouseStructurePieceType = Registry.register(Registry.STRUCTURE_PIECE, "tea_house", TeaHouseStructurePiece::new);

        for (Biome biome : ForgeRegistries.BIOMES) {
            if (!(biome instanceof World_Tea_Tree_Biome)) {
                biome.addStructure(Registered_Features.TEA_HOUSE_FEATURE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
                biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Registered_Features.TEA_HOUSE_FEATURE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));

            }
        }
    }
}
