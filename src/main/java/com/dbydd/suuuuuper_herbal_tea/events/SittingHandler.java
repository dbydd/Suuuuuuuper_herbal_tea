package com.dbydd.suuuuuper_herbal_tea.events;

import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Blocks;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class SittingHandler {

    @SubscribeEvent
    public static void sit(PlayerInteractEvent.RightClickBlock event) {
        if (event.getSide() == LogicalSide.SERVER) {
            BlockPos pos = event.getPos();
            if (event.getWorld().getBlockState(pos).getBlock() == Registered_Blocks.STONE_CHAIR) {
                event.setCanceled(true);
                Vec3d vec = new Vec3d(pos.getX()+0.5, pos.getY() + 0.5, pos.getZ()+0.5);

                final ArmorStandEntity dummy = new ArmorStandEntity(EntityType.ARMOR_STAND, event.getWorld());
                dummy.getDataManager().set(ArmorStandEntity.STATUS, (byte) (1 | 8 | 16));
                dummy.setInvisible(true);
                dummy.entityCollisionReduction = 1F;
                dummy.setNoGravity(true);
                dummy.setInvulnerable(true);
                dummy.setPosition(vec.x, vec.y, vec.z);
                event.getWorld().addEntity(dummy);
                event.getPlayer().startRiding(dummy);
            }
        }
    }

    @SubscribeEvent
    public static void stand(EntityMountEvent event) {
        if (event.isDismounting() && event.getEntityBeingMounted() instanceof ArmorStandEntity) {
            Entity entityBeingMounted = event.getEntityBeingMounted();
            if (event.getWorldObj().getBlockState(entityBeingMounted.getPosition()).getBlock() == Registered_Blocks.STONE_CHAIR && entityBeingMounted.isInvisible()) {
                entityBeingMounted.remove();
            }
        }
    }

}
