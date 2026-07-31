package com.br.nandoks.robinson_fishoe.species;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.List;

@GraphQlRepository
public interface SpeciesRepository extends JpaRepository<Species, Long> {

    @Query("""
            SELECT s FROM Species s
            WHERE LOWER(s.scientificName) LIKE LOWER(CONCAT('%', :text, '%')) OR
                  LOWER(s.commonName) LIKE LOWER(CONCAT('%', :text, '%')) OR
                  LOWER(s.family) LIKE LOWER(CONCAT('%', :text, '%')) OR
                  LOWER(s.genus) LIKE LOWER(CONCAT('%', :text, '%'))
            """)
    List<Species> search(@Param("text") String text);
}
