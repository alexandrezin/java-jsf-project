package controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import model.entities.User;
import model.services.UserService;

@ManagedBean
@SessionScoped
public class UserController {

	private String searchText;
	private User user = new User();
	private List<User> userList;

	public void validateName(FacesContext context, UIComponent component, Object value) throws ValidatorException{
		
		for(char c : value.toString().toCharArray()) {
			if (Character.isDigit(c)) throw new ValidatorException(new FacesMessage("Please, enter a valid name"));
		}
		return;
	}
	
	public void loadUserList() {
		userList = UserService.getAll();
	}
	
	public String onSearchButtonAction() {
		if (searchText != null) {
			userList = UserService.getByParameter(searchText);
		}
		return (null);
	}
	
	public String onAddNewUserButtonAction() {
		user = new User();
		return "user_add";
	}
	
	public String onSaveButtonAction() {
		if (this.user.getId()==0) UserService.createUser(this.user);
		else UserService.updateUser(this.user);
		loadUserList();
		return "user_list";
	}

	public String loadSingleUser(User user) {
		this.user = user;
		return "user_add.xhtml";
	}
	
	public String deleteUser(int id) {
		UserService.deleteUser(id);
		loadUserList();
		return (null);
	}
	
	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUserList() {
		if (userList == null) loadUserList();
		return userList;
	}
	
}