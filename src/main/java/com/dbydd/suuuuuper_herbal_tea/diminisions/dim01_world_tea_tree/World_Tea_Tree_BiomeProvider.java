package com.dbydd.suuuuuper_herbal_tea.diminisions.dim01_world_tea_tree;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProvider;

import java.util.*;

public class World_Tea_Tree_BiomeProvider extends BiomeProvider {
    private static final List<Biome> BIOMES = new ArrayList<>(Arrays.asList(Biomes.THE_VOID));

    protected World_Tea_Tree_BiomeProvider() {
        super(new HashSet<>(BIOMES));
    }

    @Override
    public Biome getNoiseBiome(int x, int y, int z) {
        return BIOMES.get(new Random().nextInt(BIOMES.size() - 1));
    }
}
