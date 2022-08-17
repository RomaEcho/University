package com.foxmindedjavaspring.university;

import java.util.HashMap;
import java.util.Map;

public final class Utils {

    private Utils() {
    }

    public static Map<String, Object> getSingleNamed(String name, 
            Object object) {
        Map<String, Object> namedSingleParameter = new HashMap<>();  
        namedSingleParameter.put(name, object);
        return namedSingleParameter;
    }
    
}
