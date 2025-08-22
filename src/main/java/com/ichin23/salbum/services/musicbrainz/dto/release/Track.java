package com.ichin23.salbum.services.musicbrainz.dto.release;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Track(
        Integer length,
        Integer number,
        Integer position,
        String title,
        Recording recording,
        @JsonProperty("artist-credit") List<ArtistCreditLookup> artistCredit
) {
}
