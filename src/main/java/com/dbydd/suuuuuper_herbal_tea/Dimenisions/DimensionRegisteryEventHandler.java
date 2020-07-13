package com.dbydd.suuuuuper_herbal_tea.Dimenisions;

import com.dbydd.suuuuuper_herbal_tea.Suuuuuuuper_herbal_tea;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Dimenisions;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class DimensionRegisteryEventHandler {
    public static final ResourceLocation DIMENSION_ID = new ResourceLocation(Suuuuuuuper_herbal_tea.NAME, "world_tea_tree");
    public static DimensionType DIMENSION_TYPE;

    @SubscribeEvent
    public static void onDimensionsRegistry(RegisterDimensionsEvent event) {
        if (DimensionType.byName(DIMENSION_ID) == null) {
            DIMENSION_TYPE = DimensionManager.registerDimension(DIMENSION_ID, Registered_Dimenisions.world_tea_tree.get(), null, true);
        }
    }
}
