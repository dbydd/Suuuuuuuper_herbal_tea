package com.dbydd.suuuuuper_herbal_tea.registeried_lists;

import com.dbydd.suuuuuper_herbal_tea.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class Registered_Blocks {
    public static final Block TEST_TREE = new TeaTree();
    public static final Block EARTH_STOVETOP = new Earth_Stovetop();
    public static final Block BIG_BLACK_POT = new Big_Black_Pot();
    public static final Block WORLD_TEA_TREE_LEAVE = new World_Tea_Tree_Leave(Block.Properties.create(Material.LEAVES).notSolid().harvestTool(ToolType.AXE).hardnessAndResistance(0.5f), "world_tea_tree_leave_not_glow");
    public static final Block WORLD_TEA_TREE_LEAVE_GLOW = new World_Tea_Tree_Leave(Block.Properties.create(Material.LEAVES).notSolid().harvestTool(ToolType.AXE).hardnessAndResistance(0.5f).lightValue(12), "world_tea_tree_leave_glow");

    public static void init() {
    }
}
