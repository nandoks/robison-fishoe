package com.br.nandoks.robinson_fishoe.species;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface SpeciesRepository extends JpaRepository<Species, Long> {
}
