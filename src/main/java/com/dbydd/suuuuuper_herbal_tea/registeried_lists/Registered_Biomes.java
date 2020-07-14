package com.dbydd.suuuuuper_herbal_tea.registeried_lists;

import com.dbydd.suuuuuper_herbal_tea.Suuuuuuuper_herbal_tea;
import com.dbydd.suuuuuper_herbal_tea.worldgen.biomes.World_Tea_Tree_Biome;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registered_Biomes {
    public static final DeferredRegister<Biome> BIOMES_REGISTER = new DeferredRegister<>(ForgeRegistries.BIOMES, Suuuuuuuper_herbal_tea.NAME);

    public static final RegistryObject<Biome> WORLD_TEA_TREE_BIOME = BIOMES_REGISTER.register("world_tea_tree_biome", () -> new World_Tea_Tree_Biome(new Biome.Builder().category(Biome.Category.NONE).surfaceBuilder(
            SurfaceBuilder.NOPE, new SurfaceBuilderConfig(Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState())
    ).downfall(0.5f).temperature(0.37f).waterColor(Integer.parseInt("10FF0C", 16)).scale(1f).depth(1f).waterFogColor(Integer.parseInt("10FF0C", 16)).precipitation(Biome.RainType.NONE)));

}
