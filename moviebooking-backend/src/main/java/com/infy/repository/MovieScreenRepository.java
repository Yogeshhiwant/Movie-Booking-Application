package com.infy.repository;

import org.springframework.data.repository.CrudRepository;

import com.infy.entity.MovieScreen;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieScreenRepository extends CrudRepository<MovieScreen, Integer>{
   
}
