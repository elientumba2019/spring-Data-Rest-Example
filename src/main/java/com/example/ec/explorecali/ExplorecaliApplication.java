package com.example.ec.explorecali;

import com.example.ec.explorecali.domain.Difficulty;
import com.example.ec.explorecali.domain.Region;
import com.example.ec.explorecali.domain.Tour;
import com.example.ec.explorecali.domain.TourPackage;
import com.example.ec.explorecali.services.TourPackService;
import com.example.ec.explorecali.services.TourServices;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class ExplorecaliApplication implements CommandLineRunner {


    //TODO 13

    @Autowired
    private TourPackService tourPackService;

    @Autowired
    private TourServices tourServices;

    private Logger logger = LoggerFactory.getLogger(getClass().getName());




    public static void main(String[] args) {

        SpringApplication.run(ExplorecaliApplication.class, args);
    }



    @Override
    public void run(String... args) throws Exception {
        createToursPackages();


        tourPackService.lookUp()
            .forEach(pack -> logger.info(pack.toString()));


        this.tourServices.createTour(new Tour(
                "My tour",
                "lkdjflksjflkdsjflkdsjf",
                "ldsjflsdkjfldskjfldskjflkdsjf",
                21212,
                "3 years",
                "lkdsjflkdsjfk",
                "elie",
                new TourPackage("BC", "Big coco"),
                Difficulty.valueOf("Medium"),
                Region.findLabel("Central cost")
        ));


        System.out.println(tourServices.getTours());
    }


    private void createToursPackages() throws IOException {
        tourPackService.createTourPackage(new TourPackage("BC", "Backpack Cal"));
        tourPackService.createTourPackage(new TourPackage("CC" , "California Cals"));
        tourPackService.createTourPackage(new TourPackage("CH" , "California Hot Spring"));
        tourPackService.createTourPackage(new TourPackage("CY" , "Cycle Califronia"));
        tourPackService.createTourPackage(new TourPackage("DS" , "From Desert To Sea"));
        tourPackService.createTourPackage(new TourPackage("KC" , "Kids California"));
        tourPackService.createTourPackage(new TourPackage("NW" , "Nature Watch"));
        tourPackService.createTourPackage(new TourPackage("SC" , "SnowBoard Cali"));
        tourPackService.createTourPackage(new TourPackage("TC" , "Taste of California"));



           this.tourServices.createTour(new Tour(
                   "My tour",
                   "lkdjflksjflkdsjflkdsjf",
                   "ldsjflsdkjfldskjfldskjflkdsjf",
                   21212,
                   "3 years",
                   "lkdsjflkdsjfk",
                   "elie",
                   new TourPackage("BC", "Big coco"),
                   Difficulty.valueOf("Medium"),
                   Region.findLabel("Central cost")
           ));


    }



    static class TourFromFile{

        private String packageType, title, description, blurb,
            price, length, bullets, keywords, difficulty, region;


        static List<TourFromFile> importData() throws IOException{

            ObjectMapper objectMapper = new ObjectMapper();
            return  objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                    .readValue(TourFromFile.class.getResourceAsStream("data.json"), new TypeReference<List<TourFromFile>>() {});
        }
    }
}
