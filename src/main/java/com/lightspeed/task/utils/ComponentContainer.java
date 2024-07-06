package com.lightspeed.task.utils;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public final class ComponentContainer {
    private static final Map<String, Object> CONTAINER = new HashMap<>();

    private ComponentContainer() {}

    public static void initializeContext() {
        var reflection = new Reflections("com.lightspeed.task");
        reflection.getTypesAnnotatedWith(Component.class)
                .forEach(singleton -> CONTAINER.put(singleton.getSimpleName(), createInstance(singleton)));
    }

    public static <T> T get(Class<T> clazz) {
        return clazz.cast(CONTAINER.getOrDefault(clazz.getSimpleName(), createInstance(clazz)));
    }

    public static Object createInstance(Class<?> clazz) {
        try {
            var defaultConstructor = clazz.getDeclaredConstructor();
            return defaultConstructor.newInstance();
        }
        catch (NoSuchMethodException e) {
            for (var constructor : clazz.getDeclaredConstructors()) {
                var parameterTypes = constructor.getParameterTypes();
                var parameters = new Object[parameterTypes.length];
                for (int i = 0; i < parameterTypes.length; i++) {
                    parameters[i] = createInstance(parameterTypes[i]);
                }
                try {
                    return constructor.newInstance(parameters);
                }
                catch (InstantiationException | IllegalAccessException | InvocationTargetException ex) {
                    return null;
                }
            }

            throw new IllegalStateException("No suitable constructor found for " + clazz.getName());
        }
        catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("Failed to create an instance of " + clazz.getName(), e);
        }
    }

}
