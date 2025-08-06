package com.quang.core.newTransform;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;


import com.quang.core.utils.QHelper;

public class NewGroup extends Group {

    //region Base
    private float baseX;
    private float baseY;
    private float baseRotation;
    private float baseScaleX = 1;
    private float baseScaleY = 1;
    private boolean isLockBase;


    @Override
    public void setX(float x) {
        super.setX(x);
        if(isLockBase) return;
        baseX = x;
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        if(isLockBase) return;
        baseY = y;
    }

    @Override
    public void setRotation(float rotation) {
        super.setRotation(rotation);
        if(isLockBase) return;
        baseRotation = rotation;
    }

    @Override
    public void setScaleX(float scaleX) {
        super.setScaleX(scaleX);
        if(isLockBase) return;
        baseScaleX = scaleX;
    }

    @Override
    public void setScaleY(float scaleY) {
        super.setScaleY(scaleY);
        if(isLockBase) return;
        baseScaleY = scaleY;
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        if(isLockBase) return;
        baseX = x;
        baseY = y;
    }

    @Override
    public void setScale(float scaleX, float scaleY) {
        super.setScale(scaleX, scaleY);
        if(isLockBase) return;
        baseScaleX = scaleX;
        baseScaleY = scaleY;
    }

    @Override
    public void setScale(float scaleXY) {
        super.setScale(scaleXY);
        if(isLockBase) return;
        baseScaleX = scaleXY;
        baseScaleY = scaleXY;
    }


    float worldRotation;
    float worldScaleX;
    float worldScaleY;

    public void compute() {
        Vector2 pos = QHelper.getCompVec1();
        pos.set(getX() + getOriginX(),getY() + getOriginY());
        getParent().localToStageCoordinates(pos);
        pos.add(getOriginX() * -1, getOriginY() * -1);

        isLockBase = true;

        setupWorldStat();

        setX(pos.x);
        setY(pos.y);
        setRotation(worldRotation);
        setScale(worldScaleX, worldScaleY);
    }

    public void returnCompute() {
        setX(baseX);
        setY(baseY);
        setRotation(baseRotation);
        setScale(baseScaleX, baseScaleY);
        isLockBase = false;
    }

    private void setupWorldStat() {
        float rotation = 0;
        float scaleX = 1;
        float scaleY = 1;

        Actor actor = this;

        while (actor != null) {
            rotation += actor.getRotation();
            scaleX *= actor.getScaleX();
            scaleY *= actor.getScaleY();

            actor = actor.getParent(); // Move up the hierarchy
        }

        worldRotation = rotation;
        worldScaleX = scaleX;
        worldScaleY = scaleY;

    }
    //endregion

    @Override
    public void draw(Batch batch, float parentAlpha) {
        drawChildren(batch, parentAlpha);
    }

    @Override
    public void drawDebug(ShapeRenderer shapes) {
        compute();
        drawDebugBounds(shapes);
        returnCompute();
        drawDebugChildren(shapes);
    }
}
