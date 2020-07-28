package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.entities.User;
import model.services.UserService;

@ManagedBean
@SessionScoped
public class UserController {
	private List<User> userList;
	
	public UserController() {
		userList = new ArrayList<User>();
	}

	public List<User> getUserList() {
		return userList;
	}
	
	public void loadUserList() {
		userList = UserService.getAll();
	}
	
}
