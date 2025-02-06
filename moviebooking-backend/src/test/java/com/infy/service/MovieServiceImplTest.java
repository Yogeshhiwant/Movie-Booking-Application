package com.infy.service;

import com.infy.dto.MovieDTO;
import com.infy.dto.MovieScheduleDTO;
import com.infy.dto.MovieScreenDTO;
import com.infy.dto.UserDTO;
import com.infy.entity.*;
import com.infy.exception.MovieBookingException;
import com.infy.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.OptimisticLockingFailureException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceImplTest {

    @Mock
    private BookingRepository mockBookingRepository;
    @Mock
    private MovieRepository mockMovieRepository;
    @Mock
    private MovieScheduleRepository mockMovieScheduleRepository;
    @Mock
    private MovieScreenRepository mockMovieScreenRepository;
    @Mock
    private UserRepository mockUserRepository;

    private MovieServiceImpl movieServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        movieServiceImplUnderTest = new MovieServiceImpl(mockBookingRepository, mockMovieRepository,
                mockMovieScheduleRepository, mockMovieScreenRepository, mockUserRepository);
    }

    @Test
    void testAddMovie() throws Exception {
        // Setup
        final MovieDTO movieDTO = new MovieDTO("movieId", "title", "genre", 130);

        // Configure MovieRepository.save(...).
        final Movie movie = new Movie("movieId", "title", "genre", 130);
        when(mockMovieRepository.save(any(Movie.class))).thenReturn(movie);

        // Run the test
        final String result = movieServiceImplUnderTest.addMovie(movieDTO);

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testAddMovie_MovieRepositoryThrowsOptimisticLockingFailureException() {
        // Setup
        final MovieDTO movieDTO = new MovieDTO("movieId", "title", "genre", 130);
        when(mockMovieRepository.save(any(Movie.class))).thenThrow(OptimisticLockingFailureException.class);

        // Run the test
        assertThatThrownBy(() -> movieServiceImplUnderTest.addMovie(movieDTO))
                .isInstanceOf(OptimisticLockingFailureException.class);
    }

    @Test
    void testCreateUser() throws Exception {
        // Setup
        final UserDTO userDTO = new UserDTO("userId", "userName", 20, 9139775526L);
        when(mockUserRepository.save(any(User.class))).thenReturn(new User("userId", "userName", 20, 9139775526L));

        // Run the test
        final String result = movieServiceImplUnderTest.createUser(userDTO);

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testCreateUser_UserRepositoryThrowsOptimisticLockingFailureException() {
        // Setup
        final UserDTO userDTO = new UserDTO("userId", "userName", 20, 0L);
        when(mockUserRepository.save(any(User.class))).thenThrow(OptimisticLockingFailureException.class);

        // Run the test
        assertThatThrownBy(() -> movieServiceImplUnderTest.createUser(userDTO))
                .isInstanceOf(OptimisticLockingFailureException.class);
    }

    @Test
    void testAddMovieScreen() throws Exception {
        // Setup
        final MovieScreenDTO movieScreenDTO = new MovieScreenDTO(0, "screenName", 0);
        when(mockMovieScreenRepository.save(any(MovieScreen.class))).thenReturn(new MovieScreen(0, "screenName", 0));

        // Run the test
        final String result = movieServiceImplUnderTest.addMovieScreen(movieScreenDTO);

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testAddMovieScreen_MovieScreenRepositoryThrowsOptimisticLockingFailureException() {
        // Setup
        final MovieScreenDTO movieScreenDTO = new MovieScreenDTO(0, "screenName", 0);
        when(mockMovieScreenRepository.save(any(MovieScreen.class))).thenThrow(OptimisticLockingFailureException.class);

        // Run the test
        assertThatThrownBy(() -> movieServiceImplUnderTest.addMovieScreen(movieScreenDTO))
                .isInstanceOf(OptimisticLockingFailureException.class);
    }

    @Test
    void testCreateMovieSchedule() throws Exception {
        // Setup
        final MovieDTO movieDTO = new MovieDTO();
        movieDTO.setMovieId("movieId");
        movieDTO.setTitle("title");
        movieDTO.setGenre("genre");
        movieDTO.setDuration(130);
        final MovieScreenDTO movieScreenDTO = new MovieScreenDTO();
        movieScreenDTO.setScreenId(0);
        movieScreenDTO.setScreenName("screenName");
        movieScreenDTO.setCapacity(0);
        final MovieScheduleDTO movieScheduleDTO = new MovieScheduleDTO("scheduleId", movieDTO, movieScreenDTO,
                LocalDateTime.of(2020, 1, 1, 0, 0, 0), 0.0);

        // Configure MovieRepository.findById(...).
        final Optional<Movie> movie = Optional.of(new Movie("movieId", "title", "genre", 130));
        when(mockMovieRepository.findById("movieId")).thenReturn(movie);

        // Configure MovieScreenRepository.findById(...).
        final Optional<MovieScreen> movieScreen = Optional.of(new MovieScreen(0, "screenName", 0));
        when(mockMovieScreenRepository.findById(0)).thenReturn(movieScreen);

        // Run the test
        final String result = movieServiceImplUnderTest.createMovieSchedule(movieScheduleDTO);

        // Verify the results
        assertThat(result).isEqualTo("result");
        verify(mockMovieScheduleRepository).save(any(MovieSchedule.class));
    }

    @Test
    void testCreateMovieSchedule_MovieRepositoryReturnsAbsent() {
        // Setup
        final MovieDTO movieDTO = new MovieDTO();
        movieDTO.setMovieId("movieId");
        movieDTO.setTitle("title");
        movieDTO.setGenre("genre");
        movieDTO.setDuration(0);
        final MovieScreenDTO movieScreenDTO = new MovieScreenDTO();
        movieScreenDTO.setScreenId(0);
        movieScreenDTO.setScreenName("screenName");
        movieScreenDTO.setCapacity(0);
        final MovieScheduleDTO movieScheduleDTO = new MovieScheduleDTO("scheduleId", movieDTO, movieScreenDTO,
                LocalDateTime.of(2020, 1, 1, 0, 0, 0), 0.0);
        when(mockMovieRepository.findById("movieId")).thenReturn(Optional.empty());

        // Configure MovieScreenRepository.findById(...).
        final Optional<MovieScreen> movieScreen = Optional.of(new MovieScreen(0, "screenName", 0));
        when(mockMovieScreenRepository.findById(0)).thenReturn(movieScreen);

        // Run the test
        assertThatThrownBy(() -> movieServiceImplUnderTest.createMovieSchedule(movieScheduleDTO))
                .isInstanceOf(MovieBookingException.class);
    }

    @Test
    void testCreateMovieSchedule_MovieScreenRepositoryReturnsAbsent() {
        // Setup
        final MovieDTO movieDTO = new MovieDTO();
        movieDTO.setMovieId("movieId");
        movieDTO.setTitle("title");
        movieDTO.setGenre("genre");
        movieDTO.setDuration(0);
        final MovieScreenDTO movieScreenDTO = new MovieScreenDTO();
        movieScreenDTO.setScreenId(0);
        movieScreenDTO.setScreenName("screenName");
        movieScreenDTO.setCapacity(0);
        final MovieScheduleDTO movieScheduleDTO = new MovieScheduleDTO("scheduleId", movieDTO, movieScreenDTO,
                LocalDateTime.of(2020, 1, 1, 0, 0, 0), 0.0);

        // Configure MovieRepository.findById(...).
        final Optional<Movie> movie = Optional.of(new Movie("movieId", "title", "genre", 0));
        when(mockMovieRepository.findById("movieId")).thenReturn(movie);

        when(mockMovieScreenRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> movieServiceImplUnderTest.createMovieSchedule(movieScheduleDTO))
                .isInstanceOf(MovieBookingException.class);
    }

    @Test
    void testCreateMovieSchedule_MovieScheduleRepositoryThrowsOptimisticLockingFailureException() {
        // Setup
        final MovieDTO movieDTO = new MovieDTO();
        movieDTO.setMovieId("movieId");
        movieDTO.setTitle("title");
        movieDTO.setGenre("genre");
        movieDTO.setDuration(0);
        final MovieScreenDTO movieScreenDTO = new MovieScreenDTO();
        movieScreenDTO.setScreenId(0);
        movieScreenDTO.setScreenName("screenName");
        movieScreenDTO.setCapacity(0);
        final MovieScheduleDTO movieScheduleDTO = new MovieScheduleDTO("scheduleId", movieDTO, movieScreenDTO,
                LocalDateTime.of(2020, 1, 1, 0, 0, 0), 0.0);

        // Configure MovieRepository.findById(...).
        final Optional<Movie> movie = Optional.of(new Movie("movieId", "title", "genre", 0));
        when(mockMovieRepository.findById("movieId")).thenReturn(movie);

        // Configure MovieScreenRepository.findById(...).
        final Optional<MovieScreen> movieScreen = Optional.of(new MovieScreen(0, "screenName", 0));
        when(mockMovieScreenRepository.findById(0)).thenReturn(movieScreen);

        when(mockMovieScheduleRepository.save(any(MovieSchedule.class)))
                .thenThrow(OptimisticLockingFailureException.class);

        // Run the test
        assertThatThrownBy(() -> movieServiceImplUnderTest.createMovieSchedule(movieScheduleDTO))
                .isInstanceOf(OptimisticLockingFailureException.class);
    }

    @Test
    void testViewAllSchedule() throws Exception {
        // Setup
        // Configure MovieScheduleRepository.findByMovieId(...).
        final MovieSchedule movieSchedule = new MovieSchedule();
        movieSchedule.setScheduleId("scheduleId");
        final Movie movie = new Movie();
        movie.setMovieId("movieId");
        movie.setTitle("title");
        movie.setGenre("genre");
        movie.setDuration(0);
        movieSchedule.setMovie(movie);
        final MovieScreen screen = new MovieScreen();
        screen.setScreenId(0);
        screen.setScreenName("screenName");
        screen.setCapacity(0);
        movieSchedule.setScreen(screen);
        movieSchedule.setStartTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        movieSchedule.setCost(0.0);
        final List<MovieSchedule> movieSchedules = List.of(movieSchedule);
        when(mockMovieScheduleRepository.findByMovieId("movieId")).thenReturn(movieSchedules);

        // Run the test
        final List<MovieSchedule> result = movieServiceImplUnderTest.viewAllSchedule("movieId");

        // Verify the results
    }

    @Test
    void testViewAllSchedule_MovieScheduleRepositoryReturnsNoItems() {
        // Setup
        when(mockMovieScheduleRepository.findByMovieId("movieId")).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> movieServiceImplUnderTest.viewAllSchedule("movieId"))
                .isInstanceOf(MovieBookingException.class);
    }

    @Test
    void testBookMovie() throws Exception {
        // Setup
        // Configure MovieScheduleRepository.findByMovieId(...).
        final MovieSchedule movieSchedule = new MovieSchedule();
        movieSchedule.setScheduleId("scheduleId");
        final Movie movie = new Movie();
        movie.setMovieId("movieId");
        movie.setTitle("title");
        movie.setGenre("genre");
        movie.setDuration(0);
        movieSchedule.setMovie(movie);
        final MovieScreen screen = new MovieScreen();
        screen.setScreenId(0);
        screen.setScreenName("screenName");
        screen.setCapacity(0);
        movieSchedule.setScreen(screen);
        movieSchedule.setStartTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        movieSchedule.setCost(0.0);
        final List<MovieSchedule> movieSchedules = List.of(movieSchedule);
        when(mockMovieScheduleRepository.findByMovieId("movieId")).thenReturn(movieSchedules);

        // Configure UserRepository.findById(...).
        final Optional<User> user = Optional.of(new User("userId", "userName", 0, 0L));
        when(mockUserRepository.findById("userId")).thenReturn(user);

        // Run the test
        final Booking result = movieServiceImplUnderTest.bookMovie("userId", "movieId", 0);

        // Verify the results
        verify(mockBookingRepository).save(any(Booking.class));
    }

    @Test
    void testBookMovie_MovieScheduleRepositoryReturnsNoItems() {
        // Setup
        when(mockMovieScheduleRepository.findByMovieId("movieId")).thenReturn(Collections.emptyList());

        // Configure UserRepository.findById(...).
        final Optional<User> user = Optional.of(new User("userId", "userName", 0, 0L));
        when(mockUserRepository.findById("userId")).thenReturn(user);

        // Run the test
        assertThatThrownBy(() -> movieServiceImplUnderTest.bookMovie("userId", "movieId", 0))
                .isInstanceOf(MovieBookingException.class);
    }

    @Test
    void testBookMovie_UserRepositoryReturnsAbsent() {
        // Setup
        // Configure MovieScheduleRepository.findByMovieId(...).
        final MovieSchedule movieSchedule = new MovieSchedule();
        movieSchedule.setScheduleId("scheduleId");
        final Movie movie = new Movie();
        movie.setMovieId("movieId");
        movie.setTitle("title");
        movie.setGenre("genre");
        movie.setDuration(0);
        movieSchedule.setMovie(movie);
        final MovieScreen screen = new MovieScreen();
        screen.setScreenId(0);
        screen.setScreenName("screenName");
        screen.setCapacity(0);
        movieSchedule.setScreen(screen);
        movieSchedule.setStartTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        movieSchedule.setCost(0.0);
        final List<MovieSchedule> movieSchedules = List.of(movieSchedule);
        when(mockMovieScheduleRepository.findByMovieId("movieId")).thenReturn(movieSchedules);

        when(mockUserRepository.findById("userId")).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> movieServiceImplUnderTest.bookMovie("userId", "movieId", 0))
                .isInstanceOf(MovieBookingException.class);
    }

    @Test
    void testBookMovie_BookingRepositoryThrowsOptimisticLockingFailureException() {
        // Setup
        // Configure MovieScheduleRepository.findByMovieId(...).
        final MovieSchedule movieSchedule = new MovieSchedule();
        movieSchedule.setScheduleId("scheduleId");
        final Movie movie = new Movie();
        movie.setMovieId("movieId");
        movie.setTitle("title");
        movie.setGenre("genre");
        movie.setDuration(0);
        movieSchedule.setMovie(movie);
        final MovieScreen screen = new MovieScreen();
        screen.setScreenId(0);
        screen.setScreenName("screenName");
        screen.setCapacity(0);
        movieSchedule.setScreen(screen);
        movieSchedule.setStartTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        movieSchedule.setCost(0.0);
        final List<MovieSchedule> movieSchedules = List.of(movieSchedule);
        when(mockMovieScheduleRepository.findByMovieId("movieId")).thenReturn(movieSchedules);

        // Configure UserRepository.findById(...).
        final Optional<User> user = Optional.of(new User("userId", "userName", 0, 0L));
        when(mockUserRepository.findById("userId")).thenReturn(user);

        when(mockBookingRepository.save(any(Booking.class))).thenThrow(OptimisticLockingFailureException.class);

        // Run the test
        assertThatThrownBy(() -> movieServiceImplUnderTest.bookMovie("userId", "movieId", 0))
                .isInstanceOf(OptimisticLockingFailureException.class);
    }

    @Test
    void testViewAllBooking() throws Exception {
        // Setup
        // Configure BookingRepository.findByUserId(...).
        final Booking booking = new Booking();
        final MovieSchedule movie = new MovieSchedule();
        movie.setScheduleId("scheduleId");
        final Movie movie1 = new Movie();
        movie1.setMovieId("movieId");
        movie1.setTitle("title");
        movie1.setGenre("genre");
        movie1.setDuration(0);
        movie.setMovie(movie1);
        final MovieScreen screen = new MovieScreen();
        screen.setScreenId(0);
        screen.setScreenName("screenName");
        screen.setCapacity(0);
        movie.setScreen(screen);
        movie.setStartTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        movie.setCost(0.0);
        booking.setMovie(movie);
        final User user = new User();
        user.setUserId("userId");
        user.setUserName("userName");
        user.setAge(0);
        user.setMobile(0L);
        booking.setUser(user);
        booking.setNoOfSeats(0);
        final List<Booking> bookings = List.of(booking);
        when(mockBookingRepository.findByUserId("userId")).thenReturn(bookings);

        // Run the test
        final List<Booking> result = movieServiceImplUnderTest.viewAllBooking("userId");

        // Verify the results
    }

    @Test
    void testViewAllBooking_BookingRepositoryReturnsNoItems() {
        // Setup
        when(mockBookingRepository.findByUserId("userId")).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> movieServiceImplUnderTest.viewAllBooking("userId"))
                .isInstanceOf(MovieBookingException.class);
    }
}
