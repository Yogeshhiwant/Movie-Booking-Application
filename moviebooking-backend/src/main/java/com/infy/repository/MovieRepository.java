package com.infy.repository;


import org.springframework.data.repository.CrudRepository;
import com.infy.entity.Movie;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer>{
	     // Optional<Movie> findById(String movieId);
}
