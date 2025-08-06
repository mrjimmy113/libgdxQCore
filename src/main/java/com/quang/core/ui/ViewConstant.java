package com.quang.core.ui;

import com.badlogic.gdx.Gdx;

public class ViewConstant {

    public static float widthOffset = 0;
    public static float heightOffset = 0;
    public static int WIDTH = 720;
    public static int HEIGHT = 1280;

    public static float getHeight() {
        return  HEIGHT + heightOffset;
    }

    public static float getWidth() {
        return WIDTH + widthOffset;
    }

    public static void setup() {
        float baseRatioInFloat = HEIGHT * 1f / WIDTH;
        float deviceRatioInFloat = Gdx.graphics.getHeight() * 1f / Gdx.graphics.getWidth();

        float baseRatioInFloatWidth = WIDTH * 1f / HEIGHT;
        float deviceRatioInFloatWidth = Gdx.graphics.getWidth() * 1f / Gdx.graphics.getHeight();

        //16:9 > 16:10 ty le chieu rong nhieu hon
        //16:9 < 18:9 ty le chieu cao nhieu hon
        if(baseRatioInFloat > deviceRatioInFloat) {
            //Tang chieu rong
            float currentWidth = deviceRatioInFloatWidth * WIDTH / baseRatioInFloatWidth;
            widthOffset = currentWidth - WIDTH;
        }else {
            //Tang chieu doc
            float currentHeight = deviceRatioInFloat * HEIGHT / baseRatioInFloat;
            heightOffset = currentHeight - HEIGHT;
        }

    }

    private static final float BANNER_BOTTOM_MARGIN = 150f;

    public static float getBannerBottomMargin() {
        return BANNER_BOTTOM_MARGIN;
    }



}
