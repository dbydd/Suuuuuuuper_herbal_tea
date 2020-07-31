package com.dbydd.suuuuuper_herbal_tea.registeried_lists;

import com.dbydd.suuuuuper_herbal_tea.Dimenisions.DimensionRegisteryEventHandler;
import com.dbydd.suuuuuper_herbal_tea.items.*;
import com.dbydd.suuuuuper_herbal_tea.utils.RandomUtils;
import com.dbydd.suuuuuper_herbal_tea.utils.StructureUtils;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldInfo;
import net.minecraft.world.storage.loot.conditions.WeatherCheck;
import net.minecraftforge.common.util.ITeleporter;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.*;
import java.util.function.Function;

public class Registered_Items {

    public static final Item BIG_BLACK_POT_ITEM = new Big_Black_Pot_Item();
    public static final Item TEA_CUP = new TeaCupItem();
    public static final Item SPOON = new BigSpoon();
    public static final Item MOUNTAIN_TEA_LEAVE = new Tea_Leaves("mountain_tea_leave", (world, playerEntity, magnification) -> {
        playerEntity.setPositionAndUpdate(playerEntity.getPosX(), playerEntity.getPosY() + 128 * (int) Math.sqrt(magnification), playerEntity.getPosZ());
        playerEntity.addPotionEffect(new EffectInstance(Effects.SLOW_FALLING, magnification, (int) Math.sqrt(magnification)));
        playerEntity.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 1000 * (int) Math.sqrt(magnification), (int) Math.sqrt(magnification)));
    }, new EffectInstance(Effects.RESISTANCE, 100, 1));
    public static final Item PLAIN_TEA_LEAVE = new Tea_Leaves("plain_tea_leave", (world, playerEntity, magnification) -> {
        playerEntity.addPotionEffect(new EffectInstance(Effects.SPEED, 1000 * (int) Math.sqrt(magnification), (int) Math.sqrt(magnification)));
        playerEntity.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 1000 * (int) Math.sqrt(magnification), (int) Math.sqrt(magnification)));
    }, new EffectInstance(Effects.HASTE, 100, 2));
    public static final Item OCEAN_TEA_LEAVE = new Tea_Leaves("ocean_tea_leave", (world, playerEntity, magnification) -> {
        playerEntity.addPotionEffect(new EffectInstance(Effects.CONDUIT_POWER, 1000 * (int) Math.sqrt(magnification), magnification));
        ItemHandlerHelper.giveItemToPlayer(playerEntity, new ItemStack(Items.TROPICAL_FISH));
    }, new EffectInstance(Effects.WATER_BREATHING, 100, 1));
    public static final Item JUNGLE_TEA_LEAVE = new Tea_Leaves("jungle_tea_leave", (world, playerEntity, magnification) -> {
        playerEntity.addPotionEffect(new EffectInstance(Effects.LUCK, 1000 * (int) Math.sqrt(magnification), (int) Math.sqrt(magnification)));
        EntityType.CAT.spawn(world.getWorld(), null, new StringTextComponent("your best friend"), playerEntity, playerEntity.getPosition(), SpawnReason.NATURAL, true, true);
    }, new EffectInstance(Effects.NIGHT_VISION, 100, 1));
    public static final Item DESERT_TEA_LEAVE = new Tea_Leaves("desert_tea_leave", (world, playerEntity, magnification) -> {
        world.setBlockState(playerEntity.getPosition(), Blocks.WATER.getDefaultState(), 2);
    }, new EffectInstance(Effects.LUCK, 100, 2));
    public static final Item TROPICAL_TEA_LEAVE = new Tea_Leaves("tropical_tea_leave", (world, playerEntity, magnification) -> {
        world.getWorld().setRainStrength(1.0f * magnification);
    }, new EffectInstance(Effects.JUMP_BOOST, 100, 2));
    public static final Item CLAY_TEA_LEAVE = new Tea_Leaves("clay_tea_leave", (world, playerEntity, magnification) -> {
        ItemHandlerHelper.giveItemToPlayer(playerEntity, new ItemStack(Items.DEAD_BUSH, (int) Math.sqrt(magnification) + 1));
    }, new EffectInstance(Effects.SATURATION, 100, 2));
    public static final Item SNOW_TEA_LEAVE = new Tea_Leaves("snow_land_tea_leave", (world, playerEntity, magnification) -> {
        playerEntity.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 1000 * (int) Math.sqrt(magnification), (int) Math.sqrt(magnification)));
        BlockPos position = playerEntity.getPosition();
        Random random = world.getRandom();
        Map<Double, BlockState> map = new HashMap<>();
        map.put(0.5d, Blocks.ICE.getDefaultState());
        map.put(0.1d, Blocks.FROSTED_ICE.getDefaultState());
        map.put(0.3d, Blocks.BLUE_ICE.getDefaultState());
        map.put(0.2d, Blocks.SNOW_BLOCK.getDefaultState());
        for (int i = 0; i <= random.nextInt(6); i++) {
            BlockPos blockPos = RandomUtils.nextPos(random, magnification * 2, true, position);
            StructureUtils.PlaceCone(world, magnification / (random.nextInt(7) + 1), blockPos, (256 - blockPos.getY()) - random.nextInt(magnification), map, random);
        }
    }, new EffectInstance(Effects.FIRE_RESISTANCE, 100, 2));
    public static final Item SWAMP_TEA_LEAVE = new Tea_Leaves("swamp_tea_leave", (world, playerEntity, magnification) -> {
        for (int i = 0; i < 4 * magnification; i++) {
            EntityType.SLIME.spawn(world.getWorld(), null, new StringTextComponent("slime!"), playerEntity, playerEntity.getPosition(), SpawnReason.NATURAL, true, true);
        }
    }, new EffectInstance(Effects.STRENGTH, 100, 2));
    public static final Item WORLD_TEA_TREE_TEA_LEAVE = new Tea_Leaves("world_tea_tree_tea_leave", (world, playerEntity, magnification) -> {
        playerEntity.changeDimension(DimensionType.byName(DimensionRegisteryEventHandler.DIMENSION_ID), new ITeleporter() {
            @Override
            public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
                entity = repositionEntity.apply(false);
                entity.setPositionAndUpdate(0, 180, 0);
                return entity;
            }
        });
        World world1 = world.getWorld();
        WorldInfo worldInfo = world1.getWorldInfo();
        worldInfo.setDayTime((world1.getDayTime() + 4321 * (int) Math.sqrt(magnification)) % 36000);
    }, new EffectInstance(Effects.RESISTANCE, 100, 5));
    public static final Item GREENDATE = new GreenDate("green_date", (world, playerEntity, magnification) -> {
        Collection<EffectInstance> activePotionEffects = playerEntity.getActivePotionEffects();
        Collection<EffectInstance> activePotionEffectsGive = playerEntity.getActivePotionEffects();
        for (EffectInstance activePotionEffect : activePotionEffects) {
            int duration = activePotionEffect.getDuration();
            int amplifier = activePotionEffect.getAmplifier();
            Effect potion = activePotionEffect.getPotion();
            activePotionEffectsGive.add(new EffectInstance(potion, Math.round(duration * ((int) Math.sqrt(magnification) * 0.7f)), amplifier + (int) Math.sqrt(magnification)));
        }
        playerEntity.clearActivePotions();
        activePotionEffectsGive.forEach(playerEntity::addPotionEffect);
    });
    public static final Item REDDATE = new GreenDate("red_date", (world, playerEntity, magnification) -> {
        Collection<EffectInstance> activePotionEffects = playerEntity.getActivePotionEffects();
        List<EffectInstance> activePotionEffectsGive = new ArrayList<>();
        for (EffectInstance activePotionEffect : activePotionEffects) {
            int duration = activePotionEffect.getDuration();
            int amplifier = activePotionEffect.getAmplifier();
            Effect potion = activePotionEffect.getPotion();
            activePotionEffectsGive.add(new EffectInstance(potion, Math.round(duration * (int) Math.sqrt(magnification)), Math.round(amplifier * ((int) Math.sqrt(magnification * 0.5)))));
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
        if (!world.isRemote()) {
            WorldInfo worldInfo = world1.getWorldInfo();
            if (world1.isRaining()) {
                worldInfo.setRaining(false);
                world1.setRainStrength(0);
                if(worldInfo.isThundering()) {
                    worldInfo.setThundering(false);
                    worldInfo.setThunderTime(0);
                }
            } else {
                worldInfo.setRaining(true);
                worldInfo.setRainTime(1000 * (int) Math.sqrt(magnification));
                worldInfo.setThundering(true);
                worldInfo.setThunderTime(1000 * (int) Math.sqrt(magnification));
                world1.rainingStrength = (int) Math.sqrt(magnification);
                world1.thunderingStrength = ((int) Math.sqrt(magnification));
            }
        }
        playerEntity.clearActivePotions();
    });

    public static void init() {
    }
}
