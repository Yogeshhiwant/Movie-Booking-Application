package com.infy.validator;

import com.infy.exception.MovieBookingException;

import com.infy.dto.UserDTO;

public class UserValidator {
	public static void validateUser(UserDTO user) throws MovieBookingException {
		if (user.getUserName().split("\\s+").length < 1) {
			throw new MovieBookingException("provide valid username");
		}
		if (Boolean.FALSE.equals(user.getAge() > 18)) {
			throw new MovieBookingException("Age must be greater than 18");
		}
		String mobileNum = String.valueOf(user.getMobile());
		if (mobileNum.length() != 10 || mobileNum.chars().distinct().count() == 1) {
			throw new MovieBookingException(
					"User mobile number should be a 10 digit number all the ten digits should not be the same");
		}
	}

}