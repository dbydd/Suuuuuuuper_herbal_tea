package com.dbydd.suuuuuper_herbal_tea.special_render;

import com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys.TileStoneTable;
import com.dbydd.suuuuuper_herbal_tea.items.TeaCupItem;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.RegisteredBlocks;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.RegisteredFluids;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.ItemStackHandler;

public class StoneTableTER extends TileEntityRenderer<TileStoneTable> {

    public StoneTableTER(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileStoneTable tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        BlockRendererDispatcher blockRenderer = Minecraft.getInstance().getBlockRendererDispatcher();
        ItemStackHandler itemOnTable = tileEntityIn.getItemOnTable();
        ItemStack stackInSlot0 = itemOnTable.getStackInSlot(0);
        if (!stackInSlot0.isEmpty()) {
            if (stackInSlot0.getItem() instanceof TeaCupItem) {
                renderCup(new Vec3d(0.25,1,0.25),new Vector3f(0.5f,0.5f,0.5f), stackInSlot0, matrixStackIn, blockRenderer, bufferIn, combinedLightIn, combinedOverlayIn);
            }
        }
    }


    private void renderCup(Vec3d translate, Vector3f scale, ItemStack stack, MatrixStack matrixStackIn, BlockRendererDispatcher blockRenderer, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStackIn.push();
        matrixStackIn.translate(translate.x, translate.y, translate.z);
        matrixStackIn.scale(scale.getX(), scale.getY(), scale.getZ());
        blockRenderer.renderBlock(RegisteredBlocks.TEA_CUP.getDefaultState(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
        matrixStackIn.pop();

        CompoundNBT blockEntityTag = stack.getChildTag("BlockEntityTag");
        if (blockEntityTag != null) {
            matrixStackIn.push();
            matrixStackIn.translate(translate.x+0.05, translate.y*0.9, translate.z+0.05);
            matrixStackIn.scale(scale.getX()*0.7f, 1, scale.getZ()*0.7f);
            FluidTank tank = new FluidTank(200);
            tank.readFromNBT(blockEntityTag.getCompound("tank"));
            Fluid fluid = tank.getFluid().getFluid();
            if (tank.getFluid().getFluid() == RegisteredFluids.TEA_WATER.fluid.get().getFluid()) {
                blockRenderer.renderBlock(RegisteredBlocks.TEA_WATER_FACE.getDefaultState(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
            } else {
                blockRenderer.renderBlock(RegisteredBlocks.WATER_FACE.getDefaultState(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
            }
            matrixStackIn.pop();
        }
    }
}
