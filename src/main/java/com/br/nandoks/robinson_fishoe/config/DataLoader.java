package com.br.nandoks.robinson_fishoe.config;


import com.br.nandoks.robinson_fishoe.enums.Status;
import com.br.nandoks.robinson_fishoe.species.Species;
import com.br.nandoks.robinson_fishoe.species.SpeciesRepository;
import com.br.nandoks.robinson_fishoe.utils.coordinate.Coordinate;
import com.br.nandoks.robinson_fishoe.utils.coordinate.CoordinateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private SpeciesRepository speciesRepository;

    @Autowired
    private CoordinateRepository coordinateRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Only load if the database is empty
        if (speciesRepository.count() > 0) {
            System.out.println("ℹ️ Database already contains data. Skipping fixtures.");
            return;
        }

        System.out.println("🔄 Loading fixtures into database...");

        // ----- Species 1: Doctorfish -----
        Species species1 = new Species();
        species1.setScientificName("Acanthurus chirurgus");
        species1.setCommonName("Doctorfish");
        species1.setFamily("Acanthuridae");
        species1.setGenus("Acanthurus");
        species1.setDistributionNotes("Western Atlantic: Bermuda, Florida, Gulf of Mexico, Caribbean Sea, south to " +
                "Brazil.");
        species1.setDescription("Body oval and strongly compressed. Adults are olive-brown with numerous wavy blue " +
                "lines on the head and body.");
        species1.setStatus(Status.PUBLISHED);
        species1.setImageUrl("https://dummyimage.com/180x180/c0392b/ffffff&text=Acanthurus_chirurgus");
        speciesRepository.save(species1);

        List<Coordinate> coords1 = Arrays.asList(
                createCoordinate(18.2208, -66.5901, species1),
                createCoordinate(18.2315, -66.6103, species1),
                createCoordinate(18.2450, -66.5850, species1)
        );
        coordinateRepository.saveAll(coords1);

        // ----- Species 2: Queen Angelfish -----
        Species species2 = new Species();
        species2.setScientificName("Holacanthus ciliaris");
        species2.setCommonName("Queen Angelfish");
        species2.setFamily("Pomacanthidae");
        species2.setGenus("Holacanthus");
        species2.setDistributionNotes("Western Atlantic: Florida to Brazil, including Gulf of Mexico and Caribbean " +
                "Sea.");
        species2.setDescription("Brightly colored with a blue body, yellow tail, and a distinctive crown-like spot on" +
                " the forehead.");
        species2.setStatus(Status.PUBLISHED);
        species2.setImageUrl("https://dummyimage.com/180x180/c0392b/ffffff&text=Holacanthus_ciliaris");
        speciesRepository.save(species2);

        List<Coordinate> coords2 = Arrays.asList(
                createCoordinate(19.2208, -67.5901,species2),
                createCoordinate(20.2315, -68.6103, species2),
                createCoordinate(21.2450, -69.5850,species2)
        );
        coordinateRepository.saveAll(coords2);

        // ----- Species 3: Stoplight Parrotfish (DRAFT) -----
        Species species3 = new Species();
        species3.setScientificName("Sparisoma viride");
        species3.setCommonName("Stoplight Parrotfish");
        species3.setFamily("Scaridae");
        species3.setGenus("Sparisoma");
        species3.setDistributionNotes("Western Atlantic: Bermuda and Florida to Brazil.");
        species3.setDescription("Greenish-brown with a distinctive yellow spot on the upper tail base. Males have a " +
                "bright green head.");
        species3.setStatus(Status.DRAFT);
        species3.setImageUrl("https://dummyimage.com/180x180/c0392b/ffffff&text=Sparisoma_viride");
        speciesRepository.save(species3);

        List<Coordinate> coords3 = Arrays.asList(
                createCoordinate(18.2000, -65.5000, species3),
                createCoordinate(18.3000, -64.5000, species3)
        );
        coordinateRepository.saveAll(coords3);

        // ----- Species 4: Sharpnose Puffer -----
        Species species4 = new Species();
        species4.setScientificName("Canthigaster rostrata");
        species4.setCommonName("Sharpnose Puffer");
        species4.setFamily("Tetraodontidae");
        species4.setGenus("Canthigaster");
        species4.setDistributionNotes("Caribbean Sea and Gulf of Mexico.");
        species4.setDescription("Small pufferfish with a pointed snout. Brownish body with blue spots and lines.");
        species4.setStatus(Status.PUBLISHED);
        species4.setImageUrl("https://dummyimage.com/180x180/c0392b/ffffff&text=Canthigaster_rostrata");
        speciesRepository.save(species4);

        List<Coordinate> coords4 = Arrays.asList(
                createCoordinate(19.5000, -70.5000, species4),
                createCoordinate(20.0000, -71.5000, species4),
                createCoordinate(20.5000, -72.5000, species4),
                createCoordinate(21.0000, -73.5000, species4)
        );
        coordinateRepository.saveAll(coords4);

        // ----- Species 5: Nassau Grouper (DRAFT) -----
        Species species5 = new Species();
        species5.setScientificName("Epinephelus striatus");
        species5.setCommonName("Nassau Grouper");
        species5.setFamily("Serranidae");
        species5.setGenus("Epinephelus");
        species5.setDistributionNotes("Western Atlantic: Bermuda and Florida to Brazil, including Caribbean Sea.");
        species5.setDescription("Large fish with a robust body. Light brown with darker bars and stripes. Can change " +
                "color dramatically.");
        species5.setStatus(Status.DRAFT);
        species5.setImageUrl("https://dummyimage.com/180x180/c0392b/ffffff&text=Epinephelus_striatus");
        speciesRepository.save(species5);

        List<Coordinate> coords5 = Arrays.asList(
                createCoordinate(18.2000, -76.0000, species5),
                createCoordinate(19.0000, -77.0000, species5)
        );
        coordinateRepository.saveAll(coords5);

        System.out.println("✅ Fixtures loaded: " + speciesRepository.count() + " species, " + coordinateRepository.count() + " coordinates");
        System.out.println("📊 Species loaded:");
        speciesRepository.findAll().forEach(s ->
                System.out.println("   - " + s.getScientificName() + " (" + s.getStatus() + ")")
        );
    }

    private Coordinate createCoordinate(Double lat, Double lon, Species species) {
        Coordinate coord = new Coordinate();
        coord.setLatitude(String.valueOf(lat));
        coord.setLongitude(String.valueOf(lon));;
        coord.setSpecies(species);
        return coord;
    }
}