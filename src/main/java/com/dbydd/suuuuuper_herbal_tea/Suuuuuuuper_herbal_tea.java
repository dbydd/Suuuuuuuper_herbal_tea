package com.dbydd.suuuuuper_herbal_tea;

import com.dbydd.suuuuuper_herbal_tea.biomes.Tea_Villa;
import com.dbydd.suuuuuper_herbal_tea.blocks.BlockBase;
import com.dbydd.suuuuuper_herbal_tea.items.ItemBase;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Features;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Block;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Items;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.*;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.security.PublicKey;
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
    public static RegistryObject<Tea_Villa> tea_villa;
    public static final ItemGroup TAB = new Tab();

    static {
        InitElementsNeedToRegister();

        Suuuuuuuper_herbal_tea.RegisteryItems(ItemBase.registeries);
        Suuuuuuuper_herbal_tea.RegisteryBlocks(BlockBase.registeries);
        RegisteryBiomes();
    }

    public Suuuuuuuper_herbal_tea() {
        ITEM_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCK_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        FLUID_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        FEATURES_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        BIOMES_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
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

    public static void RegisteryBiomes() {
         tea_villa = BIOMES_REGISTER.register("tea_villa", () -> {
            return new Tea_Villa(new Biome.Builder().category(Biome.Category.TAIGA)
                    .surfaceBuilder(SurfaceBuilder.DEFAULT,
                            new SurfaceBuilderConfig(Registered_Block.TEST_TREE.getDefaultState(), Blocks.FARMLAND.getDefaultState(), Blocks.PODZOL.getDefaultState())
                    )
                    .scale(3f)
                    .downfall(0.5f)
                    .precipitation(Biome.RainType.RAIN)
                    .depth(1f)
                    .temperature(0.7f)
                    .waterColor(0x0c0a15)
                    .waterFogColor(0x632ebf)
            );
        });
    }

    private static void InitElementsNeedToRegister() {
        Registered_Block.init();
        Registered_Items.init();
        Features.init();
    }

}

class Tab extends ItemGroup {

    Tab() {
        super(Suuuuuuuper_herbal_tea.NAME);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Items.OAK_LEAVES);
    }
}
