package com.example.ec.explorecali.controllers;


import com.example.ec.explorecali.domain.Tour;
import com.example.ec.explorecali.domain.TourRating;
import com.example.ec.explorecali.domain.TourRatingPk;
import com.example.ec.explorecali.repo.TourRatingRepo;
import com.example.ec.explorecali.repo.TourRepo;
import com.example.ec.explorecali.web.RatingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.AbstractMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

@RestController
@RequestMapping(path = "/tours/{tourId}/ratings")
public class TourRatingController {


    @Autowired
    private TourRatingRepo ratingRepo;

    @Autowired
    private TourRepo tourRepo;


    public TourRatingController() {
    }



    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createRating(@PathVariable("tourId") int id , @RequestBody @Validated RatingDto ratingDto){
        Tour tour = verifyTour(id);
        TourRatingPk pk = new TourRatingPk(tour, ratingDto.getCustomerId());
        ratingRepo.save(new TourRating(pk, ratingDto.getScore() , ratingDto.getComment()));
    }




    @GetMapping()
    public List<RatingDto> getAllRatingsForTour(@PathVariable(value = "tourId") int id){
        verifyTour(id);
        return ratingRepo.findByPkTourId(id )
                .stream()
                .map(rat -> toDto(rat))
                .collect(Collectors.toList());
    }




    @GetMapping("/average ")
    public AbstractMap.SimpleEntry<String, Double> getAverage(@PathVariable(value = "tourId") int id) {
        verifyTour(id);
        List<TourRating> tourRatings = ratingRepo.findByPkTourId(id);
        OptionalDouble average = tourRatings.stream().mapToDouble(TourRating::getScore).average();
        return new AbstractMap.SimpleEntry<String, Double>("average", average.isPresent()?average.getAsDouble():null);
    }





    private Tour verifyTour(int id) throws NoSuchElementException{
        Tour tour = tourRepo.findById(id).get();
        if(tour == null){
            throw new NoSuchElementException("Tour does not exist : " + id);
        }
        return tour;
    }




    private RatingDto toDto(TourRating rating){
        return new RatingDto(rating.getScore(), rating.getComment(), rating.getPk().getCustomerId());
    }




    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex){
        return ex.getMessage();
    }
}
