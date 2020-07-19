package com.dbydd.suuuuuper_herbal_tea.items;

import com.dbydd.suuuuuper_herbal_tea.Suuuuuuuper_herbal_tea;
import com.dbydd.suuuuuper_herbal_tea.interfaces.ITeaResource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.world.IWorld;

import java.util.function.BiConsumer;

public class WolfBerry extends ItemBase implements ITeaResource {

    private BiConsumer<IWorld, PlayerEntity> effect;

    public WolfBerry(String name, BiConsumer<IWorld, PlayerEntity> effect) {
        super(new Properties().group(Suuuuuuuper_herbal_tea.TAB), name,new Food.Builder().setAlwaysEdible().hunger(2).saturation(3).build());
        this.effect = effect;
    }

    @Override
    public BiConsumer<IWorld, PlayerEntity> generateEffects() {
        return effect;
    }
}
