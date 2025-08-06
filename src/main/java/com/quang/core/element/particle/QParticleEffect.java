package com.quang.core.element.particle;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;

public class QParticleEffect extends ParticleEffect {

    float timeScale = 1;

    public QParticleEffect(ParticleEffect effect) {
        super(effect);
    }

    public void setTimeScale(float timeScale) {
        this.timeScale = timeScale;
    }

    @Override
    public void update(float delta) {
        super.update(delta * timeScale);
    }
}
