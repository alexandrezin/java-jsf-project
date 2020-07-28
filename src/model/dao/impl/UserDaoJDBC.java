package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.UserDao;
import model.entities.User;

public class UserDaoJDBC implements UserDao {
	
	Connection conn;
	
	public UserDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void create(User user) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("INSERT INTO user(name_user, email_user) VALUES (?,?)");
			st.setString(1, user.getName());
			st.setString(2, user.getEmail());
			st.executeUpdate();
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> getAll() {
		List<User> userList = new ArrayList<User>();
		
		Statement st = null;
		ResultSet rs= null;
	
		try {
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM user");
			
			while (rs.next()) {
				User user = new User(rs.getInt("id_user"), rs.getString("name_user"), rs.getString("email_user"));
				userList.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}

}
