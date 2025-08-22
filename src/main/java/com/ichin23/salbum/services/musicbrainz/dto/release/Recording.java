package com.ichin23.salbum.services.musicbrainz.dto.release;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Recording(
        String id,
        Integer length,
        String title,
        @JsonProperty("first-release-date")String firstReleaseDate
) {
}
