package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;

public class UserService {
	
	public static void createUser(User user) {
		System.out.println("createUser");
		UserDao userDao = DaoFactory.createUserDao();
		userDao.create(user);
	}
	
	public static void updateUser(User user) {
		UserDao userDao = DaoFactory.createUserDao();
		userDao.update(user);
	}
	
	public static void deleteUser(int id) {
		UserDao userDao = DaoFactory.createUserDao();
		userDao.delete(id);
	}
	
	public static User getUserById(int id) {
		UserDao userDao = DaoFactory.createUserDao();
		return userDao.getUserById(id);
	}
	
	public static List<User> getByParameter(String parameter) {
		UserDao userDao = DaoFactory.createUserDao();
		return userDao.getByParameter(parameter);
	}
	
	public static List<User> getAll() {
		UserDao userDao = DaoFactory.createUserDao();
		return userDao.getAll();
	}
}
