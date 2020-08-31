package com.dbydd.suuuuuper_herbal_tea.dimenisions.dim01_world_tea_tree;

import net.minecraft.block.Blocks;
import net.minecraft.world.gen.GenerationSettings;

public class WorldTeaTreeGenerationSettings extends GenerationSettings {
    public WorldTeaTreeGenerationSettings() {
        this.setDefaultBlock(Blocks.AIR.getDefaultState());
        this.setDefaultFluid(Blocks.WATER.getDefaultState());
    }
}
