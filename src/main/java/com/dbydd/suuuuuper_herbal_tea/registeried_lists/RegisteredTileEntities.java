package com.dbydd.suuuuuper_herbal_tea.registeried_lists;

import com.dbydd.suuuuuper_herbal_tea.SuuuuuuuperHerbalTea;
import com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys.*;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

//fuck it
public class RegisteredTileEntities {
    public static final DeferredRegister<TileEntityType<?>> TILEENTITY_TYPE_REGISTER = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, SuuuuuuuperHerbalTea.NAME);
    public static final RegistryObject<TileEntityType<TileBigBlackPot>> TilE_BIG_BLACK_POT_TYPE = TILEENTITY_TYPE_REGISTER.register("big_black_pot_tileentity", ()->TileEntityType.Builder.create(TileBigBlackPot::new, RegisteredBlocks.BIG_BLACK_POT).build(null));
    public static final RegistryObject<TileEntityType<TileEarthStovetop>> TILE_EARTH_STOVETOP_TYPE = TILEENTITY_TYPE_REGISTER.register("earth_stovetop_tileentity", ()->TileEntityType.Builder.create(TileEarthStovetop::new, RegisteredBlocks.EARTH_STOVETOP).build(null));
    public static final RegistryObject<TileEntityType<TileStoneTable>> TILE_STONE_TABLE_TYPE = TILEENTITY_TYPE_REGISTER.register("stone_table", ()->TileEntityType.Builder.create(TileStoneTable::new, RegisteredBlocks.STONE_TABLE).build(null));
    public static final RegistryObject<TileEntityType<TileTeaCup>> TEA_CUP = TILEENTITY_TYPE_REGISTER.register("tea_cup",()->TileEntityType.Builder.create(TileTeaCup::new, RegisteredBlocks.TEA_CUP).build(null));
    public static final RegistryObject<TileEntityType<TileSink>> TILE_SINK = TILEENTITY_TYPE_REGISTER.register("sink", ()->TileEntityType.Builder.create(TileSink::new, RegisteredBlocks.SINK).build(null));
}
