package com.quang.core.data;

import java.util.ArrayList;
import java.util.Arrays;

public class DataUtilities {
    public static String ToDicKey(String dicName, Object key) {
        return dicName + "/" + ToKey(key);
    }

    public static String ToKey(Object text)
    {
        String s = "K_" + text.toString();
        return s;
    }

    public static int ReverseKey(String text)
    {
        String s = text.replace("K_", "");
        return Integer.parseInt(s);
    }

    public static ArrayList<String> getPath(String path)
    {
        return new ArrayList<String>(Arrays.asList(path.split("/")));
    }
}
