package com.dbydd.suuuuuper_herbal_tea.diminisions.dim01_world_tea_tree;

import net.minecraft.block.Blocks;
import net.minecraft.world.gen.GenerationSettings;

public class World_Tea_Tree_GenerationSettings extends GenerationSettings {
    public World_Tea_Tree_GenerationSettings() {
        this.setDefaultBlock(Blocks.OAK_LEAVES.getDefaultState());
        this.setDefaultFluid(Blocks.WATER.getDefaultState());
    }
}
