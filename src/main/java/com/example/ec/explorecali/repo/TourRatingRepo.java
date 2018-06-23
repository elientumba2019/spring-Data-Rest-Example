package com.example.ec.explorecali.repo;

import com.example.ec.explorecali.domain.TourRating;
import com.example.ec.explorecali.domain.TourRatingPk;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource(exported = false)
public interface TourRatingRepo extends CrudRepository<TourRating , TourRatingPk> {


    List<TourRating> findByPkTourId(Integer tourId);
    TourRating findByPkTourAndPkCustomerId(Integer tourId , Integer customerID);
}
