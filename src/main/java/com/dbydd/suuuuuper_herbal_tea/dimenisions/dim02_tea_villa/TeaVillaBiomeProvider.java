package com.dbydd.suuuuuper_herbal_tea.dimenisions.dim02_tea_villa;

import com.dbydd.suuuuuper_herbal_tea.registeried_lists.RegisteredBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;

import java.util.Collections;

public class TeaVillaBiomeProvider extends BiomeProvider {

    protected TeaVillaBiomeProvider() {
        super(Collections.singleton(RegisteredBiomes.TEA_VILLA.get()));
    }

    @Override
    public Biome getNoiseBiome(int x, int y, int z) {
        return RegisteredBiomes.TEA_VILLA.get();
    }
}
