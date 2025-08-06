package com.quang.core.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ShaderProgramLoader;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class QShaderAsset {
    private static final String SHADER = "shaders/";
    public static final String CUT_1 = SHADER + "cut1/cut1";
    public static final String CUT_2 = SHADER + "cut2/cut2";
    public static final String CUT_3 = SHADER + "cut3/cut3";
    public static final String DEFAULT = SHADER + "default/default";
    public static final String TRANSITION = SHADER + "transition/transition";
    public static final String TRANSITION_STROKE = SHADER + "transitionStroke/transitionStroke";
    public static final String GRAY_SCALE = SHADER + "grayScale/grayScale";


    private static AssetManager manager;

    public static void loadShader(AssetManager m) {
        manager = m;
        loadSingleShader(DEFAULT);
/*        loadSingleShader(CUT_1);
        loadSingleShader(CUT_2);
        loadSingleShader(CUT_3);
        loadSingleShader(TRANSITION);
        loadSingleShader(TRANSITION_STROKE);*/
        loadSingleShader(GRAY_SCALE);
    }

    private static void  loadSingleShader(final String name) {
        manager.load(shaderFragmentPath(name),
                ShaderProgram.class, new ShaderProgramLoader.ShaderProgramParameter(){
                    {
                        fragmentFile = shaderFragmentPath(name);
                        vertexFile = shaderVertexPath(name);
                    }
                });

    }

    public static ShaderProgram getShader(String name) {
        return  manager.get(shaderFragmentPath(name));
    }


    private static String shaderFragmentPath(String path) {
        return path + "_frag.glsl";
    }
    private static String shaderVertexPath(String path) {
        return path + "_vert.glsl";
    }
}
