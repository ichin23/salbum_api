package com.ichin23.salbum.converters;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ichin23.salbum.domain.lastfm.artist.TagLastFMResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TagDeserializer extends JsonDeserializer<List<TagLastFMResponse>> {
    @Override
    public List<TagLastFMResponse> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        JsonNode node = mapper.readTree(jsonParser);

        List<TagLastFMResponse> tags = new ArrayList<>();

        if (node.isArray()) {
            // Caso esperado: se for um array, desserializa normalmente
            for (JsonNode tagNode : node) {
                tags.add(mapper.treeToValue(tagNode, TagLastFMResponse.class));
            }
        } else if (node.isObject()) {
            // Caso de inconsistência: se for um objeto, adiciona a uma nova lista
            tags.add(mapper.treeToValue(node, TagLastFMResponse.class));
        }

        return tags;
    }
}