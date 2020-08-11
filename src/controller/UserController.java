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
	
	private String userAddTitle = "";
	private boolean showDeleteButton = false;
	private boolean showClearButton = false;
	
	public void validateName(FacesContext context, UIComponent component, Object value) throws ValidatorException{
		
		for(char c : value.toString().toCharArray()) {
			if (Character.isDigit(c)) throw new ValidatorException(new FacesMessage("Please, enter a valid name"));
		}
		return;
	}
	
	public void loadUserList() {
		userList = UserService.getAll();
	}
	
	public void onSearchButtonAction() {
		if (!searchText.equals("")) {
			userList = UserService.getByParameter(searchText);
			showClearButton = true;
		}
	}
	
	public void onClearButtonAction() {
		userList = null;
		searchText = "";
		showClearButton = false;
	}
	
	public String onAddNewUserButtonAction() {
		user = new User();
		this.userAddTitle = "Create User";
		this.showDeleteButton = false;
		return "user_add?faces-redirect=true";
	}
	
	public String onSaveButtonAction() {
		if (this.user.getId()==0) UserService.createUser(this.user);
		else UserService.updateUser(this.user);
		loadUserList();
		searchText = "";
		this.user = new User();
		return "user_list?faces-redirect=true";
	}

	public String loadSingleUser() {
		if (this.user == null) { 
			return (null);
		}
		this.userAddTitle = "Update User";
		this.showDeleteButton = true;
		return "user_add?faces-redirect=true";
	}
	
	public String deleteUser() {
		UserService.deleteUser(this.user.getId());
		loadUserList();
		return "user_list?faces-redirect=true";
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

	public String getUserAddTitle() {
		return userAddTitle;
	}

	public boolean isShowDeleteButton() {
		return showDeleteButton;
	}

	public boolean isShowClearButton() {
		return showClearButton;
	}
}