package com.br.nandoks.robinson_fishoe.species;

import com.br.nandoks.robinson_fishoe.enums.Status;
import com.br.nandoks.robinson_fishoe.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String scientificName;

    private String commonName;
    private String family;
    private String genus;
    private String distributionNotes;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status; // DRAFT, PENDING, PUBLISHED

    // Auditing (who created this)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Relations (mapped by the child side)
    @OneToMany(mappedBy = "species", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();
}

