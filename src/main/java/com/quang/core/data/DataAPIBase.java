package com.quang.core.data;

import com.quang.core.utils.IQDo;


import java.util.Hashtable;

public class DataAPIBase<T extends DataModelBase> {
    protected T dataModel = null;
    protected Hashtable<String, DataEvent> dicEvent = new Hashtable<>();

    public void registerEvent(String path, DataEvent.OnDataChange callBack) {
        unregisterEvent(path,callBack);
        if (dicEvent.containsKey(path)) {

            dicEvent.get(path).onDataChangeEvent.addListener(callBack);
        }else {
            DataEvent event = new DataEvent();
            event.onDataChangeEvent.addListener(callBack);
            dicEvent.put(path,event);
        }
    }

    public void registerEventDic(String path, int id , DataEvent.OnDataChange callBack) {
        registerEvent(path + "/" + DataUtilities.ToKey(id), callBack);
    }

    public void unregisterEvent(String path, DataEvent.OnDataChange callBack) {
        if(dicEvent.containsKey(path)) {
            dicEvent.get(path).onDataChangeEvent.removeListener(callBack);
        }
    }

    private void onDataChange(String path, Object data) {
        if (dicEvent.containsKey(path)) {
            dicEvent.get(path).RaiseDataChange(data);
        }
    }

    public void setup(T newDataModel,IQDo callBack) {
        dataModel = newDataModel;
        if(dataModel.loadData()) {
            if(callBack != null) callBack.invoke();
        }else {
            dataModel.createNewData();
            dataModel.checkLoginData();
            if(callBack != null) callBack.invoke();
        }
        dataModel.onDataChange.onDataTriggerEvent.addListener(new DataEventTrigger.OnDataTrigger() {
            @Override
            public void invoke(String path, Object data) {
                onDataChange(path,data);
            }
        });
    }

    public boolean isInit() {
        return dataModel != null;
    }

    public void flushData() {
        dataModel.pushData();

    }

    public void clearEvent() {
        dicEvent.clear();
    }
}
