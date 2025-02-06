package com.infy.validator;

import com.infy.exception.MovieBookingException;

import com.infy.dto.MovieDTO;

public class MovieValidator {
	public static void validateMovie(MovieDTO movies) throws MovieBookingException {
		if (movies.getDuration() < 120) {
			throw new MovieBookingException("Movie duration cannot be less than 120");
		}
		if (!movies.getGenre().matches("Drama|Horror|Action|Romantic")) {
			throw new MovieBookingException("please choose correct genre");
		}
		if (!movies.getTitle().matches("[A-Za-z]+")) {
			throw new MovieBookingException("Movie title should contain only alphabets");
		}
	}

}
