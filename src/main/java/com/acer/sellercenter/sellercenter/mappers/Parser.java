package com.acer.sellercenter.sellercenter.mappers;

import java.lang.reflect.Field;

public class Parser {
    public static <T, U> U parse(T source, Class<U> targetClass) throws Exception {
        U target = targetClass.getDeclaredConstructor().newInstance();

        Field[] sourceFields = source.getClass().getDeclaredFields();
        Field[] targetFields = targetClass.getDeclaredFields();

        for (Field sourceField : sourceFields) {
            for (Field targetField : targetFields) {
                if (sourceField.getName().equals(targetField.getName()) &&
                        sourceField.getType().equals(targetField.getType())) {
                    sourceField.setAccessible(true);
                    targetField.setAccessible(true);

                    targetField.set(target, sourceField.get(source));
                }
            }
        }
        return target;
    }
}
