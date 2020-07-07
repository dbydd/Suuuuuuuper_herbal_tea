package com.dbydd.suuuuuper_herbal_tea.blocks;

import com.dbydd.suuuuuper_herbal_tea.registeried_lists.BlockRenderTypes;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BlockBase extends Block {
    public static Map<String, Supplier<Block>> registeries = new HashMap<>();
    private final String name;

    public BlockBase(Properties properties, String name, RenderType renderType) {
        super(properties);
        this.name = name;
        registeries.put(name, () -> this);
        BlockRenderTypes.blockRenderTypeMap.put(this, renderType);
    }
}
