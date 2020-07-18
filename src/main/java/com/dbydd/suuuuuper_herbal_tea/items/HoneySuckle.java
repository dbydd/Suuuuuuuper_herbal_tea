package com.dbydd.suuuuuper_herbal_tea.items;

import com.dbydd.suuuuuper_herbal_tea.interfaces.ITeaResource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.IWorld;

import java.util.function.BiConsumer;

public class HoneySuckle extends ItemBase implements ITeaResource {
    public HoneySuckle(Properties properties, String name, BiConsumer<IWorld, PlayerEntity> effect) {
        super(properties, name);
    }

    @Override
    public BiConsumer<IWorld, PlayerEntity> generateEffects() {
        return null;
    }
}
