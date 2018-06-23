package com.example.ec.explorecali.repo;

import com.example.ec.explorecali.domain.TourPackage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

public interface TourPackRepo extends PagingAndSortingRepository<TourPackage , String> {

    TourPackage findByName(@Param("name") String name);


    @RestResource(exported = false)
    @Override
    void delete(TourPackage tourPackage);

    @RestResource(exported = false)
    @Override
    <S extends TourPackage> S save(S s);

    @RestResource(exported = false)
    @Override
    <S extends TourPackage> Iterable<S> saveAll(Iterable<S> iterable);

    @RestResource(exported = false)
    @Override
    void deleteById(String s);

    @RestResource(exported = false)
    @Override
    void deleteAll(Iterable<? extends TourPackage> iterable);

    @RestResource(exported = false)
    @Override
    void deleteAll();
}
