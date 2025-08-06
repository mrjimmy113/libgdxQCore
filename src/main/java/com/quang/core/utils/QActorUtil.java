package com.quang.core.utils;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class QActorUtil {
    public static float localToStageRotation(Actor actor) {
        float rotation = actor.getRotation(); // Start with the actor's local rotation
        Group parent = actor.getParent();

        // Accumulate rotations up the hierarchy
        while (parent != null) {
            rotation += parent.getRotation();
            parent = parent.getParent();
        }

        // Normalize the rotation angle to [0, 360)
        rotation = rotation % 360;
        if (rotation < 0) rotation += 360;

        return rotation;
    }

    public static float stageToLocalRotation(Actor actor, float stageRotation) {
        float rotation = stageRotation; // Start with the stage rotation
        Group parent = actor.getParent();

        // Subtract parent rotations to convert to local space
        while (parent != null) {
            rotation -= parent.getRotation();
            parent = parent.getParent();
        }

        // Normalize the rotation angle to [0, 360)
        rotation = rotation % 360;
        if (rotation < 0) rotation += 360;

        return rotation;
    }
}
