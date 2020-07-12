package com.dbydd.suuuuuper_herbal_tea.diminisions.dim01_world_tea_tree;

import com.dbydd.suuuuuper_herbal_tea.utils.MathUtils;
import com.dbydd.suuuuuper_herbal_tea.utils.RandomUtils;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.WorldGenRegion;

import java.util.Random;

public class World_Tea_Tree_ChunkGenerator extends ChunkGenerator<GenerationSettings> {
    private static final int MAIN_BRANCH_CHUNK_RANGE = 12;
    private static final int MAIN_BRANCH_BLOCK_RANGE = MAIN_BRANCH_CHUNK_RANGE << 4;

    public World_Tea_Tree_ChunkGenerator(IWorld worldIn, BiomeProvider biomeProviderIn) {
        super(worldIn, biomeProviderIn, new World_Tea_Tree_GenerationSettings());
    }

    @Override
    public void generateSurface(WorldGenRegion region, IChunk iChunk) {
        Random rand = region.getRandom();
        ChunkPos chunkPos = iChunk.getPos();
        boolean isGenerate_Big_Branch = RandomUtils.outputBooleanByChance(rand, 0.02);
        if (MathUtils.inCircle(chunkPos.x, chunkPos.z, MAIN_BRANCH_CHUNK_RANGE)) {
            generateMainBranch(region, iChunk, chunkPos);
            if(chunkPos.x == 0 && chunkPos.z == 0){
            }
        }
    }

    private void generateMainBranch(WorldGenRegion region, IChunk iChunk, ChunkPos chunkPos) {
        int beginX = chunkPos.getXStart();
        int endX = chunkPos.getXEnd();
        int beginZ = chunkPos.getZStart();
        int endZ = chunkPos.getZEnd();
        int beginY = 0;
        int endY = region.getHeight();
        for (int x = beginX; x <= endX; x++) {
            for (int z = beginZ; z <= endZ; z++) {
                if (MathUtils.inCircle(x, z, MAIN_BRANCH_BLOCK_RANGE)) {
                    for (int y = beginY; y <= endY; y++) {
                        iChunk.setBlockState(new BlockPos(x, y, z), Blocks.OAK_WOOD.getDefaultState(), false);
                    }
                }
            }
        }
    }

    @Override
    public int getGroundHeight() {
        return 100;
    }

    @Override
    public void makeBase(IWorld worldIn, IChunk chunkIn) {

    }

    @Override
    public int func_222529_a(int p_222529_1_, int p_222529_2_, Heightmap.Type heightmapType) {
        return 0;
    }
}
