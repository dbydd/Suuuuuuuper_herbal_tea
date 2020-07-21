package com.dbydd.suuuuuper_herbal_tea.interfaces;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public interface ITeaResource {
    ITeaEffects generateEffects();
}
