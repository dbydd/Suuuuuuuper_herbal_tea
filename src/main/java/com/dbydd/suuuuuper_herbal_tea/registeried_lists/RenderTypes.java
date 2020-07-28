package com.dbydd.suuuuuper_herbal_tea.registeried_lists;

import com.dbydd.suuuuuper_herbal_tea.fluids.Fluid_Base;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class RenderTypes {

    @SubscribeEvent
    public static void onRenderTypeSetup(FMLClientSetupEvent event) {

            RenderTypeLookup.setRenderLayer(Registered_Blocks.BIG_BLACK_POT, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(Registered_Blocks.TEA_CUP, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(Registered_Blocks.EARTH_STOVETOP, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(Registered_Blocks.HONEYSUCKLE, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(Registered_Blocks.JUJUCE_TREE_TREE, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(Registered_Blocks.STONE_TABLE, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(Registered_Blocks.TEA_TREE, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(Registered_Blocks.WOLF_BERRY_TREE, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(Registered_Blocks.WORLD_TEA_TREE_LEAVE, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(Registered_Blocks.WORLD_TEA_TREE_LEAVE_GLOW, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(Registered_Blocks.WORLD_TEA_TREE_FRUIT, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(Registered_Blocks.CHAIR, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(Registered_Blocks.WATER_FACE, RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(Registered_Blocks.TEA_WATER_FACE, RenderType.getTranslucent());
    }

    @SubscribeEvent
    public static void onFluidRenderTypeSetUp(FMLClientSetupEvent event){
            RenderTypeLookup.setRenderLayer(Registered_Fluids.TEA_WATER.fluid.get(), RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(Registered_Fluids.TEA_WATER.fluid_flowing.get(), RenderType.getTranslucent());
    }

}
