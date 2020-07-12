package com.dbydd.suuuuuper_herbal_tea.items;

import com.dbydd.suuuuuper_herbal_tea.Suuuuuuuper_herbal_tea;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Blocks;
import net.minecraft.item.BlockItem;

public class Big_Black_Pot_Item extends BlockItem {
    public Big_Black_Pot_Item() {
        super(Registered_Blocks.BIG_BLACK_POT, new Properties().maxStackSize(1).group(Suuuuuuuper_herbal_tea.TAB));
    }
}
