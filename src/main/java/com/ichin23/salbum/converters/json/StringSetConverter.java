package com.ichin23.salbum.converters.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Converter(autoApply = false)
public class StringSetConverter implements AttributeConverter<Set<String>, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Set<String> attribute) {
        if (attribute == null || attribute.isEmpty()) return null;
        try { return objectMapper.writeValueAsString(attribute); }
        catch (JsonProcessingException e) { throw new RuntimeException("Error serializing Set<String> to JSON", e); }
    }

    @Override
    public Set<String> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().isEmpty()) return new HashSet<>();
        try { return objectMapper.readValue(dbData, new TypeReference<Set<String>>() {}); }
        catch (IOException e) { throw new RuntimeException("Error deserializing JSON to Set<String>", e); }
    }
}