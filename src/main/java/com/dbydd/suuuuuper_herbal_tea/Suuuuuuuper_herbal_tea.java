package com.dbydd.suuuuuper_herbal_tea;

import com.dbydd.suuuuuper_herbal_tea.blocks.Big_Black_Pot;
import com.dbydd.suuuuuper_herbal_tea.blocks.BlockBase;
import com.dbydd.suuuuuper_herbal_tea.blocks.Earth_Stovetop;
import com.dbydd.suuuuuper_herbal_tea.items.ItemBase;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Block;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Fluids;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Items;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.*;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;
import java.util.function.Supplier;

@Mod("suuuuuuuper_herbal_tea")
public class Suuuuuuuper_herbal_tea {

    public static final String NAME = "suuuuuuuper_herbal_tea";
    public static final DeferredRegister<Item> ITEM_REGISTER = new DeferredRegister<>(ForgeRegistries.ITEMS, NAME);
    public static final DeferredRegister<Block> BLOCK_REGISTER = new DeferredRegister<>(ForgeRegistries.BLOCKS, NAME);
    public static final DeferredRegister<Fluid> FLUID_REGISTER = new DeferredRegister<>(ForgeRegistries.FLUIDS, NAME);
    public static final DeferredRegister<Feature<?>> FEATURES_REGISTER = new DeferredRegister<>(ForgeRegistries.FEATURES, NAME);
    public static final DeferredRegister<Biome> BIOMES_REGISTER = new DeferredRegister<>(ForgeRegistries.BIOMES, NAME);
    public static final DeferredRegister<TileEntityType<?>> TILEENTITY_REGISTER = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, NAME);
    public static final ItemGroup TAB = new Tab();

    static {
        InitElementsNeedToRegister();

        Suuuuuuuper_herbal_tea.RegisteryItems(ItemBase.registeries);
        Suuuuuuuper_herbal_tea.RegisteryBlocks(BlockBase.registeries);
        BLOCK_REGISTER.register(Earth_Stovetop.name,()->Registered_Block.EARTH_STOVETOP);
        BLOCK_REGISTER.register(Big_Black_Pot.name,()->Registered_Block.BIG_BLACK_POT);
        ITEM_REGISTER.register(Big_Black_Pot.name, ()->Registered_Items.BIG_BLACK_POT_ITEM);
        RegisteryBiomes();
    }

    public Suuuuuuuper_herbal_tea() {
        ITEM_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCK_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        FLUID_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        FEATURES_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        BIOMES_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILEENTITY_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static void RegisteryItems(Map<String, Supplier<Item>> map) {
        for (Map.Entry<String, Supplier<Item>> entry : map.entrySet()) {
            ITEM_REGISTER.register(entry.getKey(), entry.getValue());
        }
    }

    public static void RegisteryBlocks(Map<String, Supplier<Block>> map) {
        for (Map.Entry<String, Supplier<Block>> entry : map.entrySet()) {
            BLOCK_REGISTER.register(entry.getKey(), entry.getValue());
            ITEM_REGISTER.register(entry.getKey(), () -> new BlockItem(entry.getValue().get(), new Item.Properties().group(TAB)));
        }
    }

    public static RegistryObject<? extends TileEntityType<?>> RegistryTileentityType(String name, TileEntityType<?> tileEntityType){
            return TILEENTITY_REGISTER.register("tileentity_"+name, () -> tileEntityType);
    }


    public static void RegisteryBiomes() {
    }

    private static void InitElementsNeedToRegister() {
        Registered_Block.init();
        Registered_Items.init();
        Registered_Fluids.init();
    }

}

class Tab extends ItemGroup {

    Tab() {
        super(Suuuuuuuper_herbal_tea.NAME);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Registered_Items.TEA_LEAVE);
    }
}
