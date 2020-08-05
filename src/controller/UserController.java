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
	
	
	private String message = "";
	public String showMessage() {
		System.out.println("showMessage");
		this.message = "Esta mensagem deve ser mostrada apos apertar o botao";
		return (null);
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	//----------------------
	
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
		System.out.println("onSearchButtonAction");
		if (searchText != null) {
			userList = UserService.getByParameter(searchText);
		}
		return (null);
	}
	
	public String onSaveButtonAction() {
		if (this.user.getId()==0) UserService.createUser(this.user);
		else UserService.updateUser(this.user);
		user = new User();
		return "user_list";
	}

	public String loadSingleUser(User user) {
		this.user = user;
		return "user_add.xhtml";
	}
	
	public String deleteUser(int id) {
		UserService.deleteUser(id);
		return "user_list.xhtml";
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
		if (userList == null) {
			userList = UserService.getAll();
		}
		return userList;
	}
	
}
