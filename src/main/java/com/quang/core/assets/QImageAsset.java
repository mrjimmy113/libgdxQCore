package com.quang.core.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


import java.util.ArrayList;
import java.util.Hashtable;

public class QImageAsset {

    public static final String IMG_PATH = "images/";
    public static final String IMG_EXT = ".png";
    public static final String BG_EXT = ".jpg";
    public static final String ATLAS_EXT = ".atlas";

    //region UI
    public static final String LevelIconAtlas = IMG_PATH + "levelIcon" + ATLAS_EXT;
    public static final String UiAtlas = IMG_PATH + "ui" + ATLAS_EXT;

    public static final String UiAtlasKey = "ui/";
    public static final String LevelIconAtlasKey = "levelIcon/";

    public static final String BG_MENU = IMG_PATH + "bgMenu" + BG_EXT;

    public static final String DUMMY = UiAtlasKey + "dummy" ;
    public static final String DIALOG_DUMMY = UiAtlasKey + "dialogDummy" ;
    public static final String LoadingBar_Bg = UiAtlasKey + "loadingBarBg" ;
    public static final String LoadingBar = UiAtlasKey + "loadingBar" ;
    public static final String RoundWhite = UiAtlasKey + "roundWhite" ;
    public static final String RoundRed = UiAtlasKey + "roundRed" ;
    public static final String BtnArrow = UiAtlasKey + "btnArrow" ;
    public static final String AvatarContainerOutline = UiAtlasKey + "avatarContainerOutline" ;
    public static final String AvatarContainer = UiAtlasKey + "avatarContainer" ;
    public static final String Avatar = UiAtlasKey + "avatar" ;
    public static final String IcStar = UiAtlasKey + "icStar" ;
    public static final String LevelItemStar = LevelIconAtlasKey + "levelItemStar" ;
    public static final String LevelItemStarNone = LevelIconAtlasKey + "levelItemStarNone" ;
    public static final String BarStar = UiAtlasKey + "barStar" ;
    public static final String BtnSetting = UiAtlasKey + "btnSetting" ;
    public static final String BtnMusicOff = UiAtlasKey + "btnMusicOff" ;
    public static final String BtnMusicOn = UiAtlasKey + "btnMusicOn" ;
    public static final String BtnSoundOff = UiAtlasKey + "btnSoundOff" ;
    public static final String BtnSoundOn = UiAtlasKey + "btnSoundOn" ;
    public static final String BtnVibrationOff = UiAtlasKey + "btnVibrationOff" ;
    public static final String BtnVibrationOn = UiAtlasKey + "btnVibrationOn" ;
    public static final String BtnX = UiAtlasKey + "btnX" ;
    public static final String Dialog = UiAtlasKey + "dialog" ;
    public static final String LanguageBar = UiAtlasKey + "languageBar" ;
    public static final String BtnOrange = UiAtlasKey + "btnOrange" ;
    public static final String BtnGreen = UiAtlasKey + "btnGreen" ;
    public static final String IcHome = UiAtlasKey + "icHome" ;
    public static final String IcStar2 = UiAtlasKey + "icStar2" ;
    public static final String RibbonWin = UiAtlasKey + "ribbonWin" ;
    public static final String RibbonLose = UiAtlasKey + "ribbonLose" ;
    public static final String IcAds = UiAtlasKey + "icAds" ;
    public static final String DialogTittle = UiAtlasKey + "dialogTittle" ;
    public static final String BoxResult = UiAtlasKey + "boxResult" ;
    public static final String BtnHome = UiAtlasKey + "btnHome" ;
    public static final String BtnNext = UiAtlasKey + "btnNext" ;
    public static final String BtnReplay = UiAtlasKey + "btnReplay" ;
    public static final String ResultStar = UiAtlasKey + "resultStar" ;
    public static final String ResultStarNone = UiAtlasKey + "resultStarNone" ;
    public static final String BtnSkip = UiAtlasKey + "btnSkip" ;
    public static final String BtnSuggest = UiAtlasKey + "btnSuggest" ;
    public static final String BtnTime = UiAtlasKey + "btnTime" ;
    public static final String Hand = UiAtlasKey + "hand" ;
    public static final String IcLike = UiAtlasKey + "icLike" ;
    public static final String IcNah = UiAtlasKey + "icNah" ;
    public static final String IcRestart = UiAtlasKey + "icRestart";
    public static final String PaintStroke = IMG_PATH + "paintStroke" + IMG_EXT;
    public static final String BTN_NO_ADS = UiAtlasKey + "btnNoAds";
    public static final String IC_NO_ADS = UiAtlasKey + "icNoAds";
    public static final String NO_ADS_GLOW_1 = UiAtlasKey + "noAdsGlow1";
    public static final String NO_ADS_GLOW_2 = UiAtlasKey + "noAdsGlow2";
    public static final String NO_ADS_TITTLE = UiAtlasKey + "noAdsTittle";
    //endregion
    //region Hamster
    public static final String HamsterAtlas = IMG_PATH + "hamster" + ATLAS_EXT;
    public static final String HamsterKey = "hamster/";
    public static final String Hamster_Bg = IMG_PATH + "hsBg.jpg";
    public static final String Hamster_Main = HamsterKey + "hsMain" ;
    public static final String Hamster_Arm_Left = HamsterKey + "hsArmLeft";
    public static final String Hamster_Arm_Right = HamsterKey + "hsArmRight";
    public static final String Hamster_UI_Top = HamsterKey + "hsUITop";
    public static final String Hamster_UI_Bottom = HamsterKey + "hsUIBottom";
    public static final String Hamster_Icon = UiAtlasKey + "hsIcon";
    public static final String Alert = UiAtlasKey + "alert";
    public static final String Hamster_Face_Idle = HamsterKey + "hsFaceIdle";
    public static final String Hamster_Face_Smile = HamsterKey + "hsFaceSmile";
    public static final String Hamster_Face_Cry1 = HamsterKey + "hsFaceCry1";
    public static final String Hamster_Face_Cry2 = HamsterKey + "hsFaceCry2";
    public static final String Hamster_Face_Eat1 = HamsterKey + "hsFaceEat1";
    public static final String Hamster_Face_Eat2 = HamsterKey + "hsFaceEat2";
    public static final String Hamster_Face_Eat3 = HamsterKey + "hsFaceEat3";
    public static final String Hamster_Face_Eat4 = HamsterKey + "hsFaceEat4";
    public static final String Hamster_Face_Eat5 = HamsterKey + "hsFaceEat5";
    public static final String Hamster_Face_Eat6 = HamsterKey + "hsFaceEat6";
    public static final String Hamster_Menu_Icon = HamsterKey + "hsMenuIcon";
    public static final String Hamster_Btn_Green = HamsterKey + "hsBtnGreen";
    public static final String Hamster_Progress = HamsterKey + "hsProgress";
    public static final String Hamster_Progress_Bg = HamsterKey + "hsProgressBg";
    public static final String Hamster_Btn = HamsterKey + "hsBtn";
    public static final String Hamster_Icon_Cloth = HamsterKey + "hsIcCloth";
    public static final String Hamster_Icon_Food = HamsterKey + "hsIcFood";
    public static final String Hamster_Btn_Small = HamsterKey + "hsBtnSmall";
    public static final String Hamster_Btn_Yellow = HamsterKey + "hsBtnYellow";
    public static final String Hamster_Btn_Cloth = HamsterKey + "hsBtnCloth";
    public static final String Hamster_Icon_Star = HamsterKey + "hsIcStar";
    public static final String Hamster_Icon_Ads = HamsterKey + "hsIcAds";
    public static final String Hamster_Icon_Alert = HamsterKey + "hsIcAlert";
    public static final String Hamster_Icon_Hamster = HamsterKey + "hsIcHamster";
    public static final String Hamster_Dialog = HamsterKey + "hsDialog";


