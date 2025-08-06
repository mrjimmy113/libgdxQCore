package com.quang.core.ui.dialogAnim;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.quang.core.actionLib.DoAction;
import com.quang.core.ui.ViewConstant;
import com.quang.core.ui.dialog.BaseDialog;
import com.quang.core.utils.IQDo;

public class DialogDefaultAnim extends DialogAnimBase {

    SequenceAction showAction;
    SequenceAction hideAction;

    private final BaseDialog baseDialog;
    public DialogDefaultAnim(final BaseDialog baseDialog) {
        this.baseDialog = baseDialog;
        showAction = new SequenceAction();

        baseDialog.getBgG().addAction(Actions.fadeOut(0f));
        showAction.addAction(new DoAction(new IQDo() {
            @Override
            public void invoke() {
                baseDialog.getElementG().setY(ViewConstant.getHeight() * -1f);
                baseDialog.getOverlay().setY(ViewConstant.getHeight() * -1f);
                baseDialog.getLabelG().setY(ViewConstant.getHeight() * -1f);

                baseDialog.getElementG().setTouchable(Touchable.disabled);
                baseDialog.getOverlay().setTouchable(Touchable.disabled);
                baseDialog.getLabelG().setTouchable(Touchable.disabled);

                baseDialog.getBgG().addAction(Actions.fadeOut(0f));
                baseDialog.getBgG().addAction(Actions.fadeIn(0.3f));
            }
        },0.2f));
        showAction.addAction(new DoAction(new IQDo() {
            @Override
            public void invoke() {
                baseDialog.getElementG().addAction(Actions.moveTo(0,0,0.4f, Interpolation.swingOut));
                baseDialog.getLabelG().addAction(Actions.moveTo(0,0,0.4f, Interpolation.swingOut));
                baseDialog.getOverlay().addAction(Actions.moveTo(0,0,0.4f, Interpolation.swingOut));
            }
        },0.4f  ));

        showAction.addAction(new DoAction(new IQDo() {
            @Override
            public void invoke() {
                baseDialog.getElementG().setTouchable(Touchable.enabled);
                baseDialog.getLabelG().setTouchable(Touchable.enabled);
                baseDialog.getOverlay().setTouchable(Touchable.enabled);
                baseDialog.onShowDialog();
            }
        },0f));

        hideAction =new SequenceAction();
        hideAction.addAction(new DoAction(new IQDo() {
            @Override
            public void invoke() {
                baseDialog.getElementG().setTouchable(Touchable.disabled);
                baseDialog.getLabelG().setTouchable(Touchable.disabled);
                baseDialog.getOverlay().setTouchable(Touchable.disabled);

                baseDialog.getElementG().addAction(Actions.moveTo(0,ViewConstant.getHeight() * -1f,0.4f, Interpolation.swingIn));
                baseDialog.getLabelG().addAction(Actions.moveTo(0,ViewConstant.getHeight() * -1f,0.4f, Interpolation.swingIn));
                baseDialog.getOverlay().addAction(Actions.moveTo(0,ViewConstant.getHeight() * -1f,0.4f, Interpolation.swingIn));
            }
        },0.3f));

        hideAction.addAction(new DoAction(new IQDo() {
            @Override
            public void invoke() {
                baseDialog.getBgG().addAction(Actions.fadeOut(0.2f));
            }
        },0.2f));
        hideAction.addAction(new DoAction(new IQDo() {
            @Override
            public void invoke() {
                if(onHide != null) onHide.invoke();
            }
        },0f));


    }

    @Override
    public void animShow() {
        showAction.restart();
        baseDialog.getElementG().addAction(showAction);
    }

    @Override
    public void animHide() {
        hideAction.restart();
        baseDialog.getElementG().addAction(hideAction);
    }

}
