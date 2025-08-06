package com.quang.core.ui.viewAnim;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.quang.core.actionLib.DoAction;
import com.quang.core.ui.ViewConstant;
import com.quang.core.ui.view.BaseView;
import com.quang.core.utils.IQDo;


public class ViewDefaultAnim_Fade extends ViewAnimBase<BaseView> {

    public ViewDefaultAnim_Fade(BaseView baseView) {
        super(baseView);
    }

    @Override
    public void setupAnimShow() {
        showAction.addAction(new DoAction(new IQDo() {
            @Override
            public void invoke() {
                baseView.getElementG().setColor(1,1,1,0f);
                baseView.getLabelG().setColor(1,1,1,0f);
                baseView.getElementG().addAction(Actions.fadeIn(0.5f, Interpolation.linear));
                baseView.getLabelG().addAction(Actions.fadeIn(0.5f, Interpolation.linear));
            }
        },0.5f));
    }

    @Override
    public void setupAnimHide() {
        hideAction = new SequenceAction();
        hideAction.addAction(new DoAction(new IQDo() {
            @Override
            public void invoke() {
                baseView.getElementG().addAction(Actions.fadeOut(0.5f, Interpolation.linear));
                baseView.getLabelG().addAction(Actions.fadeOut(0.5f, Interpolation.linear));
            }
        },0.5f));
    }
}

