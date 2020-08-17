package com.dbydd.suuuuuper_herbal_tea.Dimenisions.dim01_world_tea_tree;

import com.dbydd.suuuuuper_herbal_tea.Suuuuuuuper_herbal_tea;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.Registered_Blocks;
import com.dbydd.suuuuuper_herbal_tea.utils.MathUtils;
import com.dbydd.suuuuuper_herbal_tea.utils.RandomUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.SaveFormat;
import net.minecraftforge.common.ForgeHooks;

import java.util.*;

public class World_Tea_Tree_ChunkGenerator extends ChunkGenerator<GenerationSettings> {
    private static final int MAIN_BRANCH_CHUNK_RANGE = 4;
    private static final int MAIN_BRANCH_BLOCK_RANGE = 32;
    private static final int MAIN_BRANCH_HEIGHT = 175;
    private static final int LEAVE_BLOCK_RANGE = MAIN_BRANCH_BLOCK_RANGE << 5;
    private static final int LEAVE_CHUNK_RANGE = LEAVE_BLOCK_RANGE >> 4;
    private static final BlockState BRANCH_DEFAULT_STATE = Blocks.OAK_LOG.getDefaultState();
    private static Map<Double, BlockState> generate_Map = new HashMap<>();

    public World_Tea_Tree_ChunkGenerator(IWorld worldIn, BiomeProvider biomeProviderIn) {
        super(worldIn, biomeProviderIn, new World_Tea_Tree_GenerationSettings());
        generate_Map.put(0.1, Registered_Blocks.WORLD_TEA_TREE_LEAVE_GLOW.getDefaultState());
        generate_Map.put(0.2, Blocks.OAK_WOOD.getDefaultState());
        generate_Map.put(0.6, Registered_Blocks.WORLD_TEA_TREE_LEAVE.getDefaultState());
    }

    @Override
    public void generateSurface(WorldGenRegion region, IChunk iChunk) {

    }

    private void generateRandomBranch(IWorld world, IChunk iChunk, ChunkPos chunkPos, BlockPos pos, Random rand) {
            BlockPos currentPos = pos;
            int currentx;
            int currentz;
            int branchwidth = Math.round((float) (rand.nextInt(7) + 1) / 2.0f);
            while (currentPos.getY() >= pos.getY() - 60) {

                for (int x = -branchwidth; x < branchwidth; x++) {
                    for (int z = -branchwidth; z < branchwidth; z++) {

                        if (MathUtils.inCircle(x, z, branchwidth)) {
                            world.setBlockState(new BlockPos(currentPos.getX() + x, currentPos.getY(), currentPos.getZ() + z), BRANCH_DEFAULT_STATE, 3);
                        }
                    }
                }

                if (currentPos.getX() < 0) {
                    currentx = currentPos.getX() + 1;
                } else {
                    currentx = currentPos.getX() - 1;
                }

                if (currentPos.getZ() < 0) {
                    currentz = currentPos.getZ() + 1;
                } else {
                    currentz = currentPos.getZ() - 1;
                }

                currentPos = new BlockPos(currentx, currentPos.getY() - 1, currentz);
            }
            while (!(currentPos.getY() <= 0 || world.getBlockState(currentPos).isAir()) && !MathUtils.inCircle(currentPos.getX(), currentPos.getZ(), MAIN_BRANCH_BLOCK_RANGE)) {
                for (int x = -branchwidth; x < branchwidth; x++) {
                    for (int z = -branchwidth; z < branchwidth; z++) {

                        if (MathUtils.inCircle(x, z, branchwidth)) {
                            world.setBlockState(new BlockPos(currentPos.getX() + x, currentPos.getY(), currentPos.getZ() + z), BRANCH_DEFAULT_STATE, 3);
                        }
                    }
                }

                currentPos = new BlockPos(currentPos.getX(), currentPos.getY() - 1, currentPos.getZ());
            }

    }

