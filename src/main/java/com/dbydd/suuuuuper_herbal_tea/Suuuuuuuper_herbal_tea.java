package com.dbydd.suuuuuper_herbal_tea;

import com.dbydd.suuuuuper_herbal_tea.blocks.BlockBase;
import com.dbydd.suuuuuper_herbal_tea.items.ItemBase;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.*;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.*;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.common.ModDimension;
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
    public static final DeferredRegister<ModDimension> MOD_DIMENSION = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, NAME);
    public static final ItemGroup TAB = new Tab();

    static {
        InitElementsNeedToRegister();

        Suuuuuuuper_herbal_tea.RegisteryItems(ItemBase.registeries);
        Suuuuuuuper_herbal_tea.RegisteryBlocks(BlockBase.registeries);
        Suuuuuuuper_herbal_tea.RegisterySingleBlock("big_black_pot", Registered_Blocks.BIG_BLACK_POT, Registered_Items.BIG_BLACK_POT_ITEM);
    }

    public Suuuuuuuper_herbal_tea() {
        ITEM_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCK_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        FLUID_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        Registered_Features.FEATURES_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        Registered_Biomes.BIOMES_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        Registered_TileEntities.TILEENTITY_TYPE_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        MOD_DIMENSION.register(FMLJavaModLoadingContext.get().getModEventBus());
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

    public static void RegisterySingleBlock(String name, Block block, Item item) {
        BLOCK_REGISTER.register(name, () -> block);
        ITEM_REGISTER.register(name, () -> item);
    }

    public static RegistryObject<ModDimension> RegisteryDimenision(String name, ModDimension dimension) {
        return MOD_DIMENSION.register(name, () -> dimension);
    }

    private static void InitElementsNeedToRegister() {
        Registered_Blocks.init();
        Registered_Items.init();
        Registered_Fluids.init();
        Registered_Dimenisions.init();
        Registered_Features.init();
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
