package model.dao;

import java.util.List;

import model.entities.User;

public interface UserDao {
	void create(User user);
	void update(User user);
	void delete(int id);
	User getUserById(int id);
	List<User> getByParameter(String parameter);
	List<User> getAll();
	
}
