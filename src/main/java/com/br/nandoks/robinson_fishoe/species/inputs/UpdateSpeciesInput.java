package com.br.nandoks.robinson_fishoe.species.inputs;

import com.br.nandoks.robinson_fishoe.enums.Status;

public record UpdateSpeciesInput(
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
