package com.quang.core.config;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.quang.core.assets.QAssetMaster;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ConfigManager {

    public Object getBaseConfig(String fileName) throws IOException, ClassNotFoundException {
        return getConfig("configs/" + fileName);
    }

    public Object getConfig(String filePath, FileHandleResolver resolver) throws IOException, ClassNotFoundException {
        FileHandle fileHandle = resolver.resolve(filePath);
        ObjectInputStream oi = new ObjectInputStream(fileHandle.read());
        Object obj = oi.readObject();
        oi.close();
        return obj;
    }

    public Object getConfig(String filePath) throws IOException, ClassNotFoundException {
        return getConfig(filePath, QAssetMaster.InternalResolver);
    }
}
