package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DB {
	
	private static DB instance;
	private DataSource dataSource;
	private String jndiName = "java:comp/env/jdbc/user_tracker";
	
	//Get Instance of DB Class
	public static DB getInstance(){
		if (instance == null) {
			try {
				instance = new DB();
			} 
			catch (Exception e) {
				throw new DbException(e.getMessage());
			}
		}
		
		return instance;
	}
	
	//Used locally only
	//If the instance is not create, create it
	private DB(){		
		try {
			dataSource = getDataSource();
		} 
		catch (NamingException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	//get data Source
	private DataSource getDataSource() throws NamingException {
		Context context = new InitialContext();
		
		DataSource theDataSource = (DataSource) context.lookup(jndiName);
		
		return theDataSource;
	}
	
	//Get / Open Connection 
	public Connection getConnection(){

		Connection conn;
		try {
			conn = dataSource.getConnection();
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		return conn;
	}
	
	//Close Connection
	public static void closeConnection(Connection conn) {
		if (conn != null)
			try {
				conn.close();
			} 
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
	}
	
	//Close RS
	public static void closeResultSet(ResultSet rs) {
		if (rs != null)
			try {
				rs.close();
			} 
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
	}
	
	//Close ST
	public static void closeStatement(Statement st) {
		if (st != null)
			try {
				st.close();
			} 
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
	}
	
}
