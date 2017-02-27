package org.lwjglb.engine.graph.particles;

import org.lwjglb.engine.items.GameItem;

import java.util.List;

public interface IParticleEmitter {

    void cleanup();

    Particle getBaseParticle();

    List<GameItem> getParticles();
}
