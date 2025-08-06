package com.quang.core.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class QFontAsset {

    public static final String FONT = "font/";
    public static final String FONT_FILE = FONT + "main.fnt";


    public static Label.LabelStyle DEFAULT_STYLE;

    public static void loadFont(AssetManager manager) {
        manager.load(FONT_FILE, BitmapFont.class);

    }

    public static BitmapFont getFont(String name) {
        return QAssetMaster.get(name, BitmapFont.class);
    }

    public static void Setup() {
        if(getFont(QFontAsset.FONT_FILE) == null) return;
        DEFAULT_STYLE = new Label.LabelStyle(getFont(QFontAsset.FONT_FILE), Color.WHITE);
        DEFAULT_STYLE.font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        DEFAULT_STYLE.font.setUseIntegerPositions(false);
        DEFAULT_STYLE.font.getData().setLineHeight(DEFAULT_STYLE.font.getLineHeight() * 1.25f);
    }
}
