package com.quang.core.data;


import com.quang.core.utils.Event;

public class DataEventTrigger {
    public interface OnDataTrigger {
        void invoke(String path, Object data);
    }

    public Event<OnDataTrigger> onDataTriggerEvent = new Event<>();

    public void RaiseDataChange(String path,Object param) {
        for (OnDataTrigger listener : onDataTriggerEvent.listeners()) {
            listener.invoke(path,param);
        }
    }
}
