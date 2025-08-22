package com.ichin23.salbum.services.musicbrainz.dto.release;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ArtistLookup(
        String id,
        String name,
        String type,
        @JsonProperty("sort-name") String sortName,
        String country
) {
}
