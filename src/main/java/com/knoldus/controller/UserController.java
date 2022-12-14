package com.knoldus.controller;

import com.knoldus.data.UserData;
import com.knoldus.exception.CustomException;
import com.knoldus.model.User;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller
public class UserController {

	protected final UserData userData;

	public UserController(UserData userData) {
		this.userData = userData;
	}

	@Get("/users/{id}")
	public User findUserById(String id) throws CustomException {
		Long user_id = null;
		try {
			user_id = Long.parseLong(id);
		} catch (NumberFormatException e) {
			throw new CustomException("User Id must be numeric");
		}
		User user = userData.getUserById(user_id);
		if (user == null) {
			throw new CustomException("Entity Not Found");
		}
		return user;
	}
}
