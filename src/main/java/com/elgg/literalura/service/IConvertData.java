package com.elgg.literalura.service;

public interface IConvertData {
    <T> T convertData(String json, Class<T> classToConvert);
}
