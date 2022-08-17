package com.foxmindedjavaspring.university;

import java.util.HashMap;
import java.util.Map;

public final class Utils {

    private Utils() {
    }

    public static Map<String, Object> getMapSinglePair(String name, 
            Object object) {
        Map<String, Object> map = new HashMap<>();  
        map.put(name, object);
        return map;
    }
    
}
