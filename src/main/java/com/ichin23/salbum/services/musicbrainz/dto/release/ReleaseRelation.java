package com.ichin23.salbum.services.musicbrainz.dto.release;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ReleaseRelation(
        String type,
        UrlResource url,
        @JsonProperty("target-type") String targetType
) {
}
