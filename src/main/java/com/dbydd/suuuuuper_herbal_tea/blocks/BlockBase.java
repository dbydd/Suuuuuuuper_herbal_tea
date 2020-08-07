package com.dbydd.suuuuuper_herbal_tea.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;

public class BlockBase extends Block {
    public static Map<String, Supplier<Block>> registeries = new TreeMap<>();
    public static Map<Block, RenderType> renderTypeMap = new HashMap<>();

    public BlockBase(Properties properties, String name) {
        super(properties);
        registeries.put(name, () -> this);
    }

    public BlockBase(Properties properties) {
        super(properties);
    }
}
