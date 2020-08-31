package com.dbydd.suuuuuper_herbal_tea.dimenisions;

import com.dbydd.suuuuuper_herbal_tea.SuuuuuuuperHerbalTea;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.RegisteredDimenisions;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class DimensionRegisteryEventHandler {
    public static final ResourceLocation WORLD_TEA_TREE_ID = new ResourceLocation(SuuuuuuuperHerbalTea.NAME, "world_tea_tree");
    public static final ResourceLocation TEA_VILLA_ID = new ResourceLocation(SuuuuuuuperHerbalTea.NAME, "tea_villa");
    public static DimensionType WORLD_TEA_TREE;
    public static DimensionType TEA_VILLA;

    @SubscribeEvent
    public static void onDimensionsRegistry(RegisterDimensionsEvent event) {
        if (DimensionType.byName(WORLD_TEA_TREE_ID) == null) {
            WORLD_TEA_TREE = DimensionManager.registerDimension(WORLD_TEA_TREE_ID, RegisteredDimenisions.WORLD_TEA_TREE.get(), null, true);
        }
        if (DimensionType.byName(TEA_VILLA_ID) == null) {
            TEA_VILLA = DimensionManager.registerDimension(TEA_VILLA_ID, RegisteredDimenisions.TEA_VILLA.get(), null, true);
        }
    }
}
