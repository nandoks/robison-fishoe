package com.br.nandoks.robinson_fishoe.species;

import com.br.nandoks.robinson_fishoe.enums.Status;

public record CreateSpeciesInput(
        String scientificName,
        String commonName,
        String family,
        String genus,
        String distributionNotes,
        String description,
        Status status,
        String imageUrl
) {
}
