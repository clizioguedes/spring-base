package com.acer.sellercenter.sellercenter.mappers;

import com.acer.sellercenter.sellercenter.utils.exception.ConversionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Parser {

    private static final Logger logger = LoggerFactory.getLogger(Parser.class);


    public static <T, U> U parse(T source, Class<U> targetClass) throws ConversionException {
        U target;
        try {
            target = targetClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException
                 | InvocationTargetException | NoSuchMethodException e) {

            logger.error(e.getMessage());
            throw new ConversionException("Erro ao converter dado");
        }

        Field[] sourceFields = source.getClass().getDeclaredFields();
        Field[] targetFields = targetClass.getDeclaredFields();

        for (Field sourceField : sourceFields) {
            for (Field targetField : targetFields) {
                if (sourceField.getName().equals(targetField.getName()) &&
                        sourceField.getType().equals(targetField.getType())) {
                    sourceField.setAccessible(true);
                    targetField.setAccessible(true);

                    try {
                        targetField.set(target, sourceField.get(source));
                    } catch (IllegalAccessException e) {
                        logger.error(e.getMessage());
                        throw new ConversionException("Erro ao converter dado");
                    }
                }
            }
        }
        return target;
    }
}
