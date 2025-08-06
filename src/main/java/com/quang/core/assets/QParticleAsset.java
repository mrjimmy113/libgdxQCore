package com.quang.core.assets;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ParticleEffectLoader;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;


public class QParticleAsset {

    public static final String PARTICLE = "particles/";
    public static final String EXT = ".p";
    public static final String Confetti = PARTICLE + "confetti" + EXT;

    private static final ParticleEffectLoader.ParticleEffectParameter Loader_Param = new ParticleEffectLoader.ParticleEffectParameter();

    public static void load(AssetManager manager) {
        Loader_Param.atlasFile = QImageAsset.UiAtlas;

        manager.load(Confetti, ParticleEffect.class, Loader_Param);
    }

    private  static final ParticleEffectLoader.ParticleEffectParameter loaderParam = new ParticleEffectLoader.ParticleEffectParameter();

    public static void loadParticle(String path, String atlasPath) {
        loaderParam.atlasFile = atlasPath;
        AssetManager manager = QAssetMaster.getAssetManager(path);
        manager.load(path, ParticleEffect.class, loaderParam);
        manager.finishLoading();
    }

    public static void setup() {
    }

    public static ParticleEffect getParticle(String name) {
        return new ParticleEffect(QAssetMaster.get(name, ParticleEffect.class));
    }
}
