package com.ichin23.salbum.domain.ratings.dto;

import java.util.UUID;

public record RatingInputDTO(UUID album_id, Double rate) {

}
