package com.foxmindedjavaspring.university.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Utils {
    public Map<String, Object> getMapSinglePair(String name, 
            Object object) {
        Map<String, Object> map = new HashMap<>();  
        map.put(name, object);
        return map;
    }
}
