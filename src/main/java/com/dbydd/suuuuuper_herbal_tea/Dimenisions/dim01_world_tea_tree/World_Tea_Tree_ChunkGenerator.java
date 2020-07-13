package com.dbydd.suuuuuper_herbal_tea.Dimenisions.dim01_world_tea_tree;

import com.dbydd.suuuuuper_herbal_tea.utils.MathUtils;
import com.dbydd.suuuuuper_herbal_tea.utils.RandomUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.*;

import java.util.*;

public class World_Tea_Tree_ChunkGenerator extends ChunkGenerator<GenerationSettings> {
    private static final int MAIN_BRANCH_CHUNK_RANGE = 2;
    private static final int MAIN_BRANCH_BLOCK_RANGE = 32;
    private static final int MAIN_BRANCH_HEIGHT = 175;
    private static final int LEAVE_BLOCK_RANGE = MAIN_BRANCH_BLOCK_RANGE << 5;
    private static final int LEAVE_CHUNK_RANGE = LEAVE_BLOCK_RANGE >> 4;
    private static Map<Double, BlockState> generate_Map = new HashMap<>();

    public World_Tea_Tree_ChunkGenerator(IWorld worldIn, BiomeProvider biomeProviderIn) {
        super(worldIn, biomeProviderIn, new World_Tea_Tree_GenerationSettings());
        generate_Map.put(0.07, Blocks.GLOWSTONE.getDefaultState());
        generate_Map.put(0.27, Blocks.OAK_WOOD.getDefaultState());
        generate_Map.put(0.6, Blocks.OAK_LEAVES.getDefaultState());
    }

    @Override
    public void generateSurface(WorldGenRegion region, IChunk iChunk) {

    }

    private void generateRandomBranch(WorldGenRegion region, IChunk iChunk, ChunkPos chunkPos) {


    }


    private void generateLeaves(IWorld world, IChunk iChunk, ChunkPos chunkPos) {
        int beginx = (chunkPos.getXStart() + chunkPos.getXEnd()) / 2;
        int beginz = (chunkPos.getZStart() + chunkPos.getZEnd()) / 2;
        Random rand = world.getRandom();
        int stratum = rand.nextInt(7) + 1;
        int currentHeight = 60;
        for (int currentStratum = 1; currentStratum <= stratum; currentStratum++) {
            int stratumHeight = rand.nextInt(16);
            if (RandomUtils.outputBooleanByChance(rand, 0.6)) {
                for (int currentHeightEachStratum = 1; currentHeightEachStratum <= stratumHeight && currentHeight < 256; currentHeightEachStratum++) {
                    int radius = rand.nextInt(16) + 16;
                    for (int x = -radius; x < radius; x++) {
                        for (int z = -radius; z < radius; z++) {
                            if (Math.sqrt(Math.pow(x, 2) + Math.pow(z, 2)) <= radius) {
                                BlockPos blockPos = new BlockPos(beginx + x, currentHeightEachStratum + currentHeight, beginz + z);
                                if (world.getBlockState(blockPos).getBlock() == Blocks.AIR)
                                    world.setBlockState(blockPos, RandomUtils.outputRandmonBlockByList(rand, generate_Map), 3);
                            }
                        }
                    }
                    currentHeight++;
                }
            }
            currentHeight += rand.nextInt(8) + 8;
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
                        world.setBlockState(new BlockPos(x, y, z), Blocks.OAK_WOOD.getDefaultState(), 3);
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
            if (chunkPos.x == 0 && chunkPos.z == 0) {

            }
        } else if (MathUtils.inCircle(chunkPos.x, chunkPos.z, LEAVE_CHUNK_RANGE)) {
            generateLeaves(worldIn, chunkIn, chunkPos);
//            generateRandomBranch(region, iChunk, chunkPos);
        } else {
            generateBarrier(worldIn, chunkIn, chunkPos);
        }
    }

    @Override
    public int func_222529_a(int p_222529_1_, int p_222529_2_, Heightmap.Type heightmapType) {
        return 0;
    }

}
