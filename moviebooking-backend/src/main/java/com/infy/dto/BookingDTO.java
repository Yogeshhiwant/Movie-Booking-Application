package com.infy.dto;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

public class BookingDTO {
	@Column(name = "booking_id")
	private int bookingId;
	@ManyToOne
	private MovieScheduleDTO movie;
	@ManyToOne
	private UserDTO user;
	@NotNull(message = "{Number of seats should not be empty}")
	private int noOfSeats;

	public BookingDTO(int bookingId, MovieScheduleDTO movie, UserDTO user, int noOfSeats) {
		super();
		this.bookingId = bookingId;
		this.movie = movie;
		this.user = user;
		this.noOfSeats = noOfSeats;
	}

	public BookingDTO() {
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public MovieScheduleDTO getMovie() {
		return movie;
	}

	public void setMovie(MovieScheduleDTO movie) {
		this.movie = movie;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public int getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", movie=" + movie + ", user=" + user + ", noOfSeats=" + noOfSeats
				+ "]";
	}

}
