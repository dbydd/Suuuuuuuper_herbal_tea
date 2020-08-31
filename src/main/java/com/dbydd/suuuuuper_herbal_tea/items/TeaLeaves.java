package com.dbydd.suuuuuper_herbal_tea.items;

import com.dbydd.suuuuuper_herbal_tea.SuuuuuuuperHerbalTea;
import com.dbydd.suuuuuper_herbal_tea.interfaces.ITeaEffects;
import com.dbydd.suuuuuper_herbal_tea.interfaces.ITeaResource;
import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;

import java.util.HashMap;
import java.util.Map;

public class TeaLeaves extends ItemBase implements ITeaResource {
    public static Map<String, TeaLeaves> leavesList = new HashMap<>();
    private final ITeaEffects effect;

    public TeaLeaves(String name, ITeaEffects effect, EffectInstance effectOnEat) {
        super(new Properties().group(SuuuuuuuperHerbalTea.TAB), name, new Food.Builder().setAlwaysEdible().hunger(1).effect(()->effectOnEat, 0.5F).build());
        leavesList.put(name, this);
        this.effect = effect;
    }

    @Override
    public ITeaEffects generateEffects() {
        return effect;
    }

}
