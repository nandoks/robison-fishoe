package com.br.nandoks.robinson_fishoe.species.inputs;

public record CreateSpeciesInput(
        String scientificName,
        String commonName,
        String family,
        String genus,
        String distributionNotes,
        String description,
        String imageUrl
) {
}
