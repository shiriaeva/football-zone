package com.footballzone.util;

import java.lang.reflect.Field;

public interface Trimmable {

    default void trim(){
        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object value = field.get(this);
                if (value != null){
                    if (value instanceof String){
                        String trimmed = (String) value;
                        field.set(this, trimmed.trim());
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
