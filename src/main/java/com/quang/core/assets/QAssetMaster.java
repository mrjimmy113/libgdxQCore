package com.quang.core.assets;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.AbsoluteFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;

public class QAssetMaster {
    public static boolean IsDebug = true;

    public static FileHandleResolver InternalResolver;
    public static FileHandleResolver AbsoluteResolver;

    public static AssetManager InternalManager;
    public static AssetManager AbsoluteManager;

    public static void Init() {
        InternalResolver = new InternalFileHandleResolver();
        AbsoluteResolver = new AbsoluteFileHandleResolver();

        InternalManager = new AssetManager(InternalResolver);
        AbsoluteManager = new AssetManager(AbsoluteResolver);
    }

    public static void Setup() {

        QImageAsset.loadImage(InternalManager);
        QFontAsset.loadFont(InternalManager);
        QParticleAsset.load(InternalManager);
        QAudioAsset.loadAudio(InternalManager);
        QShaderAsset.loadShader(InternalManager);
        QI18NAsset.init(InternalManager);
        InternalManager.finishLoading();
        setupAfterLoad();
    }

    public static void setupAfterLoad() {
        QImageAsset.Setup();
        QFontAsset.Setup();
        QI18NAsset.Setup();
    }

    public static boolean isLoaded(String path) {
        AssetManager assetManager = getAssetManager(path);
        if(assetManager == InternalManager) {
            return InternalManager.isLoaded(path);
        }else if(assetManager == AbsoluteManager) {
            String processedPath = getPath(path);
            return AbsoluteManager.isLoaded(processedPath);
        }
        return false;
    }

    public static<T> AssetManager load(String path, Class<T> type) {
        AssetManager assetManager = getAssetManager(path);
        String processedPath = getPath(path);
        assetManager.load(processedPath, type);
        return assetManager;
    }

    public static <T> T get(String path, Class<T> type) {
        AssetManager assetManager = getAssetManager(path);
        String processedPath = getPath(path);

        return assetManager.get(processedPath, type);
    }

    public static void unLoad(String path) {
        AssetManager assetManager = getAssetManager(path);
        String processedPath = getPath(path);
        if(!assetManager.isLoaded(processedPath)) return;
        assetManager.unload(processedPath);
    }

    public static void finishLoading() {
        InternalManager.finishLoading();
        AbsoluteManager.finishLoading();
    }

    public static AssetManager getAssetManager(String path) {
        if(Gdx.app.getType() == Application.ApplicationType.Desktop || IsDebug) return InternalManager;

        int firstSlashIndex = path.indexOf('/');
        String beforeFirstSlash = firstSlashIndex != -1 ? path.substring(0, firstSlashIndex) : path;
        try {
            Integer.parseInt(beforeFirstSlash);
            return AbsoluteManager;

        } catch (NumberFormatException e) {
            return InternalManager;
        }
    }

    public static String getPath(String path) {
        if (Gdx.app.getType() == Application.ApplicationType.Desktop || IsDebug) {
            return desktopPath(path);
        }

        return desktopPath(path);
    }


    public static String desktopPath(String path) {
        int firstSlashIndex = path.indexOf('/');
        String beforeFirstSlash = firstSlashIndex != -1 ? path.substring(0, firstSlashIndex) : path;
        try {
            int packId = Integer.parseInt(beforeFirstSlash);
            return path.substring(firstSlashIndex + 1);
        }catch (NumberFormatException e) {
            return path;
        }
    }


    public static void dispose() {
        InternalManager.dispose();
        AbsoluteManager.dispose();
    }
}
