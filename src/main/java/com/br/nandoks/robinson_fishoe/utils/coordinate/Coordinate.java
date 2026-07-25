package com.br.nandoks.robinson_fishoe.utils.coordinate;

import com.br.nandoks.robinson_fishoe.species.Species;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Coordinate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double latitude;
    private Double longitude;

    private String localityName; // e.g., "Off the coast of Belize"

    // Relation
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "species_id", nullable = false)
    private Species species;
}