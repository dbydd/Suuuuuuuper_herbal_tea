package com.dbydd.suuuuuper_herbal_tea.items;

import com.dbydd.suuuuuper_herbal_tea.Suuuuuuuper_herbal_tea;
import com.dbydd.suuuuuper_herbal_tea.interfaces.ITeaResource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IWorld;

import java.util.function.BiConsumer;

public class Tea_Leaves extends ItemBase implements ITeaResource {

    public Tea_Leaves(String name) {
        super(new Properties().group(Suuuuuuuper_herbal_tea.TAB), name, new Food.Builder().setAlwaysEdible().hunger(1).effect(() -> new EffectInstance(Effects.SPEED, 200, 1), 1).build());
    }

    @Override
    public BiConsumer<IWorld, PlayerEntity> generateEffects() {
        return (world, playerEntity) -> {
            if(!world.isRemote())
            playerEntity.sendMessage(new StringTextComponent("you ated a tea_leave"));
        };
    }

}
