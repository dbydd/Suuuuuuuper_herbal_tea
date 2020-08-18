package com.dbydd.suuuuuper_herbal_tea.Dimenisions.dim02_tea_villa;

import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;

import java.util.Collections;
import java.util.Set;

public class Tea_Villa_BiomeProvider extends BiomeProvider {

    protected Tea_Villa_BiomeProvider() {
        super(Collections.singleton(Registered_Biomes.TEA_VILLA.get()));
    }

    @Override
    public Biome getNoiseBiome(int x, int y, int z) {
        return Registered_Biomes.TEA_VILLA.get();
    }
}
