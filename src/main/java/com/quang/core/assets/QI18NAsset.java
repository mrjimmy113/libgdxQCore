package com.quang.core.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.I18NBundle;

public class QI18NAsset {

    private static AssetManager manager;

    public static final String I18 = "i18n/";
    public static final String I18_BUNDLE = I18 + "Lang";

    public static void init(AssetManager assetManager) {
        manager = assetManager;
    }

    public static I18NBundle get() {
        return manager.get(I18_BUNDLE);
    }

    public static void Setup() {

    }


}
