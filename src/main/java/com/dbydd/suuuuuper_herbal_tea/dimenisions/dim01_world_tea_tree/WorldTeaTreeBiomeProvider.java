package com.dbydd.suuuuuper_herbal_tea.dimenisions.dim01_world_tea_tree;

import com.dbydd.suuuuuper_herbal_tea.registeried_lists.RegisteredBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;

import java.util.*;

public class WorldTeaTreeBiomeProvider extends BiomeProvider {
    private static final List<Biome> BIOMES = new ArrayList<>(Arrays.asList(RegisteredBiomes.WORLD_TEA_TREE_BIOME.get()));

    protected WorldTeaTreeBiomeProvider() {
        super(new HashSet<>(BIOMES));
    }

    @Override
    public Biome getNoiseBiome(int x, int y, int z) {
//        return BIOMES.get(new Random().nextInt(BIOMES.size()));
        return BIOMES.get(0);
    }
}
