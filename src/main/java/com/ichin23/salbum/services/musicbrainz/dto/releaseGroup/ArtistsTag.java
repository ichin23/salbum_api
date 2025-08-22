package com.ichin23.salbum.services.musicbrainz.dto.releaseGroup;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record ArtistsTag(
        @JsonProperty("count") Integer count,
        @JsonProperty("offset") Integer offset,
        @JsonProperty("artists") List<String> artists
) {}