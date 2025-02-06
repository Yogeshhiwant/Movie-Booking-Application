package com.infy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String userId;
	private String userName;
	private int age;
	private long mobile;

	public User(String userId, String userName, int age, long mobile) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.age = age;
		this.mobile = mobile;
	}

	public User() {
		super();
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
