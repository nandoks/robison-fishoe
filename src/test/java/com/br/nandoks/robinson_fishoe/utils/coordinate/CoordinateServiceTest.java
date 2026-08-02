package com.br.nandoks.robinson_fishoe.utils.coordinate;

import com.br.nandoks.robinson_fishoe.species.Species;
import com.br.nandoks.robinson_fishoe.species.SpeciesService;
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
class CoordinateServiceTest {

    @Mock
    private CoordinateRepository coordinateRepository;

    @Mock
    private SpeciesService speciesService;

    @InjectMocks
    private CoordinateService coordinateService;

    @Test
    void createCoordinate_linksToSpeciesAndSaves() {
        Species species = new Species();
        species.setId(1L);
        CreateCoordinateInput input = new CreateCoordinateInput(18.2208, -66.5901);

        when(speciesService.findById(1L)).thenReturn(species);
        when(coordinateRepository.save(org.mockito.ArgumentMatchers.any(Coordinate.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Coordinate result = coordinateService.createCoordinate(input, 1L);

        assertThat(result.getLatitude()).isEqualTo("18.2208");
        assertThat(result.getLongitude()).isEqualTo("-66.5901");
        assertThat(result.getSpecies()).isSameAs(species);
        verify(coordinateRepository).save(result);
    }

    @Test
    void createCoordinate_propagatesWhenSpeciesNotFound() {
        CreateCoordinateInput input = new CreateCoordinateInput(18.2208, -66.5901);

        when(speciesService.findById(99L))
                .thenThrow(new RuntimeException("Species not found with id: 99"));

        assertThatThrownBy(() -> coordinateService.createCoordinate(input, 99L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("99");
    }

    @Test
    void findById_returnsCoordinateWhenPresent() {
        Coordinate coordinate = new Coordinate();
        coordinate.setId(1L);

        when(coordinateRepository.findById(1L)).thenReturn(Optional.of(coordinate));

        assertThat(coordinateService.findById(1L)).isSameAs(coordinate);
    }

    @Test
    void findById_throwsWhenAbsent() {
        when(coordinateRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> coordinateService.findById(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("1");
    }

    @Test
    void findBySpeciesId_returnsRepositoryResults() {
        Coordinate coordinate = new Coordinate();
        coordinate.setLatitude("18.2208");

        when(coordinateRepository.findBySpeciesId(1L)).thenReturn(List.of(coordinate));

        assertThat(coordinateService.findBySpeciesId(1L)).containsExactly(coordinate);
    }

    @Test
    void deleteCoordinate_deletesById() {
        Coordinate coordinate = new Coordinate();
        coordinate.setId(1L);

        when(coordinateRepository.findById(1L)).thenReturn(Optional.of(coordinate));

        coordinateService.deleteCoordinate(1L);

        verify(coordinateRepository).delete(coordinate);
    }
}
