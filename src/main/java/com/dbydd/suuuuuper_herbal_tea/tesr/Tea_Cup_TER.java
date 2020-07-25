package com.dbydd.suuuuuper_herbal_tea.tesr;

import com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys.TileTeaCup;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

public class Tea_Cup_TER extends TileEntityRenderer<TileTeaCup> {
    public Tea_Cup_TER(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileTeaCup tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {

    }
}
