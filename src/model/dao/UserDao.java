package model.dao;

import java.util.List;

import model.entities.User;

public interface UserDao {
	void create(User user);
	void update(User user);
	void delete(int id);
	List<User> getAll();
	
}
