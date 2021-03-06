package com.dbydd.suuuuuper_herbal_tea.items;

import com.dbydd.suuuuuper_herbal_tea.SuuuuuuuperHerbalTea;
import com.dbydd.suuuuuper_herbal_tea.interfaces.ITeaEffects;
import com.dbydd.suuuuuper_herbal_tea.interfaces.ITeaResource;
import net.minecraft.item.Food;

public class WolfBerry extends ItemBase implements ITeaResource {

    private ITeaEffects effect;

    public WolfBerry(String name, ITeaEffects effect) {
        super(new Properties().group(SuuuuuuuperHerbalTea.TAB), name,new Food.Builder().setAlwaysEdible().hunger(2).saturation(3).build());
        this.effect = effect;
    }

    @Override
    public ITeaEffects generateEffects() {
        return effect;
    }
}
