package com.quang.core.utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.TimeUtils;


public class FrameRate implements Disposable{
    long lastTimeCounted;
    private float sinceChange;
    private float frameRate;
    private float drawCalls;
    private float lowestFrameRate = Float.MAX_VALUE;
    private float highestDrawCalls = Float.MIN_VALUE;
    private BitmapFont font;
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private GLProfiler profiler;


    public FrameRate() {
        lastTimeCounted = TimeUtils.millis();
        profiler = new GLProfiler(Gdx.graphics);
        profiler.enable();
        sinceChange = 0;
        frameRate = Gdx.graphics.getFramesPerSecond();
        font = new BitmapFont();
        font.getData().setScale(2f);
        batch = new SpriteBatch();
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void resize(int screenWidth, int screenHeight) {
        cam = new OrthographicCamera(screenWidth, screenHeight);
        cam.translate(screenWidth / 2, screenHeight / 2);
        cam.update();
        batch.setProjectionMatrix(cam.combined);
    }

    //Before Draw call
    private void reset(){
        profiler.reset();
    }

    //After Draw call
    private void update() {
        long delta = TimeUtils.timeSinceMillis(lastTimeCounted);
        lastTimeCounted = TimeUtils.millis();

        sinceChange += delta;
        if(sinceChange >= 1000) {
            sinceChange = 0;
            frameRate = Gdx.graphics.getFramesPerSecond();
        }

        drawCalls = profiler.getDrawCalls();
        if(highestDrawCalls < drawCalls) highestDrawCalls = drawCalls;
        if(lowestFrameRate > frameRate) lowestFrameRate = frameRate;
    }

    //After calling update
    //For enable render
    private boolean isRender = true;

    private void render() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
            isRender = !isRender;
        }

        if(!isRender) return;


        batch.begin();
        font.draw(batch, (int)frameRate + " FPS ", 30, Gdx.graphics.getHeight() - 30);
        //font.draw(batch, "LOW FPS: " + (int)lowestFrameRate,30,Gdx.graphics.getHeight() - 30 - 40 *1);
        font.draw(batch, "DC: " + (int)drawCalls,30,Gdx.graphics.getHeight() - 30 - 40 *1);
        font.draw(batch, "MAX DC: " + (int)highestDrawCalls,30,Gdx.graphics.getHeight() - 30 - 40 *2);
        font.draw(batch,"HEAP: " + String.valueOf(Gdx.app.getJavaHeap() * 0.000001f)  + "MB",30,Gdx.graphics.getHeight() - 30 - 40 *3);
        font.draw(batch,"NATIVE: " + String.valueOf(Gdx.app.getNativeHeap() * 0.000001f)  + "MB",30,Gdx.graphics.getHeight() - 30 - 40 *4);
        batch.end();
    }

    public void run() {
        update();
        render();
        reset();
    }

    public void dispose() {
        font.dispose();
        batch.dispose();
    }
}
