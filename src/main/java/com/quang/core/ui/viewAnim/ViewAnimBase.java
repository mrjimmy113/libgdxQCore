package com.quang.core.ui.viewAnim;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import com.quang.core.actionLib.DoAction;
import com.quang.core.ui.view.BaseView;
import com.quang.core.utils.IQDo;

public abstract class ViewAnimBase<T extends BaseView> {

    protected T baseView;
    protected SequenceAction showAction;
    protected SequenceAction hideAction;
    protected com.quang.core.utils.IQDo onHide;

    public ViewAnimBase(T baseView) {
        this.baseView = baseView;
        showAction = new SequenceAction();
        hideAction = new SequenceAction();

        setupAnimShow();
        setupAnimHide();

        showAction.addAction(new DoAction(new IQDo() {
            @Override
            public void invoke() {
                baseView.getElementG().setTouchable(Touchable.enabled);
                baseView.getLabelG().setTouchable(Touchable.enabled);
                baseView.onShowView();
            }
        },0.1f));

        hideAction.addAction(new DoAction(new IQDo() {
            @Override
            public void invoke() {
                if(onHide != null) onHide.invoke();
                baseView.onHideView();
            }
        },0f));
    }

    public void animShow() {
        baseView.getElementG().setTouchable(Touchable.disabled);
        baseView.getLabelG().setTouchable(Touchable.disabled);
        showAction.restart();
        baseView.getElementG().addAction(showAction);
        onAnimShow();
    }

    public void animHide(IQDo onHide){
        this.onHide = onHide;
        animHide();
    }

    public void animHide() {
        baseView.getElementG().setTouchable(Touchable.disabled);
        baseView.getLabelG().setTouchable(Touchable.disabled);
        hideAction.restart();
        baseView.getElementG().addAction(hideAction);
        onAnimHide();
    }

    public void setupAnimShow() {

    }

    public void setupAnimHide() {

    }

    public void onAnimShow() {

    }
    public void onAnimHide() {

    }

}
