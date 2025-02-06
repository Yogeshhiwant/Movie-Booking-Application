package com.infy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infy.entity.MovieSchedule;

@Repository
public interface MovieScheduleRepository extends CrudRepository<MovieSchedule, String> {
    @Query("select ms from MovieSchedule ms where ms.movie.movieId=:movieId")
    List<MovieSchedule> findByMovieId(@Param("movieId") String movieId);
}
