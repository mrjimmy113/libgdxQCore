package com.quang.core.config;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class DataTableCreatorBase {

    public static final String CSV_PATH = "configs/";

    private static final String OUTPUT_PATH = "android/assets/configs/";

    public static void createCf(Class<?> cf, Class<?> cfR, String file) throws IOException, InvocationTargetException,
            InstantiationException, IllegalAccessException, NoSuchMethodException {
        createCf(cf, cfR,file, OUTPUT_PATH,cf.getSimpleName());
    }

    public static void createCf(Class<?> cf, Class<?> cfR, String file, String outputPath, String name) throws IOException, InvocationTargetException,
            InstantiationException, IllegalAccessException, NoSuchMethodException {
        saveCf(name,cf.getConstructor(List.class).newInstance(loadCfR(cfR,file)),outputPath);
    }


    public static <T extends Object> List<T> loadCfR(Class<T> cfR, String file) throws IOException {
        List<T> result = new CsvToBeanBuilder<>(new FileReader(file))
                .withType(cfR)
                .build()
                .parse();

        return result;
    }


    public static void saveCf(String name, Object obj, String outputPath) throws IOException {
        FileOutputStream f = new FileOutputStream(new File(outputPath + name));
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(obj);
        o.close();
        f.close();
    }


}
