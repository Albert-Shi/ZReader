package com.shishuheng.zreader.dao;

import java.lang.reflect.Field;
import java.util.List;

public class ObjectToTable {

    public static String getCreateSQL(Object object) {
        String sql = null;
        Field[] fields = object.getClass().getDeclaredFields();
        if (fields != null) {
            sql = "CREATE TABLE ";
            String tableName = object.getClass().getName();
            sql += tableName;
            sql += "(";
            for (Field field : fields) {
                String type = field.getType().getName();
                String name = field.getName();
                if (type.equals("byte") || type.equals("java.lang.Byte")) {
                    sql += name;
                    sql += " TINYINT, ";
                } else if (type.equals("short") || type.equals("java.lang.Short")) {
                    sql += name;
                    sql += " SMALLINT, ";
                } else if (type.equals("int") || type.equals("java.lang.Integer")) {
                    sql += name;
                    sql += " INT, ";
                } else if (type.equals("long") || type.equals("java.lang.Long")) {
                    sql += name;
                    sql += " BIGINT, ";
                } else if (type.equals("float") || type.equals("java.lang.Float")) {
                    sql += name;
                    sql += " FLOAT, ";
                } else if (type.equals("double") || type.equals("java.lang.Double")) {
                    sql += name;
                    sql += " DOUBLE, ";
                } else if (type.equals("boolean") || type.equals("java.lang.Boolean")) {
                    sql += name;
                    sql += " VARCHAR(4), ";
                } else if (type.equals("char") || type.equals("java.lang.Character")) {
                    sql += name;
                    sql += " VARCHAR(1), ";
                } else if (type.equals("java.lang.String")) {
                    sql += name;
                    sql += " TEXT, ";
                } else if (type.equals("java.lang.Date")) {
                    sql += name;
                    sql += " DATE, ";
                } else if (type.equals("java.io.File")) {
                    sql += name;
                    sql += " TEXT, ";
                }
            }
            sql = sql.substring(0, sql.length() - 2);
            sql += ")";
        }
        return sql;
    }

    public static String[] getCreateSQLs(Object[] objects) {
        String[] sqls = null;
        if (objects != null) {
            sqls = new String[objects.length];
            for (int i = 0; i < sqls.length; i++) {
                sqls[i] = getCreateSQL(objects[i]);
            }
        }
        return sqls;
    }
}