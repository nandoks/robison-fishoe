package com.br.nandoks.robinson_fishoe.species;

import com.br.nandoks.robinson_fishoe.enums.Status;
import com.br.nandoks.robinson_fishoe.species.inputs.CreateSpeciesInput;
import com.br.nandoks.robinson_fishoe.species.inputs.UpdateSpeciesInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SpeciesServiceTest {

    @Mock
    private SpeciesRepository speciesRepository;

    @InjectMocks
    private SpeciesService speciesService;

    @Test
    void createSpecies_mapsInputAndDefaultsToDraft() {
        CreateSpeciesInput input = new CreateSpeciesInput(
                "Acanthurus chirurgus",
                "Doctorfish",
                "Acanthuridae",
                "Acanthurus",
                "Western Atlantic",
                "Olive-brown body",
                "https://image.example/doctorfish.png"
        );

        when(speciesRepository.save(org.mockito.ArgumentMatchers.any(Species.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Species result = speciesService.createSpecies(input);

        assertThat(result.getScientificName()).isEqualTo("Acanthurus chirurgus");
        assertThat(result.getCommonName()).isEqualTo("Doctorfish");
        assertThat(result.getFamily()).isEqualTo("Acanthuridae");
        assertThat(result.getGenus()).isEqualTo("Acanthurus");
        assertThat(result.getDistributionNotes()).isEqualTo("Western Atlantic");
        assertThat(result.getDescription()).isEqualTo("Olive-brown body");
        assertThat(result.getImageUrl()).isEqualTo("https://image.example/doctorfish.png");
        assertThat(result.getStatus()).isEqualTo(Status.DRAFT);
        verify(speciesRepository).save(result);
    }

    @Test
    void updateSpecies_appliesOnlyNonNullFields() {
        Species species = new Species();
        species.setId(1L);
        species.setScientificName("Acanthurus chirurgus");
        species.setCommonName("Doctorfish");
        species.setStatus(Status.PUBLISHED);

        when(speciesRepository.findById(1L)).thenReturn(Optional.of(species));
        when(speciesRepository.save(species)).thenReturn(species);

        UpdateSpeciesInput input = new UpdateSpeciesInput(
                null,
                "Doctorfish v2",
                null,
                null,
                null,
                null,
                null,
                null
        );

        Species result = speciesService.updateSpecies(1L, input);

        assertThat(result.getCommonName()).isEqualTo("Doctorfish v2");
        assertThat(result.getScientificName()).isEqualTo("Acanthurus chirurgus");
        assertThat(result.getStatus()).isEqualTo(Status.PUBLISHED);
        verify(speciesRepository).save(species);
    }

    @Test
    void updateSpecies_updatesStatusWhenProvided() {
        Species species = new Species();
        species.setId(1L);
        species.setStatus(Status.DRAFT);

        when(speciesRepository.findById(1L)).thenReturn(Optional.of(species));
        when(speciesRepository.save(species)).thenReturn(species);

        UpdateSpeciesInput input = new UpdateSpeciesInput(
                null, null, null, null, null, null, Status.PENDING, null
        );

        Species result = speciesService.updateSpecies(1L, input);

        assertThat(result.getStatus()).isEqualTo(Status.PENDING);
    }

    @Test
    void findById_returnsSpeciesWhenPresent() {
        Species species = new Species();
        species.setId(1L);

        when(speciesRepository.findById(1L)).thenReturn(Optional.of(species));

        assertThat(speciesService.findById(1L)).isSameAs(species);
    }

    @Test
    void findById_throwsWhenAbsent() {
        when(speciesRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> speciesService.findById(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("1");
    }

    @Test
    void deleteSpecies_deletesById() {
        Species species = new Species();
        species.setId(1L);

        when(speciesRepository.findById(1L)).thenReturn(Optional.of(species));

        speciesService.deleteSpecies(1L);

        verify(speciesRepository).delete(species);
    }

    @Test
    void searchSpecies_returnsRepositoryResults() {
        Species species = new Species();
        species.setScientificName("Acanthurus chirurgus");

        when(speciesRepository.search("chirurgus")).thenReturn(List.of(species));

        List<Species> result = speciesService.searchSpecies("chirurgus");

        assertThat(result).containsExactly(species);
    }
}
