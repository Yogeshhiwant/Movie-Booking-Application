package com.infy.dto;

import java.util.Optional;

import com.infy.entity.MovieScreen;
import jakarta.persistence.Column;

public class MovieScreenDTO {
	@Column(name = "screenId")
	private int screenId;
	private String screenName;
	private int capacity;

	public MovieScreenDTO(int screenId, String screenName, int capacity) {
		super();
		this.screenId = screenId;
		this.screenName = screenName;
		this.capacity = capacity;
	}

	public MovieScreenDTO() {
		super();
	}

	public int getScreenId() {
		return screenId;
	}

	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "MovieScreen [screenId=" + screenId + ", screenName=" + screenName + ", capacity=" + capacity + "]";
	}

	public static MovieScreenDTO entityTODTO(Optional<MovieScreen> movieScreen) {
		if (movieScreen.isPresent()) {
			MovieScreen movieScreen2 = movieScreen.get();
			MovieScreenDTO dto = new MovieScreenDTO();
			dto.setCapacity(movieScreen2.getCapacity());
			dto.setScreenId(movieScreen2.getScreenId());
			dto.setScreenName(movieScreen2.getScreenName());
			return dto;
		}
		return null;
	}

}
