package com.example.ec.explorecali.services;

import com.example.ec.explorecali.domain.Tour;
import com.example.ec.explorecali.repo.TourRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TourServicesImpl implements TourServices{


    @Autowired
    private TourRepo tourRepo;

    @Override
    public void createTour(Tour tour) {
        this.tourRepo.save(tour);
    }



    @Override
    public long total() {
        return this.tourRepo.count();
    }

    @Override
    public List<Tour> getTours() {
        return this.tourRepo.findAll();
    }
}
