package com.ichin23.salbum.domain.review;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Table(name="reviews")
@Entity
public class Review implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


}
