package com.quang.core.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public final class Event<T>
{

    public void removeListener(T namedEventHandlerMethod)
    {
        listeners.remove(namedEventHandlerMethod);
    }

    public void removeAllListener() {
        listeners.clear();
    }

    private Set<T> listeners = new HashSet<>();
    public void addListener(T unnamedEventHandlerMethod)
    {
        listeners.add(unnamedEventHandlerMethod);
    }


    public Collection<T> listeners()
    {
        return listeners;
    }

    public boolean isEmpty() {
        return listeners.isEmpty();
    }

}
