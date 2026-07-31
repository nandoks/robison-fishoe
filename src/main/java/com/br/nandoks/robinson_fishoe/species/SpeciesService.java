package com.br.nandoks.robinson_fishoe.species;

import com.br.nandoks.robinson_fishoe.enums.Status;
import com.br.nandoks.robinson_fishoe.species.inputs.CreateSpeciesInput;
import com.br.nandoks.robinson_fishoe.species.inputs.UpdateSpeciesInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeciesService {

    @Autowired
    private SpeciesRepository speciesRepository;

    public List<Species> findAll() {
        return speciesRepository.findAll();
    }

    public Species findById(Long id) {
        return speciesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Species not found with id: " + id));
    }

    public Species createSpecies(CreateSpeciesInput input) {
        System.out.println(input);
        Species species = new Species();
        species.setScientificName(input.scientificName());
        species.setCommonName(input.commonName());
        species.setFamily(input.family());
        species.setGenus(input.genus());
        species.setDistributionNotes(input.distributionNotes());
        species.setDescription(input.description());
        species.setStatus(Status.DRAFT);
        species.setImageUrl(input.imageUrl());
        return speciesRepository.save(species);
    }

    public Species updateSpecies(Long id, UpdateSpeciesInput input) {
        Species species = findById(id);

        if (input.scientificName() != null) species.setScientificName(input.scientificName());
        if (input.commonName() != null) species.setCommonName(input.commonName());
        if (input.family() != null) species.setFamily(input.family());
        if (input.genus() != null) species.setGenus(input.genus());
        if (input.distributionNotes() != null) species.setDistributionNotes(input.distributionNotes());
        if (input.description() != null) species.setDescription(input.description());
        if (input.status() != null) species.setStatus(input.status());
        if (input.imageUrl() != null) species.setImageUrl(input.imageUrl());

        return speciesRepository.save(species);
    }

    public void deleteSpecies(Long id) {
        Species species = findById(id);
        speciesRepository.delete(species);
    }

    public List<Species> searchSpecies(String textInput) {
        return speciesRepository.search(textInput);
    }

}