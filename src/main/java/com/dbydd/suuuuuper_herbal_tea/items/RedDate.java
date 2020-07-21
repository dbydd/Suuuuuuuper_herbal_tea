package com.dbydd.suuuuuper_herbal_tea.items;

import com.dbydd.suuuuuper_herbal_tea.Suuuuuuuper_herbal_tea;
import com.dbydd.suuuuuper_herbal_tea.interfaces.ITeaEffects;
import com.dbydd.suuuuuper_herbal_tea.interfaces.ITeaResource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.IWorld;

import java.util.function.BiConsumer;

public class RedDate extends ItemBase implements ITeaResource {
    private ITeaEffects effect;

    public RedDate(String name, ITeaEffects effect) {
        super(new Properties().group(Suuuuuuuper_herbal_tea.TAB), name, new Food.Builder().setAlwaysEdible().hunger(4).saturation(8).effect(()->new EffectInstance(Effects.INSTANT_HEALTH, 100, 1),0.75f).build());
        this.effect = effect;
    }

    @Override
    public ITeaEffects generateEffects() {
        return effect;
    }
}
