package com.dbydd.suuuuuper_herbal_tea.tesr;

import com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys.TileStone_Table;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

public class Stone_Table_TER extends TileEntityRenderer<TileStone_Table> {

    public Stone_Table_TER(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileStone_Table tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {

    }
}
