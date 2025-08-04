package com.ichin23.salbum.converters.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Converter(autoApply = false) // Marcamos como false para aplicar manualmente na entidade
public class MapJsonConverter implements AttributeConverter<Map<String, Object>, String> {

    // ObjectMapper é thread-safe, então podemos usar uma única instância
    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, Object> attribute) {
        if (attribute == null) {
            return null;
        }
        try {
            // Converte o Map<String, Object> para uma string JSON
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            // Lidar com erros de serialização (log, lançar RuntimeException, etc.)
            throw new RuntimeException("Erro ao serializar Map para JSON: " + e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().isEmpty()) {
            return new HashMap<>(); // Retorna um mapa vazio se a string for nula ou vazia
        }
        try {
            // Converte a string JSON para um Map<String, Object>
            // Usamos TypeReference para lidar com tipos genéricos corretamente
            return objectMapper.readValue(dbData, Map.class);
            // Alternativamente, para maior segurança de tipo se você souber que é Map<String, Object>:
            // return objectMapper.readValue(dbData, new TypeReference<Map<String, Object>>() {});
        } catch (IOException e) {
            // Lidar com erros de desserialização
            throw new RuntimeException("Erro ao desserializar JSON para Map: " + e.getMessage(), e);
        }
    }
}