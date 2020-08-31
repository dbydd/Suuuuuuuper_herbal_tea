package com.dbydd.suuuuuper_herbal_tea.events;

import com.dbydd.suuuuuper_herbal_tea.particle.world_tea_tree_fruit_particle.WorldTeaTreeParticleFactory;
import com.dbydd.suuuuuper_herbal_tea.registeried_lists.RegisteredParticleTypes;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ParticleRegistry {

    @SubscribeEvent
    public static void onParticleFactoryRegistration(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particles.registerFactory(RegisteredParticleTypes.WORLD_TEA_TREE_FRUIT_PARTICLETYPE.get(), WorldTeaTreeParticleFactory::new);
    }

}
