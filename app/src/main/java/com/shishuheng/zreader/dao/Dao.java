package com.shishuheng.zreader.dao;

import java.lang.reflect.Field;
import java.util.List;

public class Dao {
    private List<String> nameList;
    public Field[] getNameList(Object object) {
        if (object != null) {
            Field[] fields = object.getClass().getFields();
            return fields;
        }
        return null;
    }

    public void objectToTable(Object entity) {

    }
}
