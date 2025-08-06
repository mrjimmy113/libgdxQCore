package com.quang.core.ui.view;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.quang.core.element.QLabel;
import com.quang.core.ui.ViewConstant;

import com.quang.core.ui.QViewStage;
import com.quang.core.ui.viewAnim.ViewAnimBase;
import com.quang.core.utils.IQDo;


public abstract class BaseView{

    protected Group elementG;
    protected Group labelG;

    boolean isActive = false;

    protected ViewAnimBase anim;

    public BaseView(QViewStage viewStage) {
        elementG = new Group();
        elementG.setOriginX(ViewConstant.getWidth() * 0.5f);
        elementG.setOriginY(ViewConstant.getHeight() * 0.5f);

        labelG = new Group();
        labelG.setOriginX(ViewConstant.getWidth() * 0.5f);
        labelG.setOriginY(ViewConstant.getHeight() * 0.5f);

        elementG.setTransform(false);
        labelG.setTransform(false);

        elementG.setVisible(false);
        labelG.setVisible(false);

        viewStage.getViewG().addActor(elementG);
        viewStage.getViewLabelG().addActor(labelG);
    }

    public abstract ViewIndex getIndex();

    public abstract void setup(ViewParam param);

    public void showView() {
        setTouchable(true);
        if(anim != null) anim.animShow();
        else onShowView();
    }

    public  void onShowView() {}

    public void hideView(IQDo lastHide) {
        setTouchable(false);
        if(anim != null) anim.animHide(lastHide);
        else {
            onHideView();
            lastHide.invoke();
        }

    }

    public void onHideView() {};

    public void setVisible (boolean visible) {
        elementG.setVisible(visible);
        labelG.setVisible(visible);
        isActive = visible;
    }

    public void addActor(Actor actor) {
        addActor(actor,!(actor instanceof QLabel));
    }

    public void addActor(Actor actor, boolean isToElement) {
        if(isToElement) elementG.addActor(actor);
        else labelG.addActor(actor);
    }

    public Group getElementG() {
        return elementG;
    }

    public Group getLabelG() {
        return labelG;
    }

    public void release() {
        elementG.remove();
        labelG.remove();
        onRelease();
    }

    public void onRelease(){}

    public void setTouchable(boolean touchable) {
        if(touchable) {
            elementG.setTouchable(Touchable.enabled);
            labelG.setTouchable(Touchable.enabled);
        }else {
            elementG.setTouchable(Touchable.disabled);
            labelG.setTouchable(Touchable.disabled);
        }
    }
}
