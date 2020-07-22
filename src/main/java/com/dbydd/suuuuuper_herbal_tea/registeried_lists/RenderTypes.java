package com.dbydd.suuuuuper_herbal_tea.registeried_lists;

import com.dbydd.suuuuuper_herbal_tea.fluids.Fluid_Base;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RenderTypes {
    public static Map<Block, RenderType> blockRenderTypeMap = new HashMap<>();
    public static List<Fluid_Base> fluidRenderLists = new ArrayList<>();

    @SubscribeEvent
    public static void onRenderTypeSetup(FMLClientSetupEvent event) {
        for (Map.Entry<Block, RenderType> entry : blockRenderTypeMap.entrySet()) {
            RenderTypeLookup.setRenderLayer(entry.getKey(), entry.getValue());
        }
    }

    @SubscribeEvent
    public static void onFluidRenderTypeSetUp(FMLClientSetupEvent event){
        for (Fluid_Base fluidRenderList : fluidRenderLists) {
            RenderTypeLookup.setRenderLayer(fluidRenderList.fluid.get(), RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(fluidRenderList.fluid_flowing.get(), RenderType.getTranslucent());
        }
    }

}
