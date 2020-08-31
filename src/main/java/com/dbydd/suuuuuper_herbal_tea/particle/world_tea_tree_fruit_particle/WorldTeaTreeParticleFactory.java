package com.dbydd.suuuuuper_herbal_tea.particle.world_tea_tree_fruit_particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class WorldTeaTreeParticleFactory implements IParticleFactory<WorldTeaTreeFruitParticleData> {

    private final IAnimatedSprite sprites;

    public WorldTeaTreeParticleFactory(IAnimatedSprite sprite) {
        this.sprites = sprite;
    }

    @Nullable
    @Override
    public Particle makeParticle(WorldTeaTreeFruitParticleData typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        WorldTeaTreeFruitParticle particle = new WorldTeaTreeFruitParticle(worldIn, x, y, z, typeIn.getSpeed(), typeIn.getColor(), typeIn.getDiameter());
        particle.selectSpriteRandomly(sprites);
        return particle;
    }
}
