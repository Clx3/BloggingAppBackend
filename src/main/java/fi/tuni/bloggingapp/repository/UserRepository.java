package fi.tuni.bloggingapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fi.tuni.bloggingapp.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	public List<User> findAll();
	
	public User findByUsername(String username);

}
