package com.dbydd.suuuuuper_herbal_tea.items;

import com.dbydd.suuuuuper_herbal_tea.Suuuuuuuper_herbal_tea;
import com.dbydd.suuuuuper_herbal_tea.interfaces.ITeaEffects;
import com.dbydd.suuuuuper_herbal_tea.interfaces.ITeaResource;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.world.IWorld;

import java.util.function.BiConsumer;

public class HoneySuckle_Item extends BlockItem implements ITeaResource {
    private ITeaEffects effect;

    public HoneySuckle_Item(ITeaEffects effect) {
        super(Registered_Blocks.HONEYSUCKLE,new Properties().group(Suuuuuuuper_herbal_tea.TAB).food(new Food.Builder().saturation(2).hunger(2).fastToEat().setAlwaysEdible().build()));
        this.effect = effect;
    }

    @Override
    public ITeaEffects generateEffects() {
        return effect;
    }
}
