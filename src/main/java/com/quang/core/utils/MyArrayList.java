package com.quang.core.utils;

import java.util.ArrayList;

public class MyArrayList<T> extends ArrayList<T> {


    public boolean addNotNull(T item) {
        if(item != null) {
            add(item);
            return true;
        }
        return false;
    }

    public void Exclusive(ArrayList<T> sourceArrayList) {
        ArrayList<T> exclusives = new ArrayList<>();
        for (T anObject : this)
        {
            if (anObject == null) continue;
            if (!sourceArrayList.contains(anObject))
            {
                exclusives.add(anObject);
            }
        }
        this.removeRange(0, this.size());
        this.addAll(exclusives);
    }

    public  ArrayList<T> Distinct()
    {
        ArrayList<T> returnArray = new ArrayList<>();
        for (T someObject : this)
        {
            if (!returnArray.contains(someObject))
            {
                returnArray.add(someObject);
            }
        }
        return returnArray;
    }
}