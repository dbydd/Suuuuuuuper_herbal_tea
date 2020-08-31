package com.dbydd.suuuuuper_herbal_tea.items;

import com.dbydd.suuuuuper_herbal_tea.SuuuuuuuperHerbalTea;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.RegisteredBlocks;
import net.minecraft.item.BlockItem;

public class BigBlackPotItem extends BlockItem {
    public BigBlackPotItem() {
        super(RegisteredBlocks.BIG_BLACK_POT, new Properties().maxStackSize(1).group(SuuuuuuuperHerbalTea.TAB));
    }
}
