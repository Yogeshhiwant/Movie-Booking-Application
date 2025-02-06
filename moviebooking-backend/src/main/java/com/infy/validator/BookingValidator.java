package com.infy.validator;

import com.infy.exception.MovieBookingException;
import com.infy.dto.BookingDTO;

public class BookingValidator {
	public static void validateBooking(BookingDTO booking) throws MovieBookingException {
		if (booking != null && booking.getNoOfSeats() < 1) {
			throw new MovieBookingException("No seats are available");
		}

	}

}