    private void generateLeaves(IWorld world, IChunk iChunk, ChunkPos chunkPos) {
        int beginx = (chunkPos.getXStart() + chunkPos.getXEnd()) / 2;
        int beginz = (chunkPos.getZStart() + chunkPos.getZEnd()) / 2;
        Random rand = world.getRandom();
        int stratum = rand.nextInt(14) + 2;
        int currentHeight = 60 + rand.nextInt(8);
        for (int currentStratum = 1; currentStratum <= stratum; currentStratum++) {
            int stratumHeight = rand.nextInt(16);
            if (RandomUtils.outputBooleanByChance(rand, 0.57)) {

                if (RandomUtils.outputBooleanByChance(rand, 0.35)) {
                    generateRandomBranch(world, iChunk, chunkPos, new BlockPos(beginx, currentHeight, beginz), rand);
                }

                for (int currentHeightEachStratum = 1; currentHeightEachStratum <= stratumHeight && currentHeight < 256; currentHeightEachStratum++) {

                    int radius = rand.nextInt(16) + 16;

                    for (int x = -radius; x < radius; x++) {
                        for (int z = -radius; z < radius; z++) {

                            if (Math.sqrt(Math.pow(x, 2) + Math.pow(z, 2)) <= radius) {
                                BlockPos blockPos = new BlockPos(beginx + x, currentHeight, beginz + z);

                                if (world.getBlockState(blockPos).getBlock() == Blocks.AIR) {
                                    world.setBlockState(blockPos, RandomUtils.outputRandmonBlockByList(rand, generate_Map), 3);
                                    if (RandomUtils.outputBooleanByChance(rand, 0.001)) {
                                        world.setBlockState(new BlockPos(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ()), Registered_Blocks.WORLD_TEA_TREE_FRUIT.getDefaultState(), 3);
                                    }
                                }


                            }
                        }
                    }
                    currentHeight++;
                }
            }
            currentHeight += rand.nextInt(4) + 12;
        }

    }

    private void generateBarrier(IWorld world, IChunk iChunk, ChunkPos chunkPos) {
        int beginX = chunkPos.getXStart();
        int endX = chunkPos.getXEnd();
        int beginZ = chunkPos.getZStart();
        int endZ = chunkPos.getZEnd();
        for (int x = beginX; x <= endX; x++) {
            for (int z = beginZ; z <= endZ; z++) {
                for (int y = 1; y <= MAIN_BRANCH_HEIGHT; y++) {
                    BlockPos pos = new BlockPos(x, y, z);
                    if (iChunk.getBlockState(pos).getBlock() == Blocks.AIR) {
                        iChunk.setBlockState(pos, Blocks.BARRIER.getDefaultState(), false);
                    }
                }
            }
        }
    }

    private void generateMainBranch(IWorld world) {
        int beginX = -MAIN_BRANCH_BLOCK_RANGE;
        int endX = MAIN_BRANCH_BLOCK_RANGE;
        int beginZ = -MAIN_BRANCH_BLOCK_RANGE;
        int endZ = MAIN_BRANCH_BLOCK_RANGE;
        for (int x = beginX; x <= endX; x++) {
            for (int z = beginZ; z <= endZ; z++) {
                if (MathUtils.inCircle(x, z, MAIN_BRANCH_BLOCK_RANGE)) {
                    for (int y = 1; y <= MAIN_BRANCH_HEIGHT; y++) {
                        world.setBlockState(new BlockPos(x, y, z), BRANCH_DEFAULT_STATE, 3);
                    }
                }
            }
        }
    }

    @Override
    public int getGroundHeight() {
        return 1;
    }

    @Override
    public void makeBase(IWorld worldIn, IChunk chunkIn) {
        Random rand = worldIn.getRandom();
        ChunkPos chunkPos = chunkIn.getPos();
        if (MathUtils.inCircle(chunkPos.x, chunkPos.z, MAIN_BRANCH_CHUNK_RANGE)) {
            generateMainBranch(worldIn);
            if (chunkPos.x == 0 && chunkPos.z == 0 && !worldIn.isRemote()) {
                TemplateManager structureTemplateManager = ((ServerWorld) worldIn.getWorld()).getStructureTemplateManager();
                Template tea_pavilion = structureTemplateManager.getTemplate(new ResourceLocation(Suuuuuuuper_herbal_tea.NAME, "tea_pavilion"));
                tea_pavilion.addBlocksToWorld(worldIn, new BlockPos(-5, 175, -5), new PlacementSettings(), 3);
            }
        } else if (MathUtils.inCircle(chunkPos.x, chunkPos.z, LEAVE_CHUNK_RANGE)) {
            generateLeaves(worldIn, chunkIn, chunkPos);
        } else {
            generateBarrier(worldIn, chunkIn, chunkPos);
        }
    }

    @Override
    public int func_222529_a(int p_222529_1_, int p_222529_2_, Heightmap.Type heightmapType) {
        return 0;
    }

}
