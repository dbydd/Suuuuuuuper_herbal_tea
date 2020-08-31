package com.dbydd.suuuuuper_herbal_tea.dimenisions.dim02_tea_villa;

import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.OverworldDimension;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.OverworldChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;

public class TeaVillaDimension extends OverworldDimension {
    public TeaVillaDimension(World worldIn, DimensionType typeIn) {
        super(worldIn, typeIn);
    }

    @Override
    public ChunkGenerator<? extends GenerationSettings> createChunkGenerator() {
        return new OverworldChunkGenerator(world, new TeaVillaBiomeProvider(), new OverworldGenSettings());
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }
}
