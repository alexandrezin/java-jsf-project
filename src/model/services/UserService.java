package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;

public class UserService {
	
	public static List<User> getAll() {
		UserDao userDao = DaoFactory.createUserDao();
		return userDao.getAll();
	}
}
