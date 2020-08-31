package com.dbydd.suuuuuper_herbal_tea.worldgen.biomes;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;

public class TeaVillaBiome extends Biome {
    public TeaVillaBiome(Builder biomeBuilder) {
        super(biomeBuilder);
        DefaultBiomeFeatures.addBamboo(this);
        DefaultBiomeFeatures.addBerryBushes(this);
        DefaultBiomeFeatures.addCarvers(this);
        DefaultBiomeFeatures.addDenseGrass(this);
        DefaultBiomeFeatures.addExtraDefaultFlowers(this);
        DefaultBiomeFeatures.addStructures(this);
        DefaultBiomeFeatures.addTaigaRocks(this);
        DefaultBiomeFeatures.addLakes(this);
        DefaultBiomeFeatures.addOres(this);
        DefaultBiomeFeatures.addSwampClayDisks(this);
        DefaultBiomeFeatures.addStoneVariants(this);
    }

}
