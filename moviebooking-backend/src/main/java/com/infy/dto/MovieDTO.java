package com.infy.dto;

import java.util.Optional;

import com.infy.entity.Movie;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class MovieDTO {
    @Column(name = "movie_id")
    private int movieId;
    @NotNull(message = "{please provide title. It should not be empty}")
    @Column(name = "title")
    private String title;
    @NotNull(message = "{Genre should not be empty}")
    @Pattern(regexp = "(Drama|Horror|Action|Romantic)", message = "{please choose correct genre}")
    @Column(name = "genre")
    private String genre;
    @NotNull(message = "{duration should not be empty}")
    @Column(name = "duration")
    private int duration;

    public MovieDTO(int movieId, String title, String genre, int duration) {
        super();
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
    }

    public MovieDTO() {
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Movie [movieId=" + movieId + ", title=" + title + ", genre=" + genre + ", duration=" + duration + "]";
    }

    public static MovieDTO entityToDTO(Optional<Movie> movie) {
        if (movie.isPresent()) {
            Movie movie2 = movie.get();
            MovieDTO dto = new MovieDTO();
            dto.setMovieId(movie2.getMovieId());
            dto.setGenre(movie2.getGenre());
            dto.setTitle(movie2.getTitle());
            dto.setDuration(movie2.getDuration());
            return dto;
        }
        return null;

    }

}
