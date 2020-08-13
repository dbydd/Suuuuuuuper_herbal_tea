package com.dbydd.suuuuuper_herbal_tea.special_render;

import com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys.TileBig_Black_Pot;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Blocks;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Fluids;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandler;

public class Big_Black_Pot_TER extends TileEntityRenderer<TileBig_Black_Pot> {
    public Big_Black_Pot_TER(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileBig_Black_Pot tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        BlockRendererDispatcher blockRenderer = Minecraft.getInstance().getBlockRendererDispatcher();
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        IItemHandler resources = tileEntityIn.getResources();
        FluidTank tank = tileEntityIn.getTank();

        for (int row = 0, slot = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++, slot++) {
                matrixStackIn.push();
                matrixStackIn.translate(0.27, 0, 0.25);
                matrixStackIn.translate((double) row / 4.5, 0.8, (double) col / 4.5);
                matrixStackIn.scale(0.15f, 0.15f, 0.15f);
                matrixStackIn.rotate(new Quaternion(20, 0, 0, true));
                ItemStack stackInSlot = resources.getStackInSlot(slot);
                if (!stackInSlot.isEmpty()) {
                    IBakedModel ibakedmodel = itemRenderer.getItemModelWithOverrides(stackInSlot, tileEntityIn.getWorld(), null);
                    itemRenderer.renderItem(stackInSlot, ItemCameraTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, ibakedmodel);
                }
                matrixStackIn.pop();
            }
        }

        if (!tank.isEmpty()) {
            matrixStackIn.push();
            float amount = tank.getFluidAmount();
            float capacity = tank.getCapacity();
            matrixStackIn.translate(0, (amount/capacity)/10 - 0.05, 0);
            if(tank.getFluid().getFluid() == Fluids.WATER) {
                blockRenderer.renderBlock(Registered_Blocks.WATER_FACE.getDefaultState(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
            }else if(tank.getFluid().getFluid() == Registered_Fluids.TEA_WATER.fluid.get().getFluid()){
                blockRenderer.renderBlock(Registered_Blocks.TEA_WATER_FACE.getDefaultState(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
            }
            matrixStackIn.pop();
        }

    }
}
