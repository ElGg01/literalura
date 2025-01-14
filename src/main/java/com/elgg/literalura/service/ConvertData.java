package com.elgg.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertData implements IConvertData{
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T convertData(String json, Class<T> classToConvert) {
        try {
            return objectMapper.readValue(json, classToConvert);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
