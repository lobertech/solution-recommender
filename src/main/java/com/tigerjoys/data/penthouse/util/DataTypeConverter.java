package com.tigerjoys.data.penthouse.util;

import java.util.ArrayList;
import java.util.List;

public class DataTypeConverter {
    public static List<String> convertObjListToStrList(List<Object> objects) {
        List<String> values = null;
        if (objects != null) {
            values = new ArrayList<>();
            for (Object object : objects) {
                if (object instanceof String) {
                    values.add((String) object);
                }
                else {
                    return null;
                }
            }
        }
        return values;
    }
}
