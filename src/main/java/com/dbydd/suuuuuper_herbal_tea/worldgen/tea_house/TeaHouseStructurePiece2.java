package com.dbydd.suuuuuper_herbal_tea.worldgen.tea_house;

import com.dbydd.suuuuuper_herbal_tea.Suuuuuuuper_herbal_tea;
import com.dbydd.suuuuuper_herbal_tea.events.CommonSetupEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.resources.IResource;
import net.minecraft.server.MinecraftServer;
import net.minecraft.test.StructureHelper;
import net.minecraft.util.FileUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;
import net.minecraft.world.gen.feature.structure.ScatteredStructurePiece;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;

import java.io.IOException;
import java.util.Random;

public class TeaHouseStructurePiece2 extends ScatteredStructurePiece {
    private static final ResourceLocation tempLocation = new ResourceLocation(Suuuuuuuper_herbal_tea.NAME, "tea_house");
    private final TemplateManager templateManager;

    protected TeaHouseStructurePiece2(TemplateManager templateManager, Random random, int x, int z) {
        super(CommonSetupEvent.teaHouseStructurePieceType, random, x, 64, z, 12, 10, 15);
        this.templateManager = templateManager;
    }

    public TeaHouseStructurePiece2(TemplateManager templateManager, CompoundNBT nbt) {
        super(CommonSetupEvent.teaHouseStructurePieceType, nbt);
        this.templateManager = templateManager;
    }

    @Override
    public boolean create(IWorld worldIn, ChunkGenerator<?> chunkGeneratorIn, Random randomIn, MutableBoundingBox mutableBoundingBoxIn, ChunkPos chunkPosIn) {
        int x = (chunkPosIn.getXStart() + chunkPosIn.getXEnd()) / 2;
        int z = (chunkPosIn.getZStart() + chunkPosIn.getZEnd()) / 2;
        int y = worldIn.getHeight(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, x, z);
        BlockPos pos = new BlockPos(x, y, z);
//        try {
//            IResource resource = worldIn.getWorld().getServer().getResourceManager().getResource(tempLocation);
//            CompoundNBT compoundnbt = CompressedStreamTools.readCompressed(resource.getInputStream());
//            Template template = templateManager.func_227458_a_(compoundnbt);
//            template.addBlocksToWorld(worldIn, pos, new PlacementSettings().setRotation(Rotation.randomRotation(randomIn)));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Template templateDefaulted = templateManager.getTemplate(tempLocation);
        templateDefaulted.addBlocksToWorld(worldIn, pos, new PlacementSettings().setRotation(Rotation.randomRotation(randomIn)));
        return true;
    }
}
