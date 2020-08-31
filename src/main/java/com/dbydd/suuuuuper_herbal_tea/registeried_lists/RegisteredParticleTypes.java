package com.dbydd.suuuuuper_herbal_tea.registeried_lists;

import com.dbydd.suuuuuper_herbal_tea.SuuuuuuuperHerbalTea;
import com.dbydd.suuuuuper_herbal_tea.particle.world_tea_tree_fruit_particle.WorldTeaTreeFruitParticleData;
import com.dbydd.suuuuuper_herbal_tea.particle.world_tea_tree_fruit_particle.WorldTeaTreeFruitParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegisteredParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = new DeferredRegister<>(ForgeRegistries.PARTICLE_TYPES, SuuuuuuuperHerbalTea.NAME);

    public static RegistryObject<ParticleType<WorldTeaTreeFruitParticleData>> WORLD_TEA_TREE_FRUIT_PARTICLETYPE = PARTICLE_TYPES.register("world_tea_tree_fruit_particle", WorldTeaTreeFruitParticleType::new);
}
