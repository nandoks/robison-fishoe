package com.br.nandoks.robinson_fishoe.species;

import com.br.nandoks.robinson_fishoe.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "species")
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String scientificName;

    private String commonName;
    private String family;
    private String genus;
    private String distributionNotes; // GEOGRAPHY: Range, depth, habitat type, migration patterns

    @Column(columnDefinition = "TEXT")
    private String description; // BIOLOGY: Morphology, color, size, diet, behavior, reproduction.

    @Enumerated(EnumType.STRING)
    private Status status; // DRAFT, PENDING, PUBLISHED

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Relations (mapped by the child side)
    @OneToMany(mappedBy = "species", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

}

