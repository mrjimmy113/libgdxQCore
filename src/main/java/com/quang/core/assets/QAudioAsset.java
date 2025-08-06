package com.quang.core.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class QAudioAsset {

    public static final String AUDIO = "audio/";
    public static final String NORMAL_AUDIO_EXT = ".mp3";
    public static final String LOOP_AUDIO_EXT = ".mp3";
    public static final String MUSIC_PATH = AUDIO + "music/";
    public static final String SOUND_PATH = AUDIO + "sound/";




    public static void loadAudio(AssetManager manager) {

    }
    public static Sound getSound(String name) {
        return QAssetMaster.get(name,Sound.class);
    }

    public static void loadSingleSound(String name) {
        AssetManager manager = QAssetMaster.getAssetManager(name);
        String processedPath = QAssetMaster.getPath(name);

        manager.load(processedPath,Sound.class);
        manager.finishLoading();
    }

    public static Music getMusic(String name) {
        return QAssetMaster.get(name,Music.class);
    }

    public static void loadSingleMusic(String name) {
        AssetManager manager = QAssetMaster.getAssetManager(name);
        String processedPath = QAssetMaster.getPath(name);

        manager.load(processedPath,Music.class);
        manager.finishLoading();
    }
}
