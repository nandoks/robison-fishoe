package com.br.nandoks.robinson_fishoe.utils.coordinate;

import com.br.nandoks.robinson_fishoe.species.Species;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
public class CoordinateController {

    @Autowired
    private CoordinateService coordinateService;

    @QueryMapping
    public List<Coordinate> coordinates() {
        return coordinateService.findAll();
    }

    @QueryMapping
    public List<Coordinate> coordinatesBySpecies(@Argument Long speciesId) {
        return coordinateService.findBySpeciesId(speciesId);
    }

    @QueryMapping
    public Coordinate coordinateById(@Argument Long id) {
        return coordinateService.findById(id);
    }

    @SchemaMapping
    public Species species(Coordinate coordinate) {
        return coordinate.getSpecies();
    }

    @MutationMapping
    public Coordinate createCoordinate(
            @Argument Long speciesId,
            @Argument CreateCoordinateInput input
    ) {
        return coordinateService.createCoordinate(input, speciesId);
    }

    @MutationMapping
    public Boolean deleteCoordinate(@Argument Long id) {
        coordinateService.deleteCoordinate(id);
        return true;
    }
}