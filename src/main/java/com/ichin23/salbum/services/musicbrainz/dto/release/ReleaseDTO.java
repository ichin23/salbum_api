package com.ichin23.salbum.services.musicbrainz.dto.release;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ReleaseDTO(
        String date,
        String barcode,
        String country,
        String status,
        String title,
        String id,
        String image,
        List<ReleaseRelation> relations,
        List<ReleaseMedia> media,
        @JsonProperty("artist-credit") List<ArtistLookup> artistCredit
) {
    public ReleaseDTO withImage(String newImage) {
        return new ReleaseDTO(
                this.date,
                this.barcode,
                this.country,
                this.status,
                this.title,
                this.id,
                newImage,
                this.relations,
                this.media,
                this.artistCredit
        );
    }

}
