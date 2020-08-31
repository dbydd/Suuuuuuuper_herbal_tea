package com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys;

import com.dbydd.suuuuuper_herbal_tea.items.BigSpoon;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.RegisteredItems;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.RegisteredTileEntities;
import com.dbydd.suuuuuper_herbal_tea.utils.IResourceItemHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;

public class TileTeaCup extends TileEntity {
    private IResourceItemHandler handler = new IResourceItemHandler(9);
    private FluidTank tank = new FluidTank(200);


    public TileTeaCup() {
        super(RegisteredTileEntities.TEA_CUP.get());
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("effects", handler.serializeNBT());
        compound.put("tank", tank.writeToNBT(new CompoundNBT()));
        return super.write(compound);
    }

    public FluidTank getTank() {
        return tank;
    }

    @Override
    public void read(CompoundNBT compound) {
        handler.deserializeNBT(compound.getCompound("resources"));
        tank.readFromNBT(compound.getCompound("tank"));
        super.read(compound);
    }

    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            ItemStack heldItem = player.getHeldItem(handIn);
            if (heldItem == ItemStack.EMPTY) {
                if (player.isSneaking()) {
                    ItemStack itemStack = new ItemStack(RegisteredItems.TEA_CUP);
                    itemStack.setTagInfo("BlockEntityTag", this.write(new CompoundNBT()));
                    ItemHandlerHelper.giveItemToPlayer(player, itemStack);
                    worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
                    return ActionResultType.SUCCESS;
                }
            } else if (heldItem.getItem() instanceof BigSpoon) {
                CompoundNBT tag = heldItem.getChildTag("spoonresources");
                if (tag!=null && !tag.getBoolean("isempty") && this.tank.getFluid().isEmpty() ) {
                    this.tank.readFromNBT(tag.getCompound("tank"));
                    this.handler.deserializeNBT(tag.getCompound("effects"));
                    heldItem.setTagInfo("spoonresources", BigSpoon.getEmptySpoon());
                    player.setHeldItem(handIn, heldItem);
                    markDirty2();
                    return ActionResultType.SUCCESS;
                }
            }
        }
        return ActionResultType.PASS;
    }

    public boolean canfill() {
        return tank.getFluidAmount() == 0;
    }

    public void fill(IResourceItemHandler resources, FluidStack fluidStack) {
        handler = resources;
        tank.setFluid(fluidStack);
        markDirty2();
    }

    public TileTeaCup(TileEntityType<?> tileEntityTypeIn, FluidTank tank) {
        super(tileEntityTypeIn);
        this.tank = tank;
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
        world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 2);
        super.markDirty();
    }
}
