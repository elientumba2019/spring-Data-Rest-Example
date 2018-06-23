package com.example.ec.explorecali.services;

import com.example.ec.explorecali.domain.Tour;

import java.util.List;

public interface TourServices {

    void createTour(Tour tour);
    long total();

    List<Tour> getTours();
}
