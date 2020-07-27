package com.dbydd.suuuuuper_herbal_tea.tesr;

import com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys.TileTeaCup;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Blocks;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Fluids;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class Tea_Cup_TER extends TileEntityRenderer<TileTeaCup> {
    public Tea_Cup_TER(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileTeaCup tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        FluidTank tank = tileEntityIn.getTank();
        if (!tank.isEmpty()) {
            BlockRendererDispatcher blockRenderer = Minecraft.getInstance().getBlockRendererDispatcher();
            Fluid fluid = tank.getFluid().getFluid();

            matrixStackIn.push();
            matrixStackIn.translate(0.15,0.24,0.15);
            matrixStackIn.scale(0.7f,0,0.7f);
            if (tank.getFluid().getFluid() == Registered_Fluids.TEA_WATER.fluid.get().getFluid()) {
                blockRenderer.renderBlock(Registered_Blocks.TEA_WATER_FACE.getDefaultState(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
            } else {
                blockRenderer.renderBlock(Registered_Blocks.WATER_FACE.getDefaultState(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
            }
            matrixStackIn.pop();

        }
    }
}
