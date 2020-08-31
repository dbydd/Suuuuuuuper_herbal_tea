package com.dbydd.suuuuuper_herbal_tea.items;

import com.dbydd.suuuuuper_herbal_tea.SuuuuuuuperHerbalTea;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;

public class ItemBase extends Item {
    public static Map<String, Supplier<Item>> registeries = new TreeMap<>();
    public static Properties DEFAULT_PROPERTIES = new Properties().group(SuuuuuuuperHerbalTea.TAB);

    public ItemBase(Properties properties, String name) {
        super(properties);
        registeries.put(name, () -> this);
    }

    public ItemBase(String name) {
        super(DEFAULT_PROPERTIES);
        registeries.put(name, () -> this);
    }

    /**
     * registry food
     */
    public ItemBase(Properties properties,String name, Food food) {
        super(properties.food(food));
        registeries.put(name, () -> this);
    }
}
