package com.example.ec.explorecali.services;

import com.example.ec.explorecali.domain.TourPackage;

public interface TourPackService {

    void createTourPackage(TourPackage tourPackage);

    Iterable<TourPackage> lookUp();
}
