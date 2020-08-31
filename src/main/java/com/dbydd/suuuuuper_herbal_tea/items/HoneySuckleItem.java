package com.dbydd.suuuuuper_herbal_tea.items;

import com.dbydd.suuuuuper_herbal_tea.SuuuuuuuperHerbalTea;
import com.dbydd.suuuuuper_herbal_tea.interfaces.ITeaEffects;
import com.dbydd.suuuuuper_herbal_tea.interfaces.ITeaResource;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.RegisteredBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;

public class HoneySuckleItem extends BlockItem implements ITeaResource {
    private ITeaEffects effect;

    public HoneySuckleItem(ITeaEffects effect) {
        super(RegisteredBlocks.HONEYSUCKLE,new Properties().group(SuuuuuuuperHerbalTea.TAB).food(new Food.Builder().saturation(2).hunger(2).fastToEat().setAlwaysEdible().build()));
        this.effect = effect;
    }

    @Override
    public ITeaEffects generateEffects() {
        return effect;
    }
}
