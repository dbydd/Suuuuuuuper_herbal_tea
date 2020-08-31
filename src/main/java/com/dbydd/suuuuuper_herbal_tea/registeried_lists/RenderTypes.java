package com.dbydd.suuuuuper_herbal_tea.registeried_lists;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class RenderTypes {

    @SubscribeEvent
    public static void onRenderTypeSetup(FMLClientSetupEvent event) {

            RenderTypeLookup.setRenderLayer(RegisteredBlocks.BIG_BLACK_POT, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegisteredBlocks.TEA_CUP, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegisteredBlocks.EARTH_STOVETOP, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegisteredBlocks.HONEYSUCKLE, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegisteredBlocks.JUJUCE_TREE_TREE, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegisteredBlocks.STONE_TABLE, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegisteredBlocks.TEA_TREE, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegisteredBlocks.WOLF_BERRY_TREE, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegisteredBlocks.WORLD_TEA_TREE_LEAVE, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegisteredBlocks.WORLD_TEA_TREE_LEAVE_GLOW, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegisteredBlocks.WORLD_TEA_TREE_FRUIT, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegisteredBlocks.CHAIR, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegisteredBlocks.WATER_FACE, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegisteredBlocks.TEA_WATER_FACE, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegisteredBlocks.SINK, RenderType.getTranslucent());
    }

    @SubscribeEvent
    public static void onFluidRenderTypeSetUp(FMLClientSetupEvent event){
            RenderTypeLookup.setRenderLayer(RegisteredFluids.TEA_WATER.fluid.get(), RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(RegisteredFluids.TEA_WATER.fluid_flowing.get(), RenderType.getTranslucent());
    }

}
