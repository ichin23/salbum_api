package com.ichin23.salbum.services.musicbrainz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ichin23.salbum.services.musicbrainz.dto.releaseGroup.ReleaseGroup;

import java.util.List;

public record ReleaseGroupDTO(
        Integer count,
        @JsonProperty("release-groups") List<ReleaseGroup> releaseGroups
) {}