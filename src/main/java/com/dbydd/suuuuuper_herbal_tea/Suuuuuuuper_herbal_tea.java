package com.dbydd.suuuuuper_herbal_tea;

import com.dbydd.suuuuuper_herbal_tea.blocks.BlockBase;
import com.dbydd.suuuuuper_herbal_tea.items.ItemBase;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Block;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.Mod;
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
    public static final ItemGroup TAB = new Tab();

    static {
        InitListsNeedToRegister();

        Suuuuuuuper_herbal_tea.RegisteryItems(ItemBase.registeries);
        Suuuuuuuper_herbal_tea.RegisteryBlocks(BlockBase.registeries);
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

    private static void InitListsNeedToRegister() {
        Registered_Block.init();
    }

}

class Tab extends ItemGroup{

    Tab() {
        super(Suuuuuuuper_herbal_tea.NAME);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Items.OAK_LEAVES);
    }
}
