package com.dbydd.suuuuuper_herbal_tea.worldgen;

import com.dbydd.suuuuuper_herbal_tea.blocks.Jujuce_Tree;
import com.dbydd.suuuuuper_herbal_tea.blocks.TeaTree;
import com.dbydd.suuuuuper_herbal_tea.blocks.Wolf_Berry_Tree;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Blocks;
import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.BlockStateProviderType;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TeaVillaStateProvider extends BlockStateProvider {
    public TeaVillaStateProvider() {
        super(BlockStateProviderType.SIMPLE_STATE_PROVIDER);
    }

    @Override
    public BlockState getBlockState(Random randomIn, BlockPos blockPosIn) {
        List<BlockState> blocks = Arrays.asList(Registered_Blocks.JUJUCE_TREE_TREE.getDefaultState().with(Jujuce_Tree.GROW_TIER, 5), Registered_Blocks.TEA_TREE.getDefaultState().with(TeaTree.GROW_TIER, 5), Registered_Blocks.WOLF_BERRY_TREE.getDefaultState().with(Wolf_Berry_Tree.GROW_TIER, 5), Registered_Blocks.HONEYSUCKLE.getDefaultState(), Blocks.GRASS_PATH.getDefaultState());
        return blocks.get(randomIn.nextInt(blocks.size()));
    }

    @Override
    public <T> T serialize(DynamicOps<T> p_218175_1_) {
        ImmutableMap.Builder<T, T> builder = ImmutableMap.builder();
        builder.put(p_218175_1_.createString("type"), p_218175_1_.createString(Registry.BLOCK_STATE_PROVIDER_TYPE.getKey(this.blockStateProvider).toString()));
        return (new Dynamic<>(p_218175_1_, p_218175_1_.createMap(builder.build()))).getValue();
    }
}
