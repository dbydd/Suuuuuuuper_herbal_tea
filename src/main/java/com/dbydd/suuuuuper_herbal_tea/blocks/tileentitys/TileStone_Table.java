package com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys;

import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_TileEntities;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.items.ItemStackHandler;

public class TileStone_Table extends TileEntity {
    private ItemStackHandler handler = new ItemStackHandler(1);

    public TileStone_Table() {
        super(Registered_TileEntities.TILE_STONE_TABLE_TYPE.get());
    }



}
