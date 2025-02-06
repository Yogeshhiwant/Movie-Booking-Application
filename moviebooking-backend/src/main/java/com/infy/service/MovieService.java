package com.infy.service;

import java.util.List;
import com.infy.exception.MovieBookingException;
import com.infy.dto.MovieDTO;
import com.infy.dto.MovieScheduleDTO;
import com.infy.dto.MovieScreenDTO;
import com.infy.dto.UserDTO;
import com.infy.entity.Booking;
import com.infy.entity.MovieSchedule;

public interface MovieService {

	public String addMovie(MovieDTO movies) throws MovieBookingException;

	public String createUser(UserDTO user) throws MovieBookingException;

	public String addMovieScreen(MovieScreenDTO movieScreenDto) throws MovieBookingException;

	public String createMovieSchedule(MovieScheduleDTO movieSchedule) throws MovieBookingException;

	public List<MovieSchedule> viewAllSchedule(String movieId) throws MovieBookingException;

	public Booking bookMovie(String userId, String movieId, int noOfSeats) throws MovieBookingException;

	public List<Booking> viewAllBooking(String userId) throws MovieBookingException;

}
