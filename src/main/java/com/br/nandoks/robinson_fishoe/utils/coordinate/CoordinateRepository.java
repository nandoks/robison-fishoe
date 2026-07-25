package com.br.nandoks.robinson_fishoe.utils.coordinate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.List;

@GraphQlRepository
public interface CoordinateRepository extends JpaRepository<Coordinate, Long> {
    List<Coordinate> findBySpeciesId(Long speciesId);
}