    //endregion


    public static void loadImage(AssetManager manager) {
        manager.load(LevelIconAtlas,TextureAtlas.class);
        manager.load(UiAtlas,TextureAtlas.class);
        manager.load(HamsterAtlas,TextureAtlas.class);
    }



    public static void loadSingleImg(String filePath) {
        if(!filePath.contains(IMG_EXT)) return;
        AssetManager manager = QAssetMaster.getAssetManager(filePath);
        String processedPath = QAssetMaster.getPath(filePath);
        if(manager.isLoaded(processedPath)) return;
        manager.load(processedPath,Texture.class);
        manager.finishLoading();
    }

    private static final Hashtable<String, NinePatch> ninePatchDic = new Hashtable<>();

    public static NinePatch getNinePatch(String name) {
        return ninePatchDic.get(name);
    }

    public static void registerNinePatch(String name, NinePatch ninePatch) {
        ninePatchDic.put(name, ninePatch);
    }

    public static void unregisterNinePatch(String atlas) {
        ArrayList<String> removeKey = new ArrayList<>();
        for (String key : ninePatchDic.keySet()) {
            if(key.contains(atlas)) {
                removeKey.add(key);
            }
        }

        for (String key : removeKey) {
            ninePatchDic.remove(key);
        }
    }



    public static boolean isNinePatchRegistered(String name) {
        return ninePatchDic.containsKey(name);
    }

