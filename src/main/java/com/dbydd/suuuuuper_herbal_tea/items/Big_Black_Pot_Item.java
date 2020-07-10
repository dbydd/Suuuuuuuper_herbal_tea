package com.dbydd.suuuuuper_herbal_tea.items;

import com.dbydd.suuuuuper_herbal_tea.Suuuuuuuper_herbal_tea;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Block;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class Big_Black_Pot_Item extends BlockItem {
    public Big_Black_Pot_Item() {
        super(Registered_Block.BIG_BLACK_POT, new Properties().maxStackSize(1).group(Suuuuuuuper_herbal_tea.TAB));
    }
}
