package com.dbydd.suuuuuper_herbal_tea.registeried_lists;

import com.dbydd.suuuuuper_herbal_tea.blocks.Big_Black_Pot;
import com.dbydd.suuuuuper_herbal_tea.items.*;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.Collection;

public class Registered_Items {

    public static final Item BIG_BLACK_POT_ITEM = new Big_Black_Pot_Item();
    public static final Item MOUNTAIN_TEA_LEAVE = new Tea_Leaves("mountain_tea_leave", (world, playerEntity) -> {
        playerEntity.move(MoverType.PLAYER, new Vec3d(0, 128, 0));
        playerEntity.addPotionEffect(new EffectInstance(Effects.SLOW_FALLING, 2000, 2));
    });
    public static final Item PLAIN_TEA_LEAVE = new Tea_Leaves("plain_tea_leave", (world, playerEntity) -> {
        playerEntity.addPotionEffect(new EffectInstance(Effects.SPEED, 2000, 2));
        playerEntity.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 2000, 2));
    });
    public static final Item OCEAN_TEA_LEAVE = new Tea_Leaves("ocean_tea_leave", (world, playerEntity) -> {
        playerEntity.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, 2000, 1));
        ItemHandlerHelper.giveItemToPlayer(playerEntity, new ItemStack(Items.TROPICAL_FISH));
    });
    public static final Item JUNGLE_TEA_LEAVE = new Tea_Leaves("jungle_tea_leave", (world, playerEntity) -> {
        playerEntity.addPotionEffect(new EffectInstance(Effects.LUCK, 2000, 2));
        EntityType.CAT.spawn(world.getWorld(), null, new StringTextComponent("your best friend"), playerEntity, playerEntity.getPosition(), SpawnReason.NATURAL, true, true);
    });
    public static final Item DESERT_TEA_LEAVE = new Tea_Leaves("desert_tea_leave", (world, playerEntity) -> {
        world.setBlockState(playerEntity.getPosition(), Blocks.WATER.getDefaultState(), 2);
    });
    public static final Item TROPICAL_TEA_LEAVE = new Tea_Leaves("tropical_tea_leave", (world, playerEntity) -> {
        world.getWorld().setRainStrength(1.0f);
    });
    public static final Item CLAY_TEA_LEAVE = new Tea_Leaves("clay_tea_leave", (world, playerEntity) -> {
        world.getWorld().addEntity(new ItemEntity(world.getWorld(), playerEntity.getPosX(), playerEntity.getPosY() + 3, playerEntity.getPosZ(), new ItemStack(Items.DEAD_BUSH, 64)));
    });
    public static final Item SNOW_TEA_LEAVE = new Tea_Leaves("snow_land_tea_leave", (world, playerEntity) -> {
        playerEntity.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 2000, 2));
        BlockPos position = playerEntity.getPosition();
        for (int i = 0; i <= 1; i++) {
            world.setBlockState(new BlockPos(position.getX(), position.getY() + i, position.getZ()), Blocks.ICE.getDefaultState(), 2);
        }
    });
    public static final Item SWAMP_TEA_LEAVE = new Tea_Leaves("swamp_tea_leave", (world, playerEntity) -> {
        for (int i = 0; i < 5; i++)
            EntityType.SLIME.spawn(world.getWorld(), null, new StringTextComponent("slime!"), playerEntity, playerEntity.getPosition(), SpawnReason.NATURAL, true, true);
    });
    public static final Item WORLD_TEA_TREE_TEA_LEAVE = new Tea_Leaves("world_tea_tree_tea_leave", (world, playerEntity) -> {
        playerEntity.respawnPlayer();
    });
    public static final Item GREENDATE = new GreenDate("green_date", (world, playerEntity) -> {
        Collection<EffectInstance> activePotionEffects = playerEntity.getActivePotionEffects();
        Collection<EffectInstance> activePotionEffectsGive = playerEntity.getActivePotionEffects();
        for (EffectInstance activePotionEffect : activePotionEffects) {
            int duration = activePotionEffect.getDuration();
            int amplifier = activePotionEffect.getAmplifier();
            Effect potion = activePotionEffect.getPotion();
            activePotionEffectsGive.add(new EffectInstance(potion, Math.round(duration * 1.3f), amplifier + 1));
        }
        playerEntity.clearActivePotions();
        activePotionEffectsGive.forEach(playerEntity::addPotionEffect);
    });
    public static final Item REDDATE = new GreenDate("red_date", (world, playerEntity) -> {
        Collection<EffectInstance> activePotionEffects = playerEntity.getActivePotionEffects();
        Collection<EffectInstance> activePotionEffectsGive = playerEntity.getActivePotionEffects();
        for (EffectInstance activePotionEffect : activePotionEffects) {
            int duration = activePotionEffect.getDuration();
            int amplifier = activePotionEffect.getAmplifier();
            Effect potion = activePotionEffect.getPotion();
            activePotionEffectsGive.add(new EffectInstance(potion, Math.round(duration * 1.5f), Math.round(amplifier * 1.5f)));
        }
        playerEntity.clearActivePotions();
        activePotionEffectsGive.forEach(playerEntity::addPotionEffect);
        ItemHandlerHelper.giveItemToPlayer(playerEntity, new ItemStack(Items.REDSTONE).setDisplayName(new StringTextComponent("nosebleed")));
    });
    public static final Item WOLFBERRY = new WolfBerry("wolfberry", (world, playerEntity) -> {
        playerEntity.setFireTimer(0);
        ItemHandlerHelper.giveItemToPlayer(playerEntity, new ItemStack(Items.REDSTONE).setDisplayName(new StringTextComponent("nosebleed")));
    });
    public static final Item HONEY_SUCKLE_ITEM = new HoneySuckle_Item((world, playerEntity) -> {
        World world1 = world.getWorld();
        if(world1.isRaining()){
            world1.setRainStrength(0);
        }else world1.setRainStrength(1);
        playerEntity.clearActivePotions();
    });

    public static void init() {
    }
}
