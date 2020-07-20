package com.dbydd.suuuuuper_herbal_tea.items;

import com.dbydd.suuuuuper_herbal_tea.Suuuuuuuper_herbal_tea;
import com.dbydd.suuuuuper_herbal_tea.interfaces.ITeaResource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IWorld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class Tea_Leaves extends ItemBase implements ITeaResource {
    public static Map<String, Tea_Leaves> leavesList = new HashMap<>();
    private final BiConsumer<IWorld, PlayerEntity> effect;

    public Tea_Leaves(String name, BiConsumer<IWorld, PlayerEntity> effect, EffectInstance effectOnEat) {
        super(new Properties().group(Suuuuuuuper_herbal_tea.TAB), name, new Food.Builder().setAlwaysEdible().hunger(1).effect(()->effectOnEat, 0.5F).build());
        leavesList.put(name, this);
        this.effect = effect;
    }

    @Override
    public BiConsumer<IWorld, PlayerEntity> generateEffects() {
        return effect;
    }

}