    public static void Setup() {

        registerAtlas(LevelIconAtlas, QAssetMaster.InternalManager.get(LevelIconAtlas,TextureAtlas.class));
        registerAtlas(UiAtlas, QAssetMaster.InternalManager.get(UiAtlas,TextureAtlas.class));
        registerAtlas(HamsterAtlas,QAssetMaster.InternalManager.get(HamsterAtlas,TextureAtlas.class));

        registerNinePatch(DUMMY, new NinePatch(getImg(DUMMY),2,2,2,2));
        registerNinePatch(DIALOG_DUMMY, new NinePatch(getImg(DIALOG_DUMMY),1,30,1,30));
/*
        for (NinePatchCfR ninePatch : CfManager.ins().ninePatch.records) {
            TextureRegion texReg = getImg(ninePatch.getPath());
            System.out.println(ninePatch.getPath());
            registerNinePatch(ninePatch.getPath(),
                new NinePatch(texReg,
                    ninePatch.getLeft(),
                    ninePatch.getRight(),
                    ninePatch.getTop(),
                    ninePatch.getBottom())
            );
        }*/


    }

    public static void loadSingleAtlas(String path) {
        AssetManager manager = QAssetMaster.getAssetManager(path);
        String processedPath = QAssetMaster.getPath(path);
        if(manager.isLoaded(processedPath)) return;
        manager.load(processedPath, TextureAtlas.class);
        manager.finishLoading();
        registerAtlas(getAtlasName(path), (TextureAtlas) manager.get(processedPath));
    }

    public static String getAtlasName(String atlasPath) {
        // Split the path by "/"
        String[] parts = atlasPath.split("/");

        // Get the last part of the path, which is the filename with extension
        String filenameWithExtension = parts[parts.length - 1];

        // Split the filename by "." to separate the filename and extension
        String[] filenameParts = filenameWithExtension.split("\\.");

        // Get the filename without extension
        return filenameParts[0] + "/";
    }

    private static final Hashtable<String,TextureAtlas> loadedAtlas = new Hashtable<>();
    private static final Hashtable<String,String> loadedAtlasPath = new Hashtable<>();

    public static TextureRegion getImg(String name) {
        for (String key : loadedAtlas.keySet()) {
            if(name.contains(IMG_EXT)) break;
            if(name.contains(key)) {
                String regionName = name.replace(key,"");
                return loadedAtlas.get(key).findRegion(regionName);
            }
        }

        AssetManager manager = QAssetMaster.getAssetManager(name);
        String processedPath = QAssetMaster.getPath(name);

        if(!manager.isLoaded(processedPath)) {
            manager.load(processedPath, Texture.class);
            manager.finishLoading();
        }

        return new TextureRegion(manager.get(processedPath,Texture.class));
    }

    public static TextureAtlas getAtlas(String atlasKey) {
        return loadedAtlas.get(atlasKey);
    }

    public static void registerAtlas(String atlasPath, TextureAtlas textureAtlas) {
        String atlasKey = getAtlasName(atlasPath);
        System.out.println(atlasPath + "-" + textureAtlas);
        loadedAtlas.put(atlasKey,textureAtlas);
        loadedAtlasPath.put(atlasKey,atlasPath);
    }

    public static void unRegisterAtlas(String atlasKey) {
        loadedAtlas.remove(atlasKey);
    }

    public static String getAtlasFromPath(String path) {
        for (String key : loadedAtlasPath.keySet()) {
            if(path.contains(key)) {
                return loadedAtlasPath.get(key);
            }
        }
        return "";
    }




}
