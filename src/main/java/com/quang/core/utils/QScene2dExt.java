package com.quang.core.utils;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.quang.core.ui.ViewConstant;


public class QScene2dExt {

    public static float horizontalPosX(int index, float actorWidth,
                                       float cellWidth, int cellCount,
                                       float containerWidth, float containerX,
                                       float padding) {
        float containerCenterX = containerX + containerWidth * 0.5f;
        float startX = 0;

        if(cellCount % 2 == 0) {
            startX = containerCenterX - padding * 0.5f - cellWidth * cellCount * 0.5f
                - padding * (cellCount - 1);
        }else {
            startX = containerCenterX - cellWidth * 0.5f - (padding + cellWidth) * MathUtils.floor(cellCount * 0.5f);
        }

        float x = startX + padding * (index + (cellCount % 2 == 0 ? 1 : 0)) + cellWidth * index + cellWidth * 0.5f - actorWidth * 0.5f;
        return x;
    }

    public static void horizontalPosX(Actor actor, int index,
                                      float cellWidth, int cellCount,
                                      float containerWidth, float containerX,
                                      float padding) {

        actor.setX(horizontalPosX(index,actor.getWidth(),cellWidth,cellCount,containerWidth,containerX,padding));
    }

    public static void centerXOf(Actor actor, Actor target) {
        actor.setX(target.getX() + target.getWidth() * 0.5f - actor.getWidth() * 0.5f);
    }

    public static void centerXOf(Actor actor, Actor target, float offsetX) {
        centerXOf(actor,target);
        actor.setX(actor.getX() + offsetX);
    }

    public static void centerYOf(Actor actor, Actor target) {
        actor.setY(target.getY() + target.getHeight() * 0.5f - actor.getHeight() * 0.5f);
    }

    public static void centerYOf(Actor actor, Actor target, float offsetY) {
        centerYOf(actor,target);
        actor.setY(actor.getY() + offsetY);
    }

    public static void  centerOf(Actor actor, Actor target) {
        centerXOf(actor,target);
        centerYOf(actor,target);
    }

    public static void  centerOf_OffsetY(Actor actor, Actor target,float offsetY) {
        centerXOf(actor,target);
        centerYOf(actor,target, offsetY);

    }

    public static void  centerOf_OffsetX(Actor actor, Actor target,float offsetX) {
        centerXOf(actor,target,offsetX);
        centerYOf(actor,target);
    }

    public static void centerOf_Offset(Actor actor, Actor target, float offsetX, float offsetY) {
        centerXOf(actor,target);
        centerYOf(actor,target);
        actor.setX(actor.getX() + offsetX);
        actor.setY(actor.getY() + offsetY);
    }




    public static float getCenterX(float width) {
        return (float) ViewConstant.getWidth() / 2 - width / 2;
    }

    public static float getCenterY(float height) {
        return (float) ViewConstant.getHeight() / 2 - height /2;
    }

    public static void center(Actor actor) {
        centerX(actor);
        centerY(actor);
    }

    public static void centerExtraY(Actor actor, float extraY) {
        centerX(actor);
        centerY(actor);
        actor.setY(actor.getY() + extraY);
    }

    public static void centerX(Actor actor) {
        actor.setX(getCenterX(actor.getWidth()));
    }

    public static void centerY(Actor actor) {
        actor.setY(getCenterY(actor.getHeight()));
    }


    public static int getStar(int score, int maxScore) {
        float factor = (float) score / maxScore;
        if(factor >= 0.5f  && factor < 0.75f) {
            return 1;
        }else if (factor >=0.75f && factor < 1f) {
            return 2;
        }else if(factor >= 1) {
            return 3;
        }else {
            return 0;
        }


    }

    public static void centerOrigin(Actor actor) {
        actor.setOrigin(actor.getWidth()/2, actor.getHeight() / 2);
    }

    public static float offsetXFrom(Actor origin, Actor offset) {
        return offset.getX() - origin.getX();
    }

    public static float offsetYFrom(Actor origin, Actor offset) {
        return offset.getY() - origin.getY();
    }
}
