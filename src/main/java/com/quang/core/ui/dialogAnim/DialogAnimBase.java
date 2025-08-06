package com.quang.core.ui.dialogAnim;

import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.quang.core.utils.IQDo;

public abstract class DialogAnimBase {
    protected SequenceAction showAction;
    protected SequenceAction hideAction;
    protected IQDo onHide;

    public abstract void animShow();

    public void animHide(IQDo onHide){
        this.onHide = onHide;
        animHide();
    }

    public abstract void animHide();
}
