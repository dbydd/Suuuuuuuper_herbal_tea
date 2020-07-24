package com.dbydd.suuuuuper_herbal_tea.items;

import com.dbydd.suuuuuper_herbal_tea.Suuuuuuuper_herbal_tea;
import com.dbydd.suuuuuper_herbal_tea.interfaces.IPutableItem;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Blocks;
import com.dbydd.suuuuuper_herbal_tea.utils.IResourceItemHandler;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.world.World;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class TeaCupItem extends BlockItem implements IPutableItem {
    public TeaCupItem() {
        super(Registered_Blocks.TEA_CUP, new Properties().maxStackSize(1).group(Suuuuuuuper_herbal_tea.TAB).food(new Food.Builder().setAlwaysEdible().hunger(1).build()));
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 20;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        if (!worldIn.isRemote()) {
            if (entityLiving instanceof PlayerEntity) {
                CompoundNBT blockEntityTag = stack.getChildTag("BlockEntityTag");
                IResourceItemHandler effects = new IResourceItemHandler(9);
                if (!blockEntityTag.getBoolean("isempty")) {
                    effects.deserializeNBT(blockEntityTag.getCompound("effects"));
                    effects.useResources(stack, worldIn, entityLiving);
                    blockEntityTag.put("effects", new IResourceItemHandler(9).serializeNBT());
                    blockEntityTag.put("tank", new FluidTank(200).writeToNBT(new CompoundNBT()));
                    blockEntityTag.putBoolean("isempty", true);
                    stack.setTag(blockEntityTag);
                }
                return stack;
            }
        }
        return stack;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

//    @Override
//    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
//        World world = context.getWorld();
//        if(!world.isRemote()) {
//            ActionResultType actionResultType = tryPlace(new BlockItemUseContext(context));
//            if (actionResultType.isSuccess()) {
//                return actionResultType;
//            }
//            CompoundNBT tag = stack.getChildTag("BlockEntityTag");
//            FluidTank tank = new FluidTank(200);
//            if (tag != null) {
//                CompoundNBT tanktag = tag.getCompound("tank");
//                tank.readFromNBT(tanktag);
//            }
//            if (tank.getFluidAmount() != 0) return ActionResultType.SUCCESS;
//            return ActionResultType.FAIL;
//        }
//        return ActionResultType.SUCCESS;
//    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack heldItem = playerIn.getHeldItem(handIn);
        CompoundNBT blockEntityTag = heldItem.getChildTag("BlockEntityTag");
        return blockEntityTag == null || blockEntityTag.getBoolean("isempty") ? new ActionResult<ItemStack>(ActionResultType.PASS, heldItem) : super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    protected boolean canPlace(BlockItemUseContext p_195944_1_, BlockState p_195944_2_) {
        if (!p_195944_1_.getWorld().isRemote())
            return p_195944_1_.getPlayer().isSneaking();
        return false;
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        if (!context.getWorld().isRemote()) {
            PlayerEntity player = context.getPlayer();
            if (player.isSneaking()) {
                return tryPlace(new BlockItemUseContext(context));
            } else {
                return onItemRightClick(context.getWorld(), context.getPlayer(), context.getHand()).getType();
            }

        }

        return ActionResultType.SUCCESS;
    }
}
