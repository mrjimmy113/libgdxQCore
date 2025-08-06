package com.quang.core.actionLib;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.utils.Null;

public class ActionsExt {

    static public FontScaleToAction fontScaleTo (float x, float y) {
        return fontScaleTo(x,y,0,null);
    }

    static public FontScaleToAction fontScaleTo (float x, float y, float duration) {
        return fontScaleTo(x,y,duration,null);
    }

    static public FontScaleToAction fontScaleTo (float x, float y, float duration, @Null Interpolation interpolation) {
        FontScaleToAction action = Actions.action(FontScaleToAction.class);
        action.setScale(x, y);
        action.setDuration(duration);
        action.setInterpolation(interpolation);
        return action;
    }

}
