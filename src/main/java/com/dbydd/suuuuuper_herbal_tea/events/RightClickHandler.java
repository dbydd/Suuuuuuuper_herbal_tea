package com.dbydd.suuuuuper_herbal_tea.events;

import com.dbydd.suuuuuper_herbal_tea.blocks.Earth_Stovetop;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Blocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class RightClickHandler {

    @SubscribeEvent
    public static void rightClick(PlayerInteractEvent.RightClickBlock event) {
        if (event.getSide() == LogicalSide.SERVER) {
            BlockPos pos = event.getPos();
            Block block = event.getWorld().getBlockState(pos).getBlock();
            World world = event.getWorld();
            PlayerEntity player = event.getPlayer();
            ItemStack off_hand = player.getHeldItem(Hand.OFF_HAND);
            ItemStack main_hand = player.getHeldItem(Hand.MAIN_HAND);
            if (block == Registered_Blocks.CHAIR) {
                event.setCanceled(true);
                Vec3d vec = new Vec3d(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
                final ArmorStandEntity dummy = new ArmorStandEntity(EntityType.ARMOR_STAND, event.getWorld());
                dummy.getDataManager().set(ArmorStandEntity.STATUS, (byte) (1 | 8 | 16));
                dummy.setInvisible(true);
                dummy.entityCollisionReduction = 1F;
                dummy.setNoGravity(true);
                dummy.setInvulnerable(true);
                dummy.lookAt(EntityAnchorArgument.Type.FEET, player.getLookVec());
                dummy.setPosition(vec.x, vec.y, vec.z);
                event.getWorld().addEntity(dummy);
                event.getPlayer().startRiding(dummy);
            } else if (off_hand.getToolTypes().contains(ToolType.SHOVEL) && player.isSneaking() && main_hand.getItem() == Items.CLAY_BALL && block == Blocks.BRICKS) {
                world.setBlockState(pos, Registered_Blocks.EARTH_STOVETOP.getDefaultState().with(Earth_Stovetop.FACING, Direction.fromAngle(player.getHorizontalFacing().getOpposite().getHorizontalAngle() + 90)));
                off_hand.setDamage(off_hand.getDamage() + 1);
                main_hand.shrink(1);
                player.setHeldItem(Hand.OFF_HAND, off_hand);
                player.setHeldItem(Hand.MAIN_HAND, main_hand);
            }
        }
    }

    @SubscribeEvent
    public static void stand(EntityMountEvent event) {
        if (event.isDismounting() && event.getEntityBeingMounted() instanceof ArmorStandEntity) {
            Entity entityBeingMounted = event.getEntityBeingMounted();
            if (event.getWorldObj().getBlockState(entityBeingMounted.getPosition()).getBlock() == Registered_Blocks.CHAIR && entityBeingMounted.isInvisible()) {
                entityBeingMounted.remove();
            }
        }
    }

}
