package fi.tuni.bloggingapp.controller;

import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fi.tuni.bloggingapp.entity.User;
import fi.tuni.bloggingapp.repository.UserRepository;

/**
 * Class that handles creating new users.
 *
 * @author Teemu Salminen
 * @version 2019.04.22
 * @since 0.1
 */
@RestController
public class UserController {

	/**
	 * Repository to use for database stored users
	 */
	@Autowired
	private UserRepository userRepo;

	/**
	 * Return all users, excluding their passwords.
	 * @return all users
	 */
	@RequestMapping(value = "users/", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	/**
	 * Adds a new user to database, if username isn't already taken.
	 * @param user User to add.
	 * @return User added to db
	 * @throws UsernameAlreadyTakenException thrown when username is taken
	 */
	@RequestMapping(value = "users/", method = RequestMethod.PUT)
		public User createUser(@RequestBody User user) throws UsernameAlreadyTakenException {
			User createdUser;
			user.setIsAdmin(false);

			try {
				createdUser = userRepo.save(user);

				return createdUser;
			} catch(DataIntegrityViolationException  e) {
				throw new UsernameAlreadyTakenException("Username is already in use!");
			}
		
	}

	/**
	 * Thrown when username is taken
	 */
	@ResponseStatus(code = HttpStatus.CONFLICT)
	class UsernameAlreadyTakenException extends AuthenticationException {
		
		public UsernameAlreadyTakenException(String msg) {
			super(msg);
		}
		
	}
	




}
