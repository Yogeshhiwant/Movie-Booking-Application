package com.infy.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

public class MovieScheduleDTO {
	@Column(name = "schedule_id")
	private int scheduleId;
	@ManyToOne
	private MovieDTO movie;
	@ManyToOne
	private MovieScreenDTO screen;
	private LocalDateTime startTime;
	@NotNull(message = "{please choose cost}")
	private double cost;	

	public MovieScheduleDTO() {
	}

	public MovieScheduleDTO(int scheduleId, MovieDTO movie, MovieScreenDTO screen, LocalDateTime startTime,
			Double cost) {
		super();
		this.scheduleId = scheduleId;
		this.movie = movie;
		this.screen = screen;
		this.startTime = startTime;
		this.cost = cost;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public MovieDTO getMovie() {
		return movie;
	}

	public void setMovie(MovieDTO movie) {
		this.movie = movie;
	}

	public MovieScreenDTO getScreen() {
		return screen;
	}

	public void setScreen(MovieScreenDTO screen) {
		this.screen = screen;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "MovieSchedule [scheduleId=" + scheduleId + ", movie=" + movie + ", screen=" + screen + ", startTime="
				+ startTime + ", cost=" + cost + "]";
	}

}
