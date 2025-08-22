package com.ichin23.salbum.services.musicbrainz.dto.release;

import java.util.List;

public record ReleaseMedia(
        String id,
        List<Track> tracks
) {
}
