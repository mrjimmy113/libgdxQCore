package com.quang.core.actionLib;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class RotateFocusAction {
    public static SequenceAction getAction(float delay) {
        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(Actions.rotateBy(0,delay));
        sequenceAction.addAction(Actions.rotateBy(-30,0.1f));
        sequenceAction.addAction(Actions.rotateBy(60,0.2f));
        sequenceAction.addAction(Actions.rotateBy(-45,0.1f));
        sequenceAction.addAction(Actions.rotateBy(15,0.05f));

        return  sequenceAction;
    }
}
