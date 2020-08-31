package com.dbydd.suuuuuper_herbal_tea.blocks;

import com.dbydd.suuuuuper_herbal_tea.registeried_lists.RegisteredItems;
import com.dbydd.suuuuuper_herbal_tea.utils.RandomUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class WorldTeaTreeLeave extends BlockBase{
    public WorldTeaTreeLeave(Properties properties, String name) {
        super(properties.tickRandomly(), name);
    }

    @Override
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
        if(!worldIn.isRemote()) {
            Random random = worldIn.getRandom();
            if (RandomUtils.outputBooleanByChance(random, 0.4d)) {
                int count = random.nextInt(2);
                ItemEntity itemEntity = new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(RegisteredItems.WORLD_TEA_TREE_TEA_LEAVE, count));
                worldIn.addEntity(itemEntity);
            }
        }
        super.harvestBlock(worldIn, player, pos, state, te, stack);
    }

}
