package com.dbydd.suuuuuper_herbal_tea.worldgen.tea_house;

import com.dbydd.suuuuuper_herbal_tea.Suuuuuuuper_herbal_tea;
import com.dbydd.suuuuuper_herbal_tea.events.BiomeFeatureRegistry;
import net.minecraft.block.BarrelBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.BarrelTileEntity;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.*;
import net.minecraft.world.storage.loot.LootTables;

import java.util.Random;

public class TeaHouseStructurePiece extends TemplateStructurePiece {
    private Rotation rot;

    public TeaHouseStructurePiece(TemplateManager templateManagerIn, BlockPos pos, Random rand) {
        super(BiomeFeatureRegistry.teaHouseStructurePieceType, 0);
        ResourceLocation templateName = new ResourceLocation(Suuuuuuuper_herbal_tea.NAME, "tea_house");
        Template temp = templateManagerIn.getTemplate(templateName);
        this.rot = Rotation.randomRotation(rand);
        this.setup(temp, pos, new PlacementSettings().setRotation(rot));
    }

    public TeaHouseStructurePiece(TemplateManager templateManager, CompoundNBT compoundNBT) {
        super(BiomeFeatureRegistry.teaHouseStructurePieceType, 0);
        Template temp = templateManager.func_227458_a_(compoundNBT);
        this.setup(temp, this.templatePosition, new PlacementSettings().setRotation(Rotation.valueOf(compoundNBT.getString("rot"))));
    }

    @Override
    protected void readAdditional(CompoundNBT tagCompound) {
        tagCompound.putString("rot", rot.toString());
        super.readAdditional(tagCompound);
    }

    @Override
    protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox sbb) {
        if("barrel".equals(function)){
            BlockPos offset = pos.offset(rot.rotate(Direction.NORTH));
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
            TileEntity tileentity = worldIn.getTileEntity(offset);
            if (tileentity instanceof BarrelTileEntity) {
                ((BarrelTileEntity)tileentity).setLootTable(new ResourceLocation("teahouse_barrel_loottable"), rand.nextLong());
            }
        }
    }
}
