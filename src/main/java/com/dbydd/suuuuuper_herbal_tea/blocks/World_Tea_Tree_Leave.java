package com.dbydd.suuuuuper_herbal_tea.blocks;

import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Items;
import com.dbydd.suuuuuper_herbal_tea.utils.MathUtils;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;
import java.util.Random;

public class World_Tea_Tree_Leave extends BlockBase{
    public World_Tea_Tree_Leave(Properties properties, String name) {
        super(properties, name, RenderType.getTranslucent());
    }

    @Override
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
        Random random = worldIn.getRandom();
        if(random.nextFloat()<=0.07f){
            int count = random.nextInt(2);
            ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Registered_Items.WORLD_TEA_TREE_TEA_LEAVE, count));
        }
    }
}
