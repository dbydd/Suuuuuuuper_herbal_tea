package com.dbydd.suuuuuper_herbal_tea.particle.world_tea_tree_fruit_particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class World_Tea_Tree_Particle_Factory implements IParticleFactory<World_Tea_Tree_Fruit_ParticleData> {

    private final IAnimatedSprite sprites;

    public World_Tea_Tree_Particle_Factory(IAnimatedSprite sprite) {
        this.sprites = sprite;
    }

    @Nullable
    @Override
    public Particle makeParticle(World_Tea_Tree_Fruit_ParticleData typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        World_Tea_Tree_Fruit_Particle particle = new World_Tea_Tree_Fruit_Particle(worldIn, x, y, z, typeIn.getSpeed(), typeIn.getColor(), typeIn.getDiameter());
        particle.selectSpriteRandomly(sprites);
        return particle;
    }
}
