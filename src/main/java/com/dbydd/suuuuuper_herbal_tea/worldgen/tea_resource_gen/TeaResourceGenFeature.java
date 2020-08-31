package com.dbydd.suuuuuper_herbal_tea.worldgen.tea_resource_gen;

import com.dbydd.suuuuuper_herbal_tea.blocks.JujuceTree;
import com.dbydd.suuuuuper_herbal_tea.blocks.TeaTree;
import com.dbydd.suuuuuper_herbal_tea.blocks.WolfBerryTree;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.RegisteredBlocks;
import com.dbydd.suuuuuper_herbal_tea.utils.RandomUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TeaResourceGenFeature extends FlowersFeature<NoFeatureConfig> {
    public TeaResourceGenFeature() {
        super(NoFeatureConfig::deserialize);
    }

    @Override
    public boolean func_225559_a_(IWorld world, BlockPos pos, NoFeatureConfig p_225559_3_) {
        return true;
    }

    @Override
    public int func_225560_a_(NoFeatureConfig p_225560_1_) {
        return 4;
    }

    @Override
    public BlockPos getNearbyPos(Random random, BlockPos pos, NoFeatureConfig p_225561_3_) {
        return new BlockPos(pos.getX() + random.nextInt(7) - random.nextInt(7), pos.getY() + 1, pos.getZ() + random.nextInt(7) - random.nextInt(7));
    }

    @Override
    public BlockState getFlowerToPlace(Random random, BlockPos pos, NoFeatureConfig p_225562_3_) {
        if (RandomUtils.outputBooleanByChance(random, 0.12)) {
            List<BlockState> blocks = Arrays.asList(RegisteredBlocks.JUJUCE_TREE_TREE.getDefaultState().with(JujuceTree.GROW_TIER, random.nextInt(5)), RegisteredBlocks.TEA_TREE.getDefaultState().with(TeaTree.GROW_TIER, random.nextInt(5)), RegisteredBlocks.WOLF_BERRY_TREE.getDefaultState().with(WolfBerryTree.GROW_TIER, random.nextInt(5)), RegisteredBlocks.HONEYSUCKLE.getDefaultState());
              return blocks.get(random.nextInt(blocks.size()));
        }
        return Blocks.AIR.getDefaultState();
    }
}
