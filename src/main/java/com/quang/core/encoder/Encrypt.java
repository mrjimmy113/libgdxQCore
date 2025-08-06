package com.quang.core.encoder;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureAtlasLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class Encrypt {
    private final static String zenText = ".";
    private final static int keyCrypt = 5;
    private static boolean encrypt;

    public static void protectAssets(boolean encrypt, AssetManager assets, String root) {
        assets.setLoader(Texture.class,new TextureByteLoader(assets.getFileHandleResolver()));
        //assets.setLoader(TSkeletonLoader.GSkeletonData.class,new TSkeletonLoader(new InternalFileHandleResolver()));
        if (Gdx.app.getType() != Application.ApplicationType.Desktop) return;
        Encrypt.encrypt = encrypt;
        FileHandle file = new FileHandle(root);
        if (!file.isDirectory()) proceedFile(file);
        else for (FileHandle child : file.list()) proceedFolder(child);
    }

    private static void proceedFolder(FileHandle file) {
        if (!file.isDirectory()) proceedFile(file);
        else for (FileHandle file_child : file.list()) proceedFolder(file_child);
    }
    private static void proceedFile(FileHandle file) {
        if (Array.with("png","jpg").contains(file.extension(),false))
            if (encrypt) encrypt(file);
            else decrypt(file);
    }

    public static byte[] getProceededByte(byte[] bytes) { return !isEncyptedFile(bytes)?bytes:getDecryptBytes(bytes); }

    private static boolean isEncyptedFile(byte[] bytes) {
        String s = "";
        for (int i = 0; i < zenText.length(); i++) s += (char)bytes[bytes.length-zenText.length()+i];
        return s.equals(zenText);
    }

    private static void encrypt(FileHandle file) {
        byte[] bytes = file.readBytes();
        if (isEncyptedFile(bytes)) return;
        file.writeBytes(getEncryptBytes(bytes),false);
        file.writeBytes(zenText.getBytes(),true);
    }
    private static void decrypt(FileHandle file) {
        byte[] bytes = file.readBytes();
        if (!isEncyptedFile(bytes)) return;
        file.writeBytes(getDecryptBytes(bytes), false);
    }

    private static byte encryptByte(byte normal) { return (byte) (normal+keyCrypt); }
    private static byte decryptByte(byte normal) { return (byte) (normal-keyCrypt); }

    private static byte[] getEncryptBytes(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) bytes[i] = encryptByte(bytes[i]);
        return bytes;
    }
    private static byte[] getDecryptBytes(byte[] bytes) {
        byte[] newBytes = new byte[bytes.length - zenText.length()];
        for (int i = 0; i < newBytes.length; i++) newBytes[i] = decryptByte(bytes[i]);
        return newBytes;
    }
    private static byte[] getBytes(FileHandle file) {
        byte[] bytes = file.readBytes();
        if (isEncyptedFile(bytes)) bytes = getDecryptBytes(file.readBytes());
        return bytes;
    }
}
