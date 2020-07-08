package com.dbydd.suuuuuper_herbal_tea.items;

import com.dbydd.suuuuuper_herbal_tea.Suuuuuuuper_herbal_tea;
import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class Tea_Leaves extends ItemBase {

    public Tea_Leaves(String name) {
        super(new Properties().group(Suuuuuuuper_herbal_tea.TAB), name, new Food.Builder().setAlwaysEdible().hunger(1).effect(() -> new EffectInstance(Effects.SPEED, 200, 1), 1).build());
    }

}
