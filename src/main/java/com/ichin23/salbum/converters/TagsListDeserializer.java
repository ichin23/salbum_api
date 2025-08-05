package com.ichin23.salbum.converters;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ichin23.salbum.domain.lastfm.artist.TagsListLastFMResponse;

import java.io.IOException;

public class TagsListDeserializer extends JsonDeserializer<TagsListLastFMResponse> {
    @Override
    public TagsListLastFMResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = mapper.readTree(p);

        // Caso 1: Se o JSON for um array (provavelmente [])
        if (node.isArray()) {
            return new TagsListLastFMResponse(); // Retorna um objeto vazio e evita a exceção
        }

        // Caso 2: Se o JSON for uma string (provavelmente "")
        if (node.isTextual()) {
            String value = node.asText();
            if (value.isEmpty()) {
                return new TagsListLastFMResponse(); // Retorna um objeto vazio
            }
        }

        // Caso 3: Se o JSON for um objeto (formato esperado com as tags)
        if (node.isObject()) {
            return mapper.treeToValue(node, TagsListLastFMResponse.class);
        }

        // Se não for nenhum dos casos acima, o formato é inesperado
        throw new IllegalArgumentException("Formato de 'tags' inesperado: " + node.getNodeType());
    }
}