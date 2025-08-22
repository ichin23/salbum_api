package com.ichin23.salbum.services.musicbrainz.dto.releaseGroup;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ReleaseGroup(
        String id,
        Integer score,
        Integer count,
        String title,
        String image,
        @JsonProperty("first-release-date") String firstRelease,
        @JsonProperty("primary-type") String primaryType,
        List<Release> releases
) {
}
