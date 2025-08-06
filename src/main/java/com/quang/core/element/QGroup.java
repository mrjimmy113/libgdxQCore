package com.quang.core.element;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class QGroup extends Group {

    @Override
    public void setColor(float r, float g, float b, float a) {
        for (Actor child : getChildren()) {
            child.setColor(r, g, b, a);
        }
    }

    @Override
    public void setColor(Color color) {
        for (Actor child : getChildren()) {
            child.setColor(color);
        }
    }
}
