package com.nhnacademy.test_Controller;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ControllerFactory {
    private final ConcurrentMap<String, Object> beanMap = new ConcurrentHashMap<>();

    public void init(Set<Class<?>> classes) {
        if (classes == null) return;

        for (Class<?> clazz : classes) {
            if (!Command.class.isAssignableFrom(clazz)) {
                continue;
            }

            RequestMapping mapping = clazz.getAnnotation(RequestMapping.class);
            if (mapping == null) {
                continue;
            }

            String key = buildKey(mapping.method().name(), mapping.value());

            try {
                Object instance = clazz.getDeclaredConstructor().newInstance();

                Object prev = beanMap.putIfAbsent(key, instance);
                if (prev != null) {
                    throw new IllegalStateException("Duplicate mapping: " + key
                            + " -> " + prev.getClass().getName() + ", " + clazz.getName());
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed to create controller: " + clazz.getName(), e);
            }
        }
    }

    private String buildKey(String method, String path) {
        return method.toUpperCase() + ":" + path;
    }
}