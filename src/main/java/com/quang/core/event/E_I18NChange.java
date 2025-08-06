package com.quang.core.event;

import com.quang.core.utils.Event;

public class E_I18NChange {

    public interface II18NChange {
        void invoke();
    }

    public static Event<II18NChange> e = new Event<>();

    public static void RaiseEvent() {
        for (II18NChange listener : e.listeners()) {
            listener.invoke();
        }
    }
}
