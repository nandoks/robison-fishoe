package com.br.nandoks.robinson_fishoe.utils.coordinate;


import com.br.nandoks.robinson_fishoe.species.Species;
import com.br.nandoks.robinson_fishoe.species.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CoordinateService {

    @Autowired
    private CoordinateRepository coordinateRepository;

    @Autowired
    private SpeciesService speciesService;

    public List<Coordinate> findAll() {
        return coordinateRepository.findAll();
    }

    public List<Coordinate> findBySpeciesId(Long speciesId) {
        return coordinateRepository.findBySpeciesId(speciesId);
    }

    public Coordinate findById(Long id) {
        return coordinateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coordinate not found with id: " + id));
    }

    public Coordinate createCoordinate(CreateCoordinateInput input, Long speciesId) {
        Species species = speciesService.findById(speciesId);

        Coordinate coordinate = new Coordinate();
        coordinate.setLatitude(input.latitude());
        coordinate.setLongitude(input.longitude());
        coordinate.setLocalityName(input.localityName());
        coordinate.setSpecies(species);

        return coordinateRepository.save(coordinate);
    }

    public void deleteCoordinate(Long id) {
        Coordinate coordinate = findById(id);
        coordinateRepository.delete(coordinate);
    }
}
