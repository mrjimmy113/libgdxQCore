package com.quang.core.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Json;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Hashtable;

public class DataModelBase<T> {
    public DataEventTrigger onDataChange = new DataEventTrigger();
    protected Class<T> schemaType;
    protected T data;
    protected Preferences prefs;
    private String prefName = "iqData";

    public DataModelBase(String prefName, Class<T> type) {
        this.prefName = prefName;
        schemaType = type;
    }

    public boolean loadData() {
        prefs = Gdx.app.getPreferences(prefName);
        if(prefs != null) {
            String s = prefs.getString(prefName);
            Json json = new Json();
            data = json.fromJson(schemaType,s);
            if(data != null) {
                checkLoginData();
            }else {
                return false;
            }
            return true;
        }
        return false;
    }

    protected void checkLoginData() {

    }

    public void createNewData() {

    }

    public Preferences getPrefs() {
        return prefs;
    }

    protected void saveData() {
        Json json = new Json();
        String jsonStr = json.toJson(data);
        prefs.putString(prefName, jsonStr);
        prefs.flush();
    }

    //------------------------------- read data -------------------------------------------
    public Object read(String path)  {
        ArrayList<String> paths = DataUtilities.getPath(path);
        try {
            return readDataByPath(data,paths);
        } catch (NoSuchFieldException e) {
            return null;
        } catch (IllegalAccessException e) {
            System.out.println("DataModel: " + e.getClass());
        }

        return null;
    }

    public Object readMapData(String path, int id) {
        ArrayList<String> paths = DataUtilities.getPath(path);
        try {
            return readDataDicByPath(data,paths,id);
        } catch (NoSuchFieldException e) {
            return null;
        } catch (IllegalAccessException e) {
            System.out.println("DataModel: " + e.getClass());
        }

        return null;
    }

    public Object readDataByPath(Object data, ArrayList<String> paths) throws NoSuchFieldException, IllegalAccessException {
        String key = paths.get(0);
        Class<?> type = data.getClass();
        Field fieldInfor = type.getField(key);
        Object dataKey = fieldInfor.get(data);
        if(paths.size() == 1) {
            return  dataKey;
        }else {
            paths.remove(0);
            return  readDataByPath(dataKey,paths);
        }

    }

    public Object readDataDicByPath(Object data, ArrayList<String> paths, int id) throws NoSuchFieldException, IllegalAccessException {
        String key = paths.get(0);
        Class<?> type = data.getClass();
        Field fieldInfor = type.getField(key);
        Object dataKey = fieldInfor.get(data);
        if(paths.size() == 1) {
            Hashtable<String,Object> dic = (Hashtable<String, Object>) dataKey;
            return  dic.get(DataUtilities.ToKey(id));
        }else {
            paths.remove(0);
            return  readDataDicByPath(dataKey,paths,id);
        }

    }

    // ------------------- update data ------------------------
    public void updateData(String path, Object dataNew) {
        ArrayList<String> paths = DataUtilities.getPath(path);

        try {
            updateDataByPath(data,dataNew,paths);
            onDataChange.RaiseDataChange(path,dataNew);
        } catch (NoSuchFieldException e) {
            System.out.println("Data Model: " + e.getMessage());
        } catch (IllegalAccessException e) {
            System.out.println("Data Model: " + e.getMessage());
        }
    }

    public void pushData() {
        saveData();
    }

    public void updateDataByPath(Object data, Object dataNew, ArrayList<String> paths) throws NoSuchFieldException, IllegalAccessException {
        String key = paths.get(0);
        Class<?> type = data.getClass();
        Field fieldInfor = type.getField(key);
        if(paths.size() == 1) {
            fieldInfor.set(data,dataNew);
        }else {
            Object dataKey = fieldInfor.get(data);
            paths.remove(0);
            updateDataByPath(dataKey,dataNew,paths);
        }
    }

    public void updateDataDic (String path, Object key, Object dataNew) {
        ArrayList<String> paths = DataUtilities.getPath(path);

        try {
            Object dataOut = updateDataDicByPath(data,dataNew,paths,key);
            onDataChange.RaiseDataChange(path + "/" + DataUtilities.ToKey(key),dataNew);
            onDataChange.RaiseDataChange(path,dataOut);
        } catch (NoSuchFieldException e) {
            System.out.println("Data Model: " + e.getMessage() + "NoSuchFile" + " - " + path);
        } catch (IllegalAccessException e) {
            System.out.println("Data Model: " + e.getMessage() + "Illegal");
        }
    }


    public Object updateDataDicByPath (Object data, Object dataNew, ArrayList<String> paths, Object key) throws  IllegalAccessException, NoSuchFieldException {
        String fieldName = paths.get(0);
        Class<?> type = data.getClass();
        Object result = null;

        Field fieldInfor = type.getField(fieldName);
        if(paths.size() == 1) {

            Hashtable<String,Object> dic = new Hashtable<>();
            Object dataKey = fieldInfor.get(data);
            if(dataKey != null) {
                dic = (Hashtable<String, Object>) dataKey;
            }
            String key_ = DataUtilities.ToKey(key);
            dic.put(key_,dataNew);
            fieldInfor.set(data,dic);
            result = dic;

        }else {
            Object dataKey = fieldInfor.get(data);
            paths.remove(0);
            result = updateDataDicByPath(dataKey,dataNew,paths,key);
        }
        return result;
    }
}
