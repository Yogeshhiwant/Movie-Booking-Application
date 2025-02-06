package com.infy.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.infy.dto.BookingDTO;
import com.infy.dto.MovieDTO;
import com.infy.dto.MovieScheduleDTO;
import com.infy.dto.MovieScreenDTO;
import com.infy.dto.UserDTO;
import com.infy.entity.Booking;
import com.infy.entity.MovieSchedule;
import com.infy.entity.MovieScreen;
import com.infy.exception.MovieBookingException;
import com.infy.service.MovieService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Validated
public class MovieBookingController {
	
	private final MovieService movieService;

	@Autowired
	public MovieBookingController(MovieService movieService) {
		this.movieService = movieService;
	}

	@PostMapping("/movie")
	public ResponseEntity<String> addMovieHandler(@RequestBody @Valid MovieDTO dto) throws MovieBookingException {
		String successMessage = movieService.addMovie(dto);
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);

	}

	@PostMapping("/user")
	public ResponseEntity<String> createUserHandler(@RequestBody UserDTO dto) throws MovieBookingException {
		String successMessage = movieService.createUser(dto);
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);

	}

	@PostMapping("/moviescreen")
	public ResponseEntity<String> addMovieScreenHandler(@RequestBody MovieScreenDTO dto) throws MovieBookingException {
		String successMessage = movieService.addMovieScreen(dto);
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);

	}

	@PostMapping("/movieSchedule")
	public ResponseEntity<String> createMovieScheduleHandler(@RequestBody MovieScheduleDTO dto)
			throws MovieBookingException {
		String successMessage = movieService.createMovieSchedule(dto);
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);

	}

	@GetMapping("/movieSchedule/{movieId}")
	public ResponseEntity<List<MovieSchedule>> getAllScheduleHandler(@PathVariable("movieId") String movieId) throws MovieBookingException {
		List<MovieSchedule> viewAllSchedule = movieService.viewAllSchedule(movieId);
		return new ResponseEntity<>(viewAllSchedule, HttpStatus.OK);

	}

	@PostMapping("/bookMovie")
	public ResponseEntity<Booking> bookMovieHandler(@RequestParam String userId, @RequestParam String movieId,
			@RequestParam int noOfSeats) throws MovieBookingException {
		Booking bookMovie = movieService.bookMovie(userId, movieId, noOfSeats);
		return new ResponseEntity<>(bookMovie, HttpStatus.CREATED);

	}

	@GetMapping("/booking/{userId}")
	public ResponseEntity<List<Booking>> viewAllBookingHandler(@PathVariable("userId") String userId) throws MovieBookingException {
		List<Booking> viewAllBooking = movieService.viewAllBooking(userId);
		return new ResponseEntity<>(viewAllBooking, HttpStatus.OK);

	}
}
