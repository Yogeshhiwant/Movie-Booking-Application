package com.infy.tests;

import static org.mockito.ArgumentMatchers.any;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.infy.dto.MovieDTO;
import com.infy.dto.MovieScheduleDTO;
import com.infy.dto.MovieScreenDTO;
import com.infy.dto.UserDTO;
import com.infy.entity.Movie;
import com.infy.entity.MovieSchedule;
import com.infy.entity.MovieScreen;
import com.infy.entity.User;
import com.infy.exception.MovieBookingException;
import com.infy.repository.BookingRepository;
import com.infy.repository.MovieRepository;
import com.infy.repository.MovieScheduleRepository;
import com.infy.repository.MovieScreenRepository;
import com.infy.repository.UserRepository;
import com.infy.service.MovieServiceImpl;

@SpringBootTest
class MovieBookingSpringProjectApplicationTests {
	@Mock
	private BookingRepository bookingRepo;
	@Mock
	private MovieRepository movieRepo;
	@Mock
	private MovieScheduleRepository movieScheduleRepo;
	@Mock
	private MovieScreenRepository movieScreenRepo;
	@Mock
	private UserRepository userRepo;

	@InjectMocks
	private MovieServiceImpl movieService;

	@Test
	void contextLoads() {
	}

	Movie movie = new Movie("1", "EnglishWinglish", "Action", 130);
	Movie movie1 = new Movie("2", "Godfather", "Drama", 100);
	Movie movie2 = new Movie("2", "Krish3", "Thriller", 140);
	Movie movie3 = new Movie("2", "YJHD", "Horror", 150);

	User user = new User("UserID 1", "Ranveer", 26, 1234554321l);
	User user1 = new User("UserID 2", "Hrithik", 26, 9899765443l);
	User user2 = new User("UserID 3", "Shahid", 16, 9542573115l);

	MovieScreen movieScreen = new MovieScreen(1, "100mm Screen", 80);
	MovieScreen movieScreen1 = new MovieScreen(2, "90 mm Screen", 90);

	MovieSchedule movieSchedule = new MovieSchedule("S1", movie, movieScreen, LocalDateTime.now(), 180.0);
	MovieSchedule movieSchedule1 = new MovieSchedule("S2", movie1, movieScreen1, LocalDateTime.now(), 150.0);

	MovieDTO moviedto = new MovieDTO("1", "EnglishWinglish", "Action", 130);
	MovieDTO moviedto1 = new MovieDTO("2", "Godfather", "Drama", 100);
	MovieDTO moviedto2 = new MovieDTO("2", "Krish3", "Thriller", 140);
	MovieDTO moviedto3 = new MovieDTO("2", "YJHD", "Horror", 150);

	UserDTO userdto = new UserDTO("UserID 1", "Ranveer", 26, 1234554321l);
	UserDTO userdto1 = new UserDTO("UserID 2", "Hrithik", 26, 9899765443l);
	UserDTO userdto2 = new UserDTO("UserID 3", "Shahid", 16, 9542573115l);

	MovieScreenDTO movieScreendto = new MovieScreenDTO(1, "100mm Screen", 80);
	MovieScreenDTO movieScreendto1 = new MovieScreenDTO(2, "90 mm Screen", 90);

	MovieScheduleDTO movieScheduledto = new MovieScheduleDTO("S1", moviedto, movieScreendto, LocalDateTime.now(),
			180.0);
	MovieScheduleDTO movieScheduledto1 = new MovieScheduleDTO("S2", moviedto1, movieScreendto1, LocalDateTime.now(),
			150.0);

	@Test
	public void addMovieValidTest() throws MovieBookingException {
		Mockito.when(movieRepo.save(any(Movie.class))).thenReturn(movie);
		String addMovie = movieService.addMovie(moviedto);
		Assertions.assertEquals("Movie added successfully", addMovie);

	}

	@Test
	public void addMovieInvalidTest() {
		Mockito.when(movieRepo.save(any(Movie.class))).thenReturn(movie1);
		MovieBookingException exception = Assertions.assertThrows(MovieBookingException.class,
				() -> movieService.addMovie(moviedto1));
		Assertions.assertEquals("Movie duration cannot be less than 120", exception.getMessage());
	}

	@Test
	public void addMovieInvalidTest2() {
		Mockito.when(movieRepo.save(any(Movie.class))).thenReturn(movie2);
		MovieBookingException exception = Assertions.assertThrows(MovieBookingException.class,
				() -> movieService.addMovie(moviedto2));
		Assertions.assertEquals("please choose correct genre", exception.getMessage());
	}

	@Test
	public void addMovieInvalidTest3() {
		Mockito.when(movieRepo.save(any(Movie.class))).thenReturn(movie3);
		MovieBookingException exception = Assertions.assertThrows(MovieBookingException.class,
				() -> movieService.addMovie(moviedto3));
		Assertions.assertEquals("Movie title should contain only alphabets", exception.getMessage());
	}

	@Test
	public void createUserValidTest() throws MovieBookingException {
		Mockito.when(userRepo.save(any(User.class))).thenReturn(user);
		String expected = movieService.createUser(userdto);
		Assertions.assertEquals(expected, "user created successfully");
	}

	@Test
	public void createUserInValidTest() {
		Mockito.when(userRepo.save(any(User.class))).thenReturn(user1);
		MovieBookingException exception = Assertions.assertThrows(MovieBookingException.class,
				() -> movieService.createUser(userdto1));
		Assertions.assertEquals(
				"User mobile number should be a 10 digit number all the ten digits should not be the same",
				exception.getMessage());
	}

	@Test
	public void createUserInValidTest2() {
		Mockito.when(userRepo.save(any(User.class))).thenReturn(user2);
		MovieBookingException exception = Assertions.assertThrows(MovieBookingException.class,
				() -> movieService.createUser(userdto2));
		Assertions.assertEquals("Age must be greater than 18", exception.getMessage());
	}

	@Test
	public void addMovieScreenTest() throws MovieBookingException {
		Mockito.when(movieScreenRepo.save(any(MovieScreen.class))).thenReturn(movieScreen);
		String expected = movieService.addMovieScreen(movieScreendto);
		Assertions.assertEquals(expected, "Movie Screens are added successfully");
	}

	@Test
	public void viewAllSheduleTest() throws MovieBookingException {

		String expected = movieService.createMovieSchedule(movieScheduledto);
		Assertions.assertEquals(expected, "Movie shedule created successfully");

		List<MovieSchedule> movieSchedules = new ArrayList<>();
		movieSchedules.add(movieSchedule);
		Mockito.when(movieScheduleRepo.findByMovieId("1")).thenReturn(movieSchedules);

		List<MovieSchedule> viewAllSchedule = movieService
				.viewAllSchedule(movieSchedules.get(0).getMovie().getMovieId());
		Assertions.assertEquals("EnglishWinglish", viewAllSchedule.get(0).getMovie().getTitle());
	}

}
