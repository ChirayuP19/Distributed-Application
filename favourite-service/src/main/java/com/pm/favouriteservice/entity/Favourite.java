package com.pm.favouriteservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "favourites")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Favourite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private Long productId;
}
