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
			DB.closeConnection(conn);
		}
	}

	@Override
	public void update(User user) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("UPDATE user SET name_user = ?, email_user = ? WHERE id_user = ?");
			st.setString(1, user.getName());
			st.setString(2, user.getEmail());
			st.setInt(3, user.getId());
			st.executeUpdate();
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection(conn);
		}
	}
	
	@Override
	public void delete(int id) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("DELETE FROM user WHERE id_user = ?");
			st.setInt(1, id);
			st.executeUpdate();
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection(conn);
		}
		
	}
	
	@Override
	public User getUserById(int id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		User user = new User();
		
		try {
			st = conn.prepareStatement("SELECT * FROM user WHERE user.id_user = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				user.setId(rs.getInt("id_user"));
				user.setName(rs.getString("name_user")); 
				user.setEmail(rs.getString("email_user"));
			}
			
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection(conn);
		}
		return user;
	}
	
	@Override
	public List<User> getByParameter(String parameter) {
		List<User> userList = new ArrayList<User>();
		
		Statement st = null;
		ResultSet rs= null;
	
		try {
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE name_user LIKE '%" + parameter + "%' OR email_user LIKE '%" + parameter + "%'");
			
			while (rs.next()) {
				User user = new User(rs.getInt("id_user"), rs.getString("name_user"), rs.getString("email_user"));
				userList.add(user);
			}
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection(conn);
		}
		return userList;
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
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection(conn);
		}
		return userList;
	}

}
