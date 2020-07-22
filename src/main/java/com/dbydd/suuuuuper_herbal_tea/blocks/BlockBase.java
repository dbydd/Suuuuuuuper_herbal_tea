package com.dbydd.suuuuuper_herbal_tea.blocks;

import com.dbydd.suuuuuper_herbal_tea.registeried_lists.RenderTypes;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;

public class BlockBase extends Block {
    public static Map<String, Supplier<Block>> registeries = new TreeMap<>();

    public BlockBase(Properties properties, String name, RenderType renderType) {
        super(properties);
        registeries.put(name, () -> this);
        RenderTypes.blockRenderTypeMap.put(this, renderType);
    }
}
