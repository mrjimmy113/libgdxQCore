package com.quang.core.actionLib;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class BtnFocusAction {
    public static SequenceAction getAction(float start) {
        return  Actions.sequence(
                    Actions.scaleTo(start,start,1f),
                    Actions.scaleTo(start + 0.2f,start + 0.2f,0.2f),
                    Actions.scaleTo(start,start,0.1f),
                    Actions.scaleTo(start + 0.1f,start + 0.1f,0.1f),
                    Actions.scaleTo(start,start,0.1f));
    }

    public static SequenceAction getAction(float start, float wait) {
        return  Actions.sequence(
                    Actions.scaleTo(start,start,wait),
                    Actions.scaleTo(start + 0.2f,start + 0.2f,0.2f),
                    Actions.scaleTo(start,start,0.1f),
                    Actions.scaleTo(start + 0.1f,start + 0.1f,0.1f),
                    Actions.scaleTo(start,start,0.1f)
        );
    }

    public static SequenceAction getAction(float start, float wait, float waitEnd) {
        return  Actions.sequence(
                    Actions.scaleTo(start,start,wait),
                    Actions.scaleTo(start + 0.2f,start + 0.2f,0.2f),
                    Actions.scaleTo(start,start,0.1f),
                    Actions.scaleTo(start + 0.1f,start + 0.1f,0.1f),
                    Actions.scaleTo(start,start,0.1f),
                    Actions.scaleTo(start,start,waitEnd)
        );
    }
}
