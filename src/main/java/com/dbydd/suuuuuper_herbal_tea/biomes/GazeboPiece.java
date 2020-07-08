package com.dbydd.suuuuuper_herbal_tea.biomes;

import com.dbydd.suuuuuper_herbal_tea.Suuuuuuuper_herbal_tea;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;

public class GazeboPiece extends TemplateStructurePiece {
    public static IStructurePieceType TYPE = IStructurePieceType.register(GazeboPiece::new, "Gazebo_piece");
    private final ResourceLocation templateName;
    private final Rotation rotation;

    public GazeboPiece(TemplateManager templateManager, CompoundNBT compoundNBT) {
        super(TYPE, compoundNBT);
        this.templateName = new ResourceLocation(compoundNBT.getString("Template"));
        this.rotation = Rotation.valueOf(compoundNBT.getString("Rot"));
        this.setup(templateManager);
    }

    public GazeboPiece(TemplateManager templateManager, ResourceLocation templateName, Rotation rotation, BlockPos pos) {
        super(TYPE, 0);
        this.templateName = templateName;
        this.rotation = rotation;
        this.templatePosition = pos;
        this.setup(templateManager);
    }

    private void setup(TemplateManager templateManagerIn) {
        Template template = templateManagerIn.getTemplateDefaulted(this.templateName);
        PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).addProcessor(BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK);
        this.setup(template, this.templatePosition, placementsettings);
    }

    @Override
    protected void readAdditional(CompoundNBT tagCompound) {
        super.readAdditional(tagCompound);
        tagCompound.putString("Template", this.templateName.toString());
        tagCompound.putString("Rot", this.rotation.name());
    }

    @Override
    public boolean create(IWorld worldIn, ChunkGenerator<?> chunkGeneratorIn, Random randomIn, MutableBoundingBox mutableBoundingBoxIn, ChunkPos chunkPosIn) {
        int xoffset = randomIn.nextInt(15);
        int zoffset = randomIn.nextInt(15);
        BlockPos blockPos = new BlockPos(chunkPosIn.getXStart() + xoffset, chunkGeneratorIn.getGroundHeight(), chunkPosIn.getZStart() + zoffset);
        template.addBlocksToWorld(worldIn,blockPos, placeSettings,  1);
        worldIn.getPlayers().get(0).sendMessage(new StringTextComponent(blockPos.toString()));
        return true;
    }

    @Override
    protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox sbb) {

    }

}
