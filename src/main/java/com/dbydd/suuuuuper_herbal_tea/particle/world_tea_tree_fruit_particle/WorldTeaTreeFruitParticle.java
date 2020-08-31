package com.dbydd.suuuuuper_herbal_tea.particle.world_tea_tree_fruit_particle;


import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.awt.*;


public class WorldTeaTreeFruitParticle extends SpriteTexturedParticle {
    protected WorldTeaTreeFruitParticle(World world, double x, double y, double z, Vec3d speed, Color color, float diameter) {
        super(world, x, y, z, speed.x, speed.y, speed.z);
        maxAge = 80;
        setColor(color.getRed(), color.getGreen(), color.getBlue());
        setAlphaF(color.getAlpha());
        particleScale = world.rand.nextFloat();
        canCollide = false;
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }
}
