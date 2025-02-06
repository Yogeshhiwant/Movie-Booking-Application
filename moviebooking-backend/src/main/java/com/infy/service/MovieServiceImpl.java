package com.infy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.dto.MovieDTO;
import com.infy.dto.MovieScheduleDTO;
import com.infy.dto.MovieScreenDTO;
import com.infy.dto.UserDTO;
import com.infy.entity.Booking;
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
import com.infy.validator.MovieValidator;
import com.infy.validator.UserValidator;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

	private final BookingRepository bookingRepository;

	private final MovieRepository movieRepository;

	private final MovieScheduleRepository movieScheduleRepository;

	private final MovieScreenRepository movieScreenRepository;

	private final UserRepository userRepository;

	@Autowired
	public MovieServiceImpl(BookingRepository bookingRepository, MovieRepository movieRepository,
			MovieScheduleRepository movieScheduleRepository, MovieScreenRepository movieScreenRepository,
			UserRepository userRepository) {
		super();
		this.bookingRepository = bookingRepository;
		this.movieRepository = movieRepository;
		this.movieScheduleRepository = movieScheduleRepository;
		this.movieScreenRepository = movieScreenRepository;
		this.userRepository = userRepository;
	}

	// add movie method
	@Override
	public String addMovie(MovieDTO movieDTO) throws MovieBookingException {

		MovieValidator.validateMovie(movieDTO);

		Movie movie = new Movie();
		movie.setMovieId(movieDTO.getMovieId());
		movie.setTitle(movieDTO.getTitle());
		movie.setGenre(movieDTO.getGenre());
		movie.setDuration(movieDTO.getDuration());

		Movie savedMovie = movieRepository.save(movie);

		return "Movie " + savedMovie.getTitle() + " added successfully";
	}

	// create user method
	@Override
	public String createUser(UserDTO userDTO) throws MovieBookingException {

		UserValidator.validateUser(userDTO);

		User user = new User();
		user.setUserId(userDTO.getUserId());
		user.setAge(userDTO.getAge());
		user.setMobile(userDTO.getMobile());
		user.setUserName(userDTO.getUserName());

		User savedUser = userRepository.save(user);
		return "user " + savedUser.getUserName() + " created successfully";

	}

	// addMoviescreen method
	@Override
	public String addMovieScreen(MovieScreenDTO movieScreenDTO) throws MovieBookingException {

		MovieScreen movieScreen = new MovieScreen();
		movieScreen.setScreenId(movieScreenDTO.getScreenId());
		movieScreen.setScreenName(movieScreenDTO.getScreenName());
		movieScreen.setCapacity(movieScreenDTO.getCapacity());

		MovieScreen savedMovieScreen = movieScreenRepository.save(movieScreen);
		return "Movie Screen " + savedMovieScreen.getScreenName() + " are added successfully";
	}

	// createMovieSchedule method
	public String createMovieSchedule(MovieScheduleDTO movieScheduleDTO) throws MovieBookingException {
		Optional<Movie> optMovie = movieRepository.findById(movieScheduleDTO.getMovie().getMovieId());
		Optional<MovieScreen> optMovieScreen = movieScreenRepository
				.findById(movieScheduleDTO.getScreen().getScreenId());

		Movie movie = optMovie.orElseThrow(() -> new MovieBookingException("Movie details not found !"));
		MovieScreen movieScreen = optMovieScreen
				.orElseThrow(() -> new MovieBookingException("Movie screen not found !"));

		if (movie != null && movieScreen != null) {

			MovieSchedule movieShedule = new MovieSchedule();
			movieShedule.setScheduleId(movieScheduleDTO.getScheduleId());
			movieShedule.setMovie(movie);
			movieShedule.setScreen(movieScreen);
			movieShedule.setCost(movieScheduleDTO.getCost());
			movieShedule.setStartTime(movieScheduleDTO.getStartTime());

			movieScheduleRepository.save(movieShedule);
			return "Movie schedule created successfully with scheduleId " + movieScheduleDTO.getScheduleId();

		} else {
			throw new MovieBookingException("could not create movieSchedule !");
		}
	}

	// viewAllSchedule method
	@Override
	public List<MovieSchedule> viewAllSchedule(String movieId) throws MovieBookingException {

		List<MovieSchedule> movieSchedules = movieScheduleRepository.findByMovieId(movieId);
		if (movieSchedules.isEmpty()) {
			throw new MovieBookingException("no movie schedules exists with the given movieId " + movieId);
		}
		return movieSchedules;

	}

	// bookMovie method
	@Override
	public Booking bookMovie(String userId, String movieId, int noOfSeats) throws MovieBookingException {

		Optional<MovieSchedule> optSchedule = movieScheduleRepository.findByMovieId(movieId).stream().findFirst();
		Optional<User> optUser = userRepository.findById(userId);

		MovieSchedule movieSchedule = optSchedule.orElseThrow(() -> new MovieBookingException(
				"Movie schedule with the given movieId" + movieId + " does not exist !"));	
		User user = optUser.orElseThrow(
				() -> new MovieBookingException("User with the given userId " + userId + " does not exist !"));

		Booking booking = new Booking();
		booking.setUser(user);
		booking.setNoOfSeats(noOfSeats);
		booking.setMovie(movieSchedule);
		bookingRepository.save(booking);

		return booking;

	}

	// viewAllBooking method
	public List<Booking> viewAllBooking(String userId) throws MovieBookingException {
		List<Booking> bookings = bookingRepository.findByUserId(userId);
		if (bookings.isEmpty()) {
			throw new MovieBookingException("No bookings exists with the given userId " + userId);
		} else {
			return bookings;
		}
	}
}
