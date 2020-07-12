package com.dbydd.suuuuuper_herbal_tea.registeried_lists;

import com.dbydd.suuuuuper_herbal_tea.Suuuuuuuper_herbal_tea;

import com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys.TileBig_Black_Pot;
import com.dbydd.suuuuuper_herbal_tea.blocks.tileentitys.TileEarth_Stovetop;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

//fuck it
public class Registered_TileEntities {
    public static final DeferredRegister<TileEntityType<?>> TILEENTITY_TYPE_REGISTER = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, Suuuuuuuper_herbal_tea.NAME);
    public static final RegistryObject<TileEntityType<TileBig_Black_Pot>> TilE_BIG_BLACK_POT_TYPE = TILEENTITY_TYPE_REGISTER.register("big_black_pot_tileentity", ()->TileEntityType.Builder.create(TileBig_Black_Pot::new, Registered_Blocks.BIG_BLACK_POT).build(null));
    public static final RegistryObject<TileEntityType<TileEarth_Stovetop>> TILE_EARTH_STOVETOP_TYPE = TILEENTITY_TYPE_REGISTER.register("earth_stovetop_tileentity", ()->TileEntityType.Builder.create(TileEarth_Stovetop::new, Registered_Blocks.EARTH_STOVETOP).build(null));


}
