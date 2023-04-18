package com.micro.common.util.other;


import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author XiongJiaMin
 * @apiNote clz
 * @since 2022-11-23 13:41
 **/
@SuppressWarnings("unused")
public abstract class ClassLocator {

    public static <T>T getInstance (Class<T> clz, Object... args) {
        try {
            Constructor<T> constructor = clz.getDeclaredConstructor(args.getClass());
            constructor.setAccessible(true);
            return constructor.newInstance(args);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException(String.join(" ", "no constructor found:", clz.getSimpleName(), "args:", args.getClass().getSimpleName()), e);
        }
    }

    public static Map<Object, List<Annotation>> getFieldsToAnnotation (Object obj) {
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        Map<Object, List<Annotation>> fields = new HashMap<>(16);
        if (!CommonUtils.isEmpty(declaredFields)) {
            try {
                for (Field field : declaredFields) {
                    Annotation[] annotations = field.getAnnotations();
                    if (!CommonUtils.isEmpty(annotations)) {
                        Object o = field.get(obj);
                        fields.put(o, new ArrayList<>(Arrays.asList(annotations)));
                    }
                }
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException("field get error", e);
            }
        }
        return fields;
    }
}
