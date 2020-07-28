package model.dao;

import db.DB;
import model.dao.impl.UserDaoJDBC;

public class DaoFactory {
	public static UserDao createUserDao() {
		DB db = DB.getInstance();
		return new UserDaoJDBC(db.getConnection());
	}
}
