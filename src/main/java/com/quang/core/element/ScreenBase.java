package com.quang.core.element;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;


public abstract class ScreenBase implements Screen {
    protected Game parent;
    public Stage getStage() {
        return stage;
    }
    protected boolean isSetupCamera;
    protected Stage stage;

    public ScreenBase(Game parent, Stage stage) {
        this.parent = parent;
        this.stage = stage;
        init();
    }
    OrthographicCamera camera;
    private void init() {
        isSetupCamera = false;
        camera = new OrthographicCamera();
        onInit();
    }

    public void onInit() {};

    @Override
    public void show() {
        onShow();
    }

    public void onShow() {};

    @Override
    public void render(float delta) {
        if(stage == null) return;

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        onRender(delta);
    }

    public void onRender(float delta) {};

    float cameraViewPortWidth;
    @Override
    public void resize(int width, int height) {
        if(stage == null) return;
/*
        stage.getViewport().update(width, height);
        if(!isSetupCamera) {
            isSetupCamera = true;
            cameraViewPortWidth =  camera.viewportWidth - ViewConstant.widthOffset;
            camera.translate(-ViewConstant.widthOffset / 2,0);

        }
        camera.viewportWidth = cameraViewPortWidth;
        camera.update();*/
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        onHide();
    }

    public void onHide() {}

    @Override
    public void dispose() {
        onDispose();
    }

    public void onDispose() {};


    public abstract int getId();
}
