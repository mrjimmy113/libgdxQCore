package com.quang.core.ui.viewAnim;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import com.quang.core.actionLib.DoAction;
import com.quang.core.ui.ViewConstant;
import com.quang.core.ui.view.BaseView;
import com.quang.core.utils.IQDo;

public class ViewDefaultAnim_Move extends ViewAnimBase<BaseView> {

    public ViewDefaultAnim_Move(BaseView baseView) {
        super(baseView);
    }

    @Override
    public void setupAnimShow() {
        showAction.addAction(new DoAction(new IQDo() {
            @Override
            public void invoke() {
                baseView.getElementG().setY(ViewConstant.getHeight() * -1f);
                baseView.getLabelG().setY(ViewConstant.getHeight() * -1f);
                baseView.getElementG().addAction(Actions.moveTo(0,0,0.5f, Interpolation.swing));
                baseView.getLabelG().addAction(Actions.moveTo(0,0,0.5f, Interpolation.swing));
            }
        },0.5f));
    }

    @Override
    public void setupAnimHide() {
        hideAction.addAction(new DoAction(new IQDo() {
            @Override
            public void invoke() {
                baseView.getElementG().addAction(Actions.moveTo(0,ViewConstant.getHeight() * -1f,0.5f, Interpolation.swing));
                baseView.getLabelG().addAction(Actions.moveTo(0,ViewConstant.getHeight() * -1f,0.5f, Interpolation.swing));
            }
        },0.5f));
    }
}
