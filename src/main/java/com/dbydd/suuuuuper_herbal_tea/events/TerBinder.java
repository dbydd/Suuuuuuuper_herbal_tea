package com.dbydd.suuuuuper_herbal_tea.events;


import com.dbydd.suuuuuper_herbal_tea.registeried_lists.RegisteredTileEntities;
import com.dbydd.suuuuuper_herbal_tea.special_render.BigBlackPotTER;
import com.dbydd.suuuuuper_herbal_tea.special_render.EarthStovetopTER;
import com.dbydd.suuuuuper_herbal_tea.special_render.StoneTableTER;
import com.dbydd.suuuuuper_herbal_tea.special_render.TeaCupTER;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TerBinder {
    @SubscribeEvent
    public static void onClientEvent(FMLClientSetupEvent event) {
        ClientRegistry.bindTileEntityRenderer(RegisteredTileEntities.TilE_BIG_BLACK_POT_TYPE.get(), BigBlackPotTER::new);
        ClientRegistry.bindTileEntityRenderer(RegisteredTileEntities.TILE_EARTH_STOVETOP_TYPE.get(), EarthStovetopTER::new);
        ClientRegistry.bindTileEntityRenderer(RegisteredTileEntities.TILE_STONE_TABLE_TYPE.get(), StoneTableTER::new);
        ClientRegistry.bindTileEntityRenderer(RegisteredTileEntities.TEA_CUP.get(), TeaCupTER::new);
    }
}
