package com.dbydd.suuuuuper_herbal_tea.biomes;

import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Block;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.List;

public class Tea_Villa extends Biome {
    public Tea_Villa(Builder biomeBuilder) {
        super(biomeBuilder);
        DefaultBiomeFeatures.addStructures(this);
        DefaultBiomeFeatures.addBambooJungleVegetation(this);
        DefaultBiomeFeatures.addCarvers(this);
        DefaultBiomeFeatures.addLakes(this);
        DefaultBiomeFeatures.addTallGrass(this);
        DefaultBiomeFeatures.addOres(this);
        DefaultBiomeFeatures.addGrass(this);
//        this.addStructure();
    }


}
