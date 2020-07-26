package com.dbydd.suuuuuper_herbal_tea.registeried_lists;

import com.dbydd.suuuuuper_herbal_tea.Suuuuuuuper_herbal_tea;
import com.dbydd.suuuuuper_herbal_tea.particle.world_tea_tree_fruit_particle.World_Tea_Tree_Fruit_ParticleData;
import com.dbydd.suuuuuper_herbal_tea.particle.world_tea_tree_fruit_particle.World_Tea_Tree_Fruit_ParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registered_ParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = new DeferredRegister<>(ForgeRegistries.PARTICLE_TYPES, Suuuuuuuper_herbal_tea.NAME);

    public static RegistryObject<ParticleType<World_Tea_Tree_Fruit_ParticleData>> WORLD_TEA_TREE_FRUIT_PARTICLETYPE = PARTICLE_TYPES.register("world_tea_tree_fruit_particle", World_Tea_Tree_Fruit_ParticleType::new);
}
