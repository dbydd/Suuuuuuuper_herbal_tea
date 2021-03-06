package com.dbydd.suuuuuper_herbal_tea.registeried_lists;

import com.dbydd.suuuuuper_herbal_tea.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class RegisteredBlocks {


    public static final Block EARTH_STOVETOP = new EarthStovetop();
    public static final Block BIG_BLACK_POT = new BigBlackPot();
    public static final Block TEA_CUP = new BlockTeaCup();
    public static final Block CHAIR = new StoneChair(Block.Properties.create(Material.ROCK).notSolid().hardnessAndResistance(3.0f));
    public static final Block STONE_TABLE = new Stone_Table();
    public static final Block WORLD_TEA_TREE_LEAVE = new WorldTeaTreeLeave(Block.Properties.create(Material.LEAVES).notSolid().harvestTool(ToolType.AXE).sound(SoundType.PLANT).hardnessAndResistance(0.5f), "world_tea_tree_leave_not_glow");
    public static final Block WORLD_TEA_TREE_LEAVE_GLOW = new WorldTeaTreeLeave(Block.Properties.create(Material.LEAVES).notSolid().harvestTool(ToolType.AXE).hardnessAndResistance(0.5f).sound(SoundType.PLANT).lightValue(12), "world_tea_tree_leave_glow");
    public static final Block WATER_FACE = new BlockBase(Block.Properties.create(Material.AIR).notSolid().hardnessAndResistance(-1));
    public static final Block TEA_WATER_FACE = new BlockBase(Block.Properties.create(Material.AIR).notSolid().hardnessAndResistance(-1));
    public static final Block WORLD_TEA_TREE_FRUIT = new WorldTeaTreeFruit();
    public static final Block WOLF_BERRY_TREE = new WolfBerryTree();
    public static final Block JUJUCE_TREE_TREE = new JujuceTree();
    public static final Block TEA_TREE = new TeaTree();
    public static final Block HONEYSUCKLE = new HoneySuckleBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0.5f).sound(SoundType.PLANT));
    public static final Block SINK = new BlockSink();

    public static void init() {
    }
}
