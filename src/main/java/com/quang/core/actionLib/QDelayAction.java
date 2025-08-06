package com.quang.core.actionLib;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class QDelayAction {

    public static SequenceAction moveTo(float delay,float x, float y, float moveTime,Interpolation interpolation) {
        SequenceAction action = new SequenceAction();
        action.addAction(Actions.moveBy(0,0,delay));
        action.addAction(Actions.moveTo(x,y,moveTime,interpolation));
        return action;
    }

    public static SequenceAction scaleTo(float delay,float x, float time, Interpolation interpolation) {
        SequenceAction action = new SequenceAction();
        action.addAction(Actions.scaleBy(0,0,delay));
        action.addAction(Actions.scaleTo(x,x,time,interpolation));
        return action;
    }

}
