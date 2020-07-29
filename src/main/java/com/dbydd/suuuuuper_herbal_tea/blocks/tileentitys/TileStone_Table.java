package com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys;

import com.dbydd.suuuuuper_herbal_tea.interfaces.IPutableItem;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_TileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TileStone_Table extends TileEntity {
    private ItemStackHandler itemOnTable = new ItemStackHandler(2);

    public TileStone_Table() {
        super(Registered_TileEntities.TILE_STONE_TABLE_TYPE.get());
    }


    @Override
    public void read(CompoundNBT compound) {
        itemOnTable.deserializeNBT(compound.getCompound("itemontable"));
        super.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("itemontable", itemOnTable.serializeNBT());
        return super.write(compound);
    }

    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            ItemStack heldItem = player.getHeldItem(handIn);
            if (heldItem.getItem() instanceof IPutableItem) {
                if (itemOnTable.getStackInSlot(0) == ItemStack.EMPTY) {
                    itemOnTable.setStackInSlot(0, heldItem);
                    player.setHeldItem(handIn, ItemStack.EMPTY);
                    markDirty2();
                    return ActionResultType.SUCCESS;
                } else if (itemOnTable.getStackInSlot(1) == ItemStack.EMPTY) {
                    itemOnTable.setStackInSlot(1, heldItem);
                    player.setHeldItem(handIn, ItemStack.EMPTY);
                    markDirty2();
                    return ActionResultType.SUCCESS;
                }
            } else if (heldItem == ItemStack.EMPTY) {
                if (itemOnTable.getStackInSlot(0) != ItemStack.EMPTY) {
                    ItemHandlerHelper.giveItemToPlayer(player, itemOnTable.extractItem(0, itemOnTable.getStackInSlot(0).getCount(), false));
                    markDirty2();
                } else {
                    ItemHandlerHelper.giveItemToPlayer(player, itemOnTable.extractItem(1, itemOnTable.getStackInSlot(1).getCount(), false));
                    markDirty2();
                }
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.PASS;
    }

    public ItemStackHandler getItemOnTable() {
        return itemOnTable;
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(pos, 1, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        handleUpdateTag(pkt.getNbtCompound());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }

    @Override
    public void handleUpdateTag(CompoundNBT tag) {
        this.read(tag);
    }

    public void markDirty2() {
        world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
        super.markDirty();
    }
}
