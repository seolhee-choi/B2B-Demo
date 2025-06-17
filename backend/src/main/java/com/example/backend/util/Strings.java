package com.example.backend.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

public abstract class Strings extends StringUtils {
    private Strings() {
    }
    static public List<Map<String, Object>> convertObjectToList(Object obj) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        ObjectMapper oMapper = new ObjectMapper();

        List<Object> list = new ArrayList<>((Collection<?>)obj);

        for(Object object : list) {
            Map<String, Object> map = oMapper.convertValue(object, new TypeReference<Map<String, Object>>() {});
            result.add(map);
        }

        return result ;
    }
    public static boolean isNull(Object obj) {
        boolean dataIsNull = false;

        if (obj == null) {
            dataIsNull = true;
        } else if ((obj instanceof String)) {
            if (((String)obj) == "") {
                dataIsNull = true;
            } else if (((String)obj) == "null") {
                dataIsNull = true;
            } else if (((String)obj).isBlank()) {
                dataIsNull = true;
            } else if (((String)obj).isEmpty()) {
                dataIsNull = true;
            }
        } else if (obj instanceof Map) {
            if (((Map<?, ?>)obj).isEmpty()) {
                dataIsNull = true;
            } else if (((Map<?, ?>)obj).size() == 0) {
                dataIsNull = true;
            }
        } else if (obj instanceof List) {
            if (((List<?>)obj).isEmpty()) {
                dataIsNull = true;
            } else if (((List<?>)obj).size() == 0) {
                dataIsNull = true;
            }
        }

        return dataIsNull;
    }
}
