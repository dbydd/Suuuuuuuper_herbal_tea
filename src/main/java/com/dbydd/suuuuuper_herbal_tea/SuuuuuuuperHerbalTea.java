package com.dbydd.suuuuuper_herbal_tea;

import com.dbydd.suuuuuper_herbal_tea.blocks.BlockBase;
import com.dbydd.suuuuuper_herbal_tea.items.ItemBase;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.*;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.*;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;
import java.util.function.Supplier;

@Mod("suuuuuuuper_herbal_tea")
public class SuuuuuuuperHerbalTea {

    public static final String NAME = "suuuuuuuper_herbal_tea";
    public static final DeferredRegister<Item> ITEM_REGISTER = new DeferredRegister<>(ForgeRegistries.ITEMS, NAME);
    public static final DeferredRegister<Block> BLOCK_REGISTER = new DeferredRegister<>(ForgeRegistries.BLOCKS, NAME);
    public static final DeferredRegister<Fluid> FLUID_REGISTER = new DeferredRegister<>(ForgeRegistries.FLUIDS, NAME);
    public static final DeferredRegister<ModDimension> MOD_DIMENSION = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, NAME);
    public static final ItemGroup TAB = new Tab();

    static {
        InitElementsNeedToRegister();

        SuuuuuuuperHerbalTea.RegisterySingleBlock("tea_cup", RegisteredBlocks.TEA_CUP, RegisteredItems.TEA_CUP);
        SuuuuuuuperHerbalTea.RegisterySingleBlock("big_black_pot", RegisteredBlocks.BIG_BLACK_POT, RegisteredItems.BIG_BLACK_POT_ITEM);
        SuuuuuuuperHerbalTea.RegisterySingleBlockWithItem("sink", RegisteredBlocks.SINK);
        SuuuuuuuperHerbalTea.RegisterySingleBlockNoItem("tea_water_face", RegisteredBlocks.TEA_WATER_FACE);
        SuuuuuuuperHerbalTea.RegisterySingleBlockNoItem("water_face", RegisteredBlocks.WATER_FACE);
        SuuuuuuuperHerbalTea.RegisteryBlocks(BlockBase.registeries);
        SuuuuuuuperHerbalTea.RegisteryItems(ItemBase.registeries);
        SuuuuuuuperHerbalTea.RegisterySingleBlock("honeysuckle", RegisteredBlocks.HONEYSUCKLE, RegisteredItems.HONEY_SUCKLE_ITEM);
    }

    public SuuuuuuuperHerbalTea() {
        ITEM_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCK_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        FLUID_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegisteredParticleTypes.PARTICLE_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegisteredFeatures.FEATURES_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegisteredBiomes.BIOMES_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegisteredTileEntities.TILEENTITY_TYPE_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        RegisteredDecorators.DECORATORS_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
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

    public static void RegisterySingleBlockWithItem(String name, Block block) {
        BLOCK_REGISTER.register(name, () -> block);
        ITEM_REGISTER.register(name, () -> new BlockItem(block, new Item.Properties().group(TAB)));
    }

    public static void RegisterySingleBlockNoItem(String name, Block block) {
        BLOCK_REGISTER.register(name, () -> block);
    }

    public static RegistryObject<ModDimension> RegisteryDimenision(String name, ModDimension dimension) {
        return MOD_DIMENSION.register(name, () -> dimension);
    }

    private static void InitElementsNeedToRegister() {
        RegisteredBlocks.init();
        RegisteredItems.init();
        RegisteredFluids.init();
        RegisteredDimenisions.init();
        RegisteredFeatures.init();
    }

}

class Tab extends ItemGroup {

    Tab() {
        super(SuuuuuuuperHerbalTea.NAME);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(RegisteredItems.WORLD_TEA_TREE_TEA_LEAVE);
    }
}
