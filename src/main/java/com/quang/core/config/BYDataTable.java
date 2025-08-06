package com.quang.core.config;


import java.io.Serializable;
import java.util.List;

public abstract class BYDataTable<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public List<T> records;

    public BYDataTable() {
    }

    public BYDataTable(List<T> records) {
        this.records = records;
    }

    public List<T> getRecords() {
        return records;
    }

    public T getById(int id) {
        if (id <= 0 || id > records.size()) return null;

        T result = records.get(id - 1);
        if (getIdOfRecord(result) != id) {
            result = null;
            System.out.println("GET BY ID FAILED");
            for (T s : records) {
                if (getIdOfRecord(s) == id) {
                    result = s;
                }
            }
        }
        return result;
    }

    public abstract int getIdOfRecord(T record);
}
