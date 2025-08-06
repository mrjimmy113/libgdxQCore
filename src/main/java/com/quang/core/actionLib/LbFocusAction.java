package com.quang.core.actionLib;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class LbFocusAction {

    public static SequenceAction getAction(float start) {
        return  Actions.sequence(
                ActionsExt.fontScaleTo(start,start,1f),
                ActionsExt.fontScaleTo(start + 0.2f,start + 0.2f,0.2f),
                ActionsExt.fontScaleTo(start,start,0.1f),
                ActionsExt.fontScaleTo(start + 0.1f,start + 0.1f,0.1f),
                ActionsExt.fontScaleTo(start,start,0.1f)

        );
    }

    public static SequenceAction getAction(float start, float wait) {
        return  Actions.sequence(
                ActionsExt.fontScaleTo(start,start,wait),
                ActionsExt.fontScaleTo(start + 0.2f,start + 0.2f,0.2f),
                ActionsExt.fontScaleTo(start,start,0.1f),
                ActionsExt.fontScaleTo(start + 0.1f,start + 0.1f,0.1f),
                ActionsExt.fontScaleTo(start,start,0.1f)
        );
    }
}
