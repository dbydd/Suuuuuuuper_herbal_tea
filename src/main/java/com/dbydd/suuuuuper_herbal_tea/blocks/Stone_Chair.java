package com.dbydd.suuuuuper_herbal_tea.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Stone_Chair extends BlockBase {
    public Stone_Chair() {
        super(Properties.create(Material.ROCK).notSolid().hardnessAndResistance(3.0f), "stone_chair", RenderType.getTranslucent());
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        Vec3d vec = new Vec3d(pos.getX(), pos.getY() + 0.5, pos.getZ());

        final ArmorStandEntity dummy = new ArmorStandEntity(EntityType.ARMOR_STAND, worldIn);
        dummy.getDataManager().set(ArmorStandEntity.STATUS, (byte) (1 | 8 | 16));
        dummy.setInvisible(true);
        dummy.entityCollisionReduction = 1F;
        dummy.setNoGravity(true);
        dummy.setInvulnerable(true);
        dummy.setPosition(vec.x, vec.y, vec.z);
        worldIn.addEntity(dummy);
        player.startRiding(dummy);

        return ActionResultType.SUCCESS;
    }
}
