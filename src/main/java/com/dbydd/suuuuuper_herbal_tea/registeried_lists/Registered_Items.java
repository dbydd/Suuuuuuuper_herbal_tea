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
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.Collection;

public class Registered_Items {

    public static final Item BIG_BLACK_POT_ITEM = new Big_Black_Pot_Item();
    public static final Item TEA_CUP = new TeaCupItem();
    public static final Item SPOON =new BigSpoon();
    public static final Item MOUNTAIN_TEA_LEAVE = new Tea_Leaves("mountain_tea_leave", (world, playerEntity, magnification) -> {
        playerEntity.move(MoverType.PLAYER, new Vec3d(0, 128*magnification, 0));
        playerEntity.addPotionEffect(new EffectInstance(Effects.SLOW_FALLING, 2000*magnification, 2*magnification));
        playerEntity.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 2000*magnification, 2*magnification));
    }, new EffectInstance(Effects.RESISTANCE, 100, 1));
    public static final Item PLAIN_TEA_LEAVE = new Tea_Leaves("plain_tea_leave", (world, playerEntity, magnification) -> {
        playerEntity.addPotionEffect(new EffectInstance(Effects.SPEED, 2000*magnification, 2*magnification));
        playerEntity.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 2000*magnification, 2*magnification));
    }, new EffectInstance(Effects.HASTE, 100, 2));
    public static final Item OCEAN_TEA_LEAVE = new Tea_Leaves("ocean_tea_leave", (world, playerEntity, magnification) -> {
        playerEntity.addPotionEffect(new EffectInstance(Effects.CONDUIT_POWER, 2000*magnification, magnification));
        ItemHandlerHelper.giveItemToPlayer(playerEntity, new ItemStack(Items.TROPICAL_FISH));
    }, new EffectInstance(Effects.WATER_BREATHING, 100, 1));
    public static final Item JUNGLE_TEA_LEAVE = new Tea_Leaves("jungle_tea_leave", (world, playerEntity, magnification) -> {
        playerEntity.addPotionEffect(new EffectInstance(Effects.LUCK, 2000*magnification, 2*magnification));
        EntityType.CAT.spawn(world.getWorld(), null, new StringTextComponent("your best friend"), playerEntity, playerEntity.getPosition(), SpawnReason.NATURAL, true, true);
    }, new EffectInstance(Effects.NIGHT_VISION, 100, 1));
    public static final Item DESERT_TEA_LEAVE = new Tea_Leaves("desert_tea_leave", (world, playerEntity, magnification) -> {
        world.setBlockState(playerEntity.getPosition(), Blocks.WATER.getDefaultState(), 2);
    }, new EffectInstance(Effects.LUCK, 100, 2));
    public static final Item TROPICAL_TEA_LEAVE = new Tea_Leaves("tropical_tea_leave", (world, playerEntity, magnification) -> {
        world.getWorld().setRainStrength(1.0f*magnification);
    }, new EffectInstance(Effects.JUMP_BOOST, 100, 2));
    public static final Item CLAY_TEA_LEAVE = new Tea_Leaves("clay_tea_leave", (world, playerEntity, magnification) -> {
        world.getWorld().addEntity(new ItemEntity(world.getWorld(), playerEntity.getPosX(), playerEntity.getPosY() + 3, playerEntity.getPosZ(), new ItemStack(Items.DEAD_BUSH, (64*magnification)%64)));
    }, new EffectInstance(Effects.SATURATION, 100, 2));
    public static final Item SNOW_TEA_LEAVE = new Tea_Leaves("snow_land_tea_leave", (world, playerEntity, magnification) -> {
        playerEntity.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 2000*magnification, 2*magnification));
        BlockPos position = playerEntity.getPosition();
        for (int i = 0; i <= magnification; i++) {
            world.setBlockState(new BlockPos(position.getX(), position.getY() + i, position.getZ()), Blocks.ICE.getDefaultState(), 2);
        }
    }, new EffectInstance(Effects.FIRE_RESISTANCE, 100, 2));
    public static final Item SWAMP_TEA_LEAVE = new Tea_Leaves("swamp_tea_leave", (world, playerEntity, magnification) -> {
        for (int i = 0; i < 4*magnification; i++)
            EntityType.SLIME.spawn(world.getWorld(), null, new StringTextComponent("slime!"), playerEntity, playerEntity.getPosition(), SpawnReason.NATURAL, true, true);
    }, new EffectInstance(Effects.STRENGTH, 100, 2));
    public static final Item WORLD_TEA_TREE_TEA_LEAVE = new Tea_Leaves("world_tea_tree_tea_leave", (world, playerEntity, magnification) -> {
        playerEntity.respawnPlayer();
        World world1 = world.getWorld();
        world1.setDayTime((world1.getDayTime() + 18000*magnification) % 36000);
    }, new EffectInstance(Effects.RESISTANCE, 100, 5));
    public static final Item GREENDATE = new GreenDate("green_date", (world, playerEntity, magnification) -> {
        Collection<EffectInstance> activePotionEffects = playerEntity.getActivePotionEffects();
        Collection<EffectInstance> activePotionEffectsGive = playerEntity.getActivePotionEffects();
        for (EffectInstance activePotionEffect : activePotionEffects) {
            int duration = activePotionEffect.getDuration();
            int amplifier = activePotionEffect.getAmplifier();
            Effect potion = activePotionEffect.getPotion();
            activePotionEffectsGive.add(new EffectInstance(potion, Math.round(duration * 1.3f)*magnification, amplifier + magnification));
        }
        playerEntity.clearActivePotions();
        activePotionEffectsGive.forEach(playerEntity::addPotionEffect);
    });
    public static final Item REDDATE = new GreenDate("red_date", (world, playerEntity, magnification) -> {
        Collection<EffectInstance> activePotionEffects = playerEntity.getActivePotionEffects();
        Collection<EffectInstance> activePotionEffectsGive = playerEntity.getActivePotionEffects();
        for (EffectInstance activePotionEffect : activePotionEffects) {
            int duration = activePotionEffect.getDuration();
            int amplifier = activePotionEffect.getAmplifier();
            Effect potion = activePotionEffect.getPotion();
            activePotionEffectsGive.add(new EffectInstance(potion, Math.round(duration * 1.5f)*magnification, Math.round(amplifier * 1.5f)*magnification));
        }
        playerEntity.clearActivePotions();
        activePotionEffectsGive.forEach(playerEntity::addPotionEffect);
        ItemHandlerHelper.giveItemToPlayer(playerEntity, new ItemStack(Items.REDSTONE, magnification).setDisplayName(new TranslationTextComponent("nosebleed")));
    });
    public static final Item WOLFBERRY = new WolfBerry("wolfberry", (world, playerEntity, magnification) -> {
        playerEntity.setFireTimer(0);
        ItemHandlerHelper.giveItemToPlayer(playerEntity, new ItemStack(Items.REDSTONE, magnification).setDisplayName(new TranslationTextComponent("nosebleed")));
    });
    public static final Item HONEY_SUCKLE_ITEM = new HoneySuckle_Item((world, playerEntity, magnification) -> {
        World world1 = world.getWorld();
        if (world1.isRaining()) {
            world1.setRainStrength(0);
        } else world1.setRainStrength(magnification);
        playerEntity.clearActivePotions();
    });

    public static void init() {
    }
}
