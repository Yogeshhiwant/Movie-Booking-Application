package com.infy.dto;

import jakarta.validation.constraints.NotNull;

public class UserDTO {
	@NotNull(message = "{please provide UserId}")
	private String userId;
	@NotNull(message = "{userName should not be empty}")
	private String userName;
	private int age;
	private long mobile;

	public UserDTO(String userId, String userName, int age, long mobile) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.age = age;
		this.mobile = mobile;
	}

	public UserDTO() {

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", age=" + age + ", mobile=" + mobile + "]";
	}

}
