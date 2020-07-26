package com.dbydd.suuuuuper_herbal_tea.blocks;

import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Items;
import com.dbydd.suuuuuper_herbal_tea.utils.MathUtils;
import com.dbydd.suuuuuper_herbal_tea.utils.RandomUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;
import java.util.Random;

public class World_Tea_Tree_Leave extends BlockBase{
    public World_Tea_Tree_Leave(Properties properties, String name) {
        super(properties.tickRandomly(), name, RenderType.getTranslucent());
    }

    @Override
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
        Random random = worldIn.getRandom();
        if(RandomUtils.outputBooleanByChance(random, 0.4d)){
            int count = random.nextInt(2);
            ItemEntity itemEntity = new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Registered_Items.WORLD_TEA_TREE_TEA_LEAVE, count));
            worldIn.addEntity(itemEntity);
        }
    }

}
