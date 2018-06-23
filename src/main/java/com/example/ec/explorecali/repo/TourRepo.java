package com.example.ec.explorecali.repo;

import com.example.ec.explorecali.domain.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;



@RepositoryRestResource(collectionResourceRel = "packages" , path = "packages")
public interface TourRepo extends PagingAndSortingRepository<Tour, Integer> {

    List<Tour> findAll();
    List<Tour> findByTourPackageCode(@Param("code") String code);
    Page<Tour> findByTourPackageCode(@Param("code")String code, Pageable pageable);




    @RestResource(exported = false)
    @Override
    <S extends Tour> S save(S s);

    @RestResource(exported = false)
    @Override
    void deleteAll(Iterable<? extends Tour> iterable);

    @RestResource(exported = false)
    @Override
    void deleteAll();

    @RestResource(exported = false)
    @Override
    void deleteById(Integer integer);

    @RestResource(exported = false)
    @Override
    void delete(Tour tour);

    @RestResource(exported = false)
    @Override
    <S extends Tour> Iterable<S> saveAll(Iterable<S> iterable);
}
