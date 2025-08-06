package com.quang.core.encoder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.graphics.glutils.PixmapTextureData;
import com.badlogic.gdx.utils.Array;

public class TextureByteLoader extends AsynchronousAssetLoader<Texture, TextureLoader.TextureParameter> {
    public static class TextureLoaderInfo {
        String filename;
        TextureData data;
        Texture texture;
        byte[] bytes;
    }

    TextureLoaderInfo info = new TextureLoaderInfo();

    public TextureByteLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, TextureLoader.TextureParameter parameter) {
        info.filename = fileName;

        byte[] bytes = manager.getFileHandleResolver().resolve(fileName).readBytes();
        bytes = Encrypt.getProceededByte(bytes);

        if (parameter == null || parameter.textureData == null) {
            Pixmap.Format format = null;
            boolean genMipMaps = false;
            info.texture = null;

            if (parameter != null) {
                format = parameter.format;
                genMipMaps = parameter.genMipMaps;
                info.texture = parameter.texture;
            }
            Pixmap pixmap = new Pixmap(bytes, 0, bytes.length);
            info.data = new PixmapTextureData(pixmap, format, genMipMaps, false);
        } else {
            info.data = parameter.textureData;
            info.texture = parameter.texture;
        }
        info.bytes = bytes;
        if (!info.data.isPrepared()) info.data.prepare();
    }

    @Override
    public Texture loadSync(AssetManager manager, String fileName, FileHandle file, TextureLoader.TextureParameter parameter) {
        if (info == null) return null;
        Texture texture = info.texture;
        if (texture != null) texture.load(info.data);
        else texture = new Texture(info.data);
        if (parameter != null) {
            texture.setFilter(parameter.minFilter, parameter.magFilter);
            texture.setWrap(parameter.wrapU, parameter.wrapV);
        }
        return texture;
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, TextureLoader.TextureParameter parameter) { return null; }
}
