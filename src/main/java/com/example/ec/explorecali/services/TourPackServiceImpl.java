package com.example.ec.explorecali.services;

import com.example.ec.explorecali.domain.TourPackage;
import com.example.ec.explorecali.repo.TourPackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TourPackServiceImpl implements TourPackService {


    @Autowired
    private TourPackRepo tourPackRepo;

    @Override
    public void createTourPackage(TourPackage tourPackage) {
        this.tourPackRepo.save(tourPackage);
    }


    @Override
    public Iterable<TourPackage> lookUp() {
        return this.tourPackRepo.findAll();
    }
}
