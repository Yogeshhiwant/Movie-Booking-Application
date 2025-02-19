package com.infy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private MovieSchedule movie;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private int noOfSeats;

    public Booking(int bookingId, MovieSchedule movie, User user, int noOfSeats) {
        super();
        this.bookingId = bookingId;
        this.movie = movie;
        this.user = user;
        this.noOfSeats = noOfSeats;
    }

    public Booking() {
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public MovieSchedule getMovie() {
        return movie;
    }

    public void setMovie(MovieSchedule movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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
