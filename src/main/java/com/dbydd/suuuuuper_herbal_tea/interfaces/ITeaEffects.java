package com.dbydd.suuuuuper_herbal_tea.interfaces;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.IWorld;

@FunctionalInterface
public interface ITeaEffects {
    void generate(IWorld world, PlayerEntity playerEntity, int value);
}
