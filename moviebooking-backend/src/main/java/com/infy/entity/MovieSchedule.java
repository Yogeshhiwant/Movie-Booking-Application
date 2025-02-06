package com.infy.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class MovieSchedule {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String scheduleId;
	@ManyToOne
	@JoinColumn(name="movie_id")
	private Movie movie;
	@ManyToOne
	@JoinColumn(name="screen_id")
	private MovieScreen screen;
	private LocalDateTime startTime;
	private double cost;
	public MovieSchedule(String scheduleId, Movie movie, MovieScreen screen, LocalDateTime startTime, double cost) {
		super();
		this.scheduleId = scheduleId;
		this.movie = movie;
		this.screen = screen;
		this.startTime = startTime;
		this.cost = cost;
	}
	public MovieSchedule() {
		super();
	}
	public String getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public MovieScreen getScreen() {
		return screen;
	}
	public void setScreen(MovieScreen screen) {
		this.screen = screen;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	@Override
	public String toString() {
		return "MovieSchedule [scheduleId=" + scheduleId + ", movie=" + movie + ", screen=" + screen + ", startTime="
				+ startTime + ", cost=" + cost + "]";
	}
	
}
