package com.quang.core.ui;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class QViewStage extends Stage {

    private Group viewG;
    private Group viewLabelG;
    private Group dialogG;
    private Group bottomBarG;
    private Group tutorialG;
    private Group restAdsG;

    public QViewStage() {
        newGroup();
    }

    public QViewStage(Viewport viewport) {
        super(viewport);
        newGroup();
    }

    public QViewStage(Viewport viewport, Batch batch) {
        super(viewport, batch);
        newGroup();
    }

    public Group getViewG() {
        return viewG;
    }

    public Group getViewLabelG() {
        return viewLabelG;
    }

    public Group getDialogG() {
        return dialogG;
    }


    public Group getBottomBarG() {
        return bottomBarG;
    }

    public Group getTutorialG() {
        return tutorialG;
    }

    public Group getRestAdsG() {return  restAdsG;}

    private void newGroup() {
        viewG = new Group();
        viewLabelG = new Group();
        dialogG = new Group();
        bottomBarG = new Group();
        tutorialG = new Group();
        restAdsG = new Group();

        viewG.setTransform(false);
        viewLabelG.setTransform(false);
        dialogG.setTransform(false);
        bottomBarG.setTransform(false);
        tutorialG.setTransform(false);
        restAdsG.setTransform(false);

        addActor(viewG);
        addActor(viewLabelG);
        addActor(dialogG);
        addActor(bottomBarG);
        addActor(tutorialG);
        addActor(restAdsG);
    }
}
