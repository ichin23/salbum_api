package com.ichin23.salbum.converters;
// Inside your package
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.ichin23.salbum.domain.lastfm.track.TrackResponseLastFM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrackDeserializer extends JsonDeserializer<List<TrackResponseLastFM>> {

    @Override
    public List<TrackResponseLastFM> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        List<TrackResponseLastFM> tracks = new ArrayList<>();

        if (node.isArray()) {
            // If it's an array, deserialize it as a list
            for (JsonNode objNode : node) {
                tracks.add(oc.treeToValue(objNode, TrackResponseLastFM.class));
            }
        } else {
            // If it's a single object, deserialize it and add it to a new list
            tracks.add(oc.treeToValue(node, TrackResponseLastFM.class));
        }

        return tracks;
    }
}