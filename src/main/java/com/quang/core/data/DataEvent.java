package com.quang.core.data;


import com.quang.core.utils.Event;

import java.util.Iterator;

public class DataEvent {

    public interface OnDataChange {
        void invoke(Object data, Iterator<OnDataChange> iterator);
    }

    public Event<OnDataChange> onDataChangeEvent = new Event<>();

    public void RaiseDataChange(Object param) {
        Iterator<OnDataChange> iterator = onDataChangeEvent.listeners().iterator();

        // Loop through the list and remove elements that meet a certain condition
        while (iterator.hasNext()) {
            OnDataChange element = iterator.next();
            element.invoke(param,iterator);
        }
    }
}
