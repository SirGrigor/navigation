package com.house.navigation.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StringToUUIDConvertor implements Converter<String, UUID> {

    @Override
    public UUID convert(String uuid) {
        try {
            return UUID.fromString(uuid);
        }
        catch (IllegalArgumentException ex){
            throw new IllegalArgumentException("Invalid input UUID string");
        }
    }
}