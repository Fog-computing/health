package cn.ac.sec.util;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JSONUtil {
    private static ObjectMapper objectMapper;

    public static <T> T readValue(String content, Class<T> valueType) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper ();
        }
        try {
            return objectMapper.readValue (content, valueType);
        } catch (Exception e) {
            e.printStackTrace ();
        }

        return null;
    }

    public static String toJSon(Object object) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper ();
        }
        try {
            return objectMapper.writeValueAsString (object);
        } catch (Exception e) {
            e.printStackTrace ();
        }

        return null;
    }

    public static <T> List<T> readListValue(String content, Class<T> valueType) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper ();
        }

        try {
            return (List<T>) objectMapper.readValue (content, new TypeReference<List<T>> () {
            });
        } catch (Exception e) {
            e.printStackTrace ();
        }

        return null;

    }

}
