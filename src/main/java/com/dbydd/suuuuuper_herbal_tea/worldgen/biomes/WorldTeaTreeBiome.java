package com.dbydd.suuuuuper_herbal_tea.worldgen.biomes;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;

public class WorldTeaTreeBiome extends Biome {
    public WorldTeaTreeBiome(Builder biomeBuilder) {
        super(biomeBuilder);
    }

    @Override
    public int getSkyColor() {
        return 0;
    }
}
