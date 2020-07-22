package com.dbydd.suuuuuper_herbal_tea.tesr;

import com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys.TileEarth_Stovetop;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Blocks;
import com.dbydd.suuuuuper_herbal_tea.utils.IResourceItemHandler;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.VertexBuilderUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelDataManager;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.client.model.data.ModelDataMap;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.ForgeRegistries;

public class Earth_Stovetop_TER extends TileEntityRenderer<TileEarth_Stovetop> {
    public Earth_Stovetop_TER(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileEarth_Stovetop tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        IResourceItemHandler resources = tileEntityIn.getResources();
        ItemStackHandler fuel_ash_handler = tileEntityIn.getFuel_ash_Handler();
        boolean haspot = tileEntityIn.hasPot();
        boolean isburning = tileEntityIn.isIsburning();
        boolean cooking = tileEntityIn.isCooking();
        FluidTank tank = tileEntityIn.getTank();
        BlockRendererDispatcher blockRenderer = Minecraft.getInstance().getBlockRendererDispatcher();
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        BlockPos pos = tileEntityIn.getPos();
        World world = tileEntityIn.getWorld();

        //renderpot and resources
        if (haspot) {
            matrixStackIn.push();
            matrixStackIn.translate(0, 0.6, 0);
            BlockState potDefaultStateState = Registered_Blocks.BIG_BLACK_POT.getDefaultState();
            blockRenderer.renderBlock(potDefaultStateState, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
            matrixStackIn.pop();

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
        }

        if (isburning) {
            matrixStackIn.push();
            matrixStackIn.translate(0.25, 0.25, 0.25);
            matrixStackIn.scale(0.5f, 0.25f, 0.5f);
            BlockState fireState = Blocks.FIRE.getDefaultState().with(FireBlock.AGE, (int) ((partialTicks * 100) % 15));
            blockRenderer.renderBlock(fireState, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
            matrixStackIn.pop();
        }

        if (!tank.isEmpty()) {
            matrixStackIn.push();
            int space = tank.getSpace();
            int capacity = tank.getCapacity();
            matrixStackIn.translate(0, 1, 0);
            BlockState waterState = Blocks.ICE.getDefaultState();
            bufferIn.getBuffer(RenderType.getTranslucent());
            blockRenderer.renderBlock(waterState, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, EmptyModelData.INSTANCE);
            matrixStackIn.pop();
        }

        matrixStackIn.push();
        ItemStack fuel = fuel_ash_handler.getStackInSlot(0);
        matrixStackIn.translate(0.55, 0.3, 0.55);
        matrixStackIn.rotate(new Quaternion(90, 0, 0, true));
        matrixStackIn.scale(0.4f, 0.4f, 1);
        IBakedModel ibakedmodel = itemRenderer.getItemModelWithOverrides(fuel, tileEntityIn.getWorld(), null);
        itemRenderer.renderItem(fuel, ItemCameraTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, ibakedmodel);
        matrixStackIn.pop();
    }
}
