package com.dbydd.suuuuuper_herbal_tea.events;


import com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys.TileStone_Table;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_TileEntities;
import com.dbydd.suuuuuper_herbal_tea.tesr.Big_Black_Pot_TER;
import com.dbydd.suuuuuper_herbal_tea.tesr.Earth_Stovetop_TER;
import com.dbydd.suuuuuper_herbal_tea.tesr.Stone_Table_TER;
import com.dbydd.suuuuuper_herbal_tea.tesr.Tea_Cup_TER;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TerBinder {
    @SubscribeEvent
    public static void onClientEvent(FMLClientSetupEvent event) {
        ClientRegistry.bindTileEntityRenderer(Registered_TileEntities.TilE_BIG_BLACK_POT_TYPE.get(), Big_Black_Pot_TER::new);
        ClientRegistry.bindTileEntityRenderer(Registered_TileEntities.TILE_EARTH_STOVETOP_TYPE.get(),Earth_Stovetop_TER::new);
        ClientRegistry.bindTileEntityRenderer(Registered_TileEntities.TILE_STONE_TABLE_TYPE.get(), Stone_Table_TER::new);
        ClientRegistry.bindTileEntityRenderer(Registered_TileEntities.TEA_CUP.get(), Tea_Cup_TER::new);
    }
}
