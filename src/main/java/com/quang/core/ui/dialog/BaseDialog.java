package com.quang.core.ui.dialog;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.quang.core.utils.IQDo;
import com.quang.core.element.QLabel;
import com.quang.core.ui.QViewStage;
import com.quang.core.ui.dialogAnim.DialogAnimBase;


public abstract class BaseDialog{

    protected Group dialogG;
    protected Group elementG;
    protected Group labelG;
    protected Group overlay;
    protected Group bgG;

    boolean isActive = false;

    protected DialogAnimBase anim;
    protected QViewStage viewStage;

    public BaseDialog(QViewStage viewStage) {
        this.viewStage = viewStage;
        dialogG = new Group();
        bgG = new Group();
        elementG = new Group();
        labelG = new Group();
        overlay = new Group();
        elementG.setTransform(false);
        labelG.setTransform(false);
        bgG.setTransform(false);
        overlay.setTransform(false);

        elementG.setVisible(true);
        labelG.setVisible(true);
        bgG.setVisible(true);
        overlay.setVisible(true);

        bgG.setTouchable(Touchable.enabled);
        dialogG.addActor(bgG);
        dialogG.addActor(elementG);
        dialogG.addActor(labelG);
        dialogG.addActor(overlay);
        viewStage.getDialogG().addActor(dialogG);

    }

    public abstract DialogIndex getIndex();

    public abstract void setup(DialogParam param);

    public final void showDialog() {
        if(anim != null) anim.animShow();
        else onShowDialog();
    }

    public void onShowDialog() {

    }

    public void onHideDialog(final IQDo onHide) {
        if(anim != null) anim.animHide(onHide);
        else onHide.invoke();
    }

    public void setVisible (boolean visible) {
        if(visible) viewStage.addActor(dialogG);
        else dialogG.remove();
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

    public Group getBgG() {
        return bgG;
    }

    public Group getOverlay() {
        return overlay;
    }
}
