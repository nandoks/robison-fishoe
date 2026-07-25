package com.br.nandoks.robinson_fishoe.species;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SpeciesController {

    @Autowired
    private SpeciesService speciesService;

    @QueryMapping
    public List<Species> species() {
        return speciesService.findAll();
    }

    @QueryMapping
    public Species speciesById(@Argument Long id) {
        return speciesService.findById(id);
    }

    @MutationMapping
    public Species createSpecies(@Argument CreateSpeciesInput input) {
        return speciesService.createSpecies(input);
    }

    @MutationMapping
    public Species updateSpecies(@Argument Long id, @Argument UpdateSpeciesInput input) {
        return speciesService.updateSpecies(id, input);
    }

    @MutationMapping
    public Boolean deleteSpecies(@Argument Long id) {
        speciesService.deleteSpecies(id);
        return true;
    }


}
