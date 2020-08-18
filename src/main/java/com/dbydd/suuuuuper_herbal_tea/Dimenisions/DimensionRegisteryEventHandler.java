package com.dbydd.suuuuuper_herbal_tea.Dimenisions;

import com.dbydd.suuuuuper_herbal_tea.Suuuuuuuper_herbal_tea;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Dimenisions;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.omg.CORBA.PUBLIC_MEMBER;

@Mod.EventBusSubscriber
public class DimensionRegisteryEventHandler {
    public static final ResourceLocation WORLD_TEA_TREE_ID = new ResourceLocation(Suuuuuuuper_herbal_tea.NAME, "world_tea_tree");
    public static final ResourceLocation TEA_VILLA_ID = new ResourceLocation(Suuuuuuuper_herbal_tea.NAME, "tea_villa");
    public static DimensionType WORLD_TEA_TREE;
    public static DimensionType TEA_VILLA;

    @SubscribeEvent
    public static void onDimensionsRegistry(RegisterDimensionsEvent event) {
        if (DimensionType.byName(WORLD_TEA_TREE_ID) == null) {
            WORLD_TEA_TREE = DimensionManager.registerDimension(WORLD_TEA_TREE_ID, Registered_Dimenisions.WORLD_TEA_TREE.get(), null, true);
        }
        if(DimensionType.byName(TEA_VILLA_ID) == null) {
            TEA_VILLA = DimensionManager.registerDimension(TEA_VILLA_ID, Registered_Dimenisions.TEA_VILLA.get(), null, true);
        }
    }
}
