package com.dbydd.suuuuuper_herbal_tea.items;

import com.dbydd.suuuuuper_herbal_tea.Suuuuuuuper_herbal_tea;
import com.dbydd.suuuuuper_herbal_tea.interfaces.ITeaEffects;
import com.dbydd.suuuuuper_herbal_tea.interfaces.ITeaResource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.IntegerProperty;
import net.minecraft.world.IWorld;

import java.util.Collection;
import java.util.function.BiConsumer;

public class GreenDate extends ItemBase implements ITeaResource {

    private ITeaEffects effect;

    public GreenDate(String name, ITeaEffects effect) {
        super(new Properties().group(Suuuuuuuper_herbal_tea.TAB), name, new Food.Builder().setAlwaysEdible().hunger(4).saturation(8).effect(() -> new EffectInstance(Effects.STRENGTH, 100, 1), 0.7f).build());
        this.effect = effect;
    }

    @Override
    public ITeaEffects generateEffects() {
        return effect;
    }
}
