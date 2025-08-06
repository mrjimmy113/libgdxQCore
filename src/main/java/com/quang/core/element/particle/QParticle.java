package com.quang.core.element.particle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ParticleEffectActor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.quang.core.assets.QParticleAsset;
import com.quang.core.element.IQSortedActor;

public class QParticle extends ParticleEffectActor implements Pool.Poolable, IQSortedActor {

    private boolean isRender = true;

    public QParticle(String name) {
        super(new QParticleEffect(QParticleAsset.getParticle(name)), true);
        getEffect().setEmittersCleanUpBlendFunction(false);

    }


    public void setParColor(Color color) {
        Array<ParticleEmitter> emitters = getEffect().getEmitters();
        float[] colorArr = new float[] { color.r, color.g, color.b };
        for (ParticleEmitter e : emitters) {
            e.getTint().setColors(colorArr);
        }
    }

    @Override
    public void setScale(float scale) {
        Array<ParticleEmitter> emitters = getEffect().getEmitters();
        for (ParticleEmitter e : emitters) {
            e.scaleSize(scale);
            e.getVelocity().scale(scale);
            e.getGravity().scale(scale);
        }
    }

    @Override
    public void setScale(float scaleX, float scaleY) {
        Array<ParticleEmitter> emitters = getEffect().getEmitters();
        for (ParticleEmitter e : emitters) {
            e.scaleSize(scaleX, scaleY);
            e.getVelocity().scale((scaleX + scaleY) * 0.5f);
            e.getGravity().scale((scaleX + scaleY) * 0.5f);
        }
    }

    public void setContinues(boolean continues) {
        Array<ParticleEmitter> emitters = getEffect().getEmitters();
        for (ParticleEmitter e : emitters) {
            e.setContinuous(continues);
        }
    }

    public void stopEmitters() {
        Array<ParticleEmitter> emitters = getEffect().getEmitters();
        for (ParticleEmitter e : emitters) {
            e.allowCompletion();
        }
    }

    @Override
    public void start() {
        setVisible(true);
        super.start();
    }

    public void reStart() {
        getEffect().reset();
        start();
    }


    public void stop() {
        setVisible(false);
        isRunning = false;
    }

    public void setRender(boolean render) {
        isRender = render;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        ParticleEffect particleEffect = getEffect();
        particleEffect.setPosition(getX(), getY());
        if (lastDelta > 0) {
            particleEffect.update(lastDelta);
            lastDelta = 0;
        }

        if(!isRender) return;

        if (isRunning) {
            particleEffect.draw(batch);
            isRunning = !particleEffect.isComplete();
        }
    }

    public void setTimeScale(float timeScale) {
        if(getEffect() instanceof QParticleEffect) {
            ((QParticleEffect)getEffect()).setTimeScale(timeScale);
        }
    }

    public void flip() {
        Array<ParticleEmitter> emitters = getEffect().getEmitters();
        for (ParticleEmitter e : emitters) {
            e.getAngle().setHigh(180 - e.getAngle().getHighMin(), 180 - e.getAngle().getHighMax());
            e.getAngle().setLow(180 - e.getAngle().getLowMin(), 180 - e.getAngle().getLowMax());
        }

    }

    @Override
    public void reset() {
        setVisible(false);
        stop();
        setScale(1);
        setRotation(0);
        remove();
    }

    @Override
    public String getAsset() {
        if(getEffect().getEmitters().size < 0) return "None";
        if(getEffect().getEmitters().get(0).getSprites().size <0) return "None";

        return getEffect().getEmitters().get(0).getSprites().get(0).getTexture().toString();
    }

    @Override
    public String toString() {
        return "QParticle: " + getAsset() + " - Visible: " + isVisible();
    }

    @Override
    public Actor getActor() {
        return this;
    }
}
