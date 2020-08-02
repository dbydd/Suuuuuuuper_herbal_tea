package com.dbydd.suuuuuper_herbal_tea.blocks;

import com.dbydd.suuuuuper_herbal_tea.particle.world_tea_tree_fruit_particle.World_Tea_Tree_Fruit_ParticleData;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_ParticleTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.util.ITeleporter;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class World_Tea_Tree_Fruit extends BlockBase {

    public World_Tea_Tree_Fruit() {
        super(Properties.create(Material.PLANTS).hardnessAndResistance(-1.0f).notSolid().lightValue(12).harvestLevel(3).sound(SoundType.PLANT).tickRandomly(), "world_tea_tree_fruit");
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {

            List<DimensionType> dimensiontypes = new ArrayList<>();
            DimensionManager.getRegistry().forEach(dimensiontypes::add);
            int size = dimensiontypes.size();
            int l = Math.abs((int)(pos.toLong() % size));
            DimensionType dimensiontype = dimensiontypes.get(l);
            player.changeDimension(dimensiontype, new ITeleporter() {
                @Override
                public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
                    Random random = worldIn.getRandom();
                    entity = repositionEntity.apply(false);
                    entity.setPositionAndUpdate(pos.getX() + random.nextInt(16), pos.getY()+8-random.nextInt(16), pos.getZ() + random.nextInt(16));
                    return entity;
                }
            });

        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        for (int i = 0; i <= random.nextInt(8) + 1; i++) {
            worldIn.spawnParticle(new World_Tea_Tree_Fruit_ParticleData(new Vec3d(8 - random.nextInt(16), 8 - random.nextInt(16), 8 - random.nextInt(16)), new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256), random.nextInt(256)), random.nextFloat()), (double) pos.getX() + 8 - random.nextInt(16), (double) pos.getY() + 8 - random.nextInt(16), (double) pos.getZ() + 8 - random.nextInt(16), random.nextInt(8) + 8, 0.5 - random.nextDouble(), 0.5 - random.nextDouble(), 0.5 - random.nextDouble(), 0.5 - random.nextDouble());
        }
        super.randomTick(state, worldIn, pos, random);
    }

    @Override
    public int tickRate(IWorldReader worldIn) {
        return 80;
    }
}
