package pages;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import baseClasses.Order;
import baseClasses.Page;
import baseClasses.User;

@Named
@SessionScoped
public class userAccount extends Page implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user = new User();
	private ArrayList<Order> orderList = new ArrayList<Order>();
	private List<User> users = new ArrayList<User>();
	private boolean login = false;
	private boolean isRegister = false;
	private boolean updateAccount = false;
	private boolean showProfile = true;
	
	
	
	
	public void init() {
		setContent("SELECT * FROM user;");
		try {
			users = toUsers(content);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<User> getAllUsers() {
		return users;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void addUser() {
		user.setAdmin(false);
		user.setOrders(orderList);
		super.insertDB(user);
		if(updateAccount == true)
			super.notify("Updated successfully","");
		
		isRegister = true;
		showProfile = true;
		updateAccount = false;
	}

	public ArrayList<Order> getOrderList() {
		return orderList;
	}

	public void login() {
		setContent("SELECT * FROM user;");
		try {
			users = toUsers(content);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getName().equals(this.user.getName())
					&& users.get(i).getPassword().equals(this.user.getPassword())) {
				ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
				try {
					login = true;
					System.out.println("Login:" + login);
					ec.redirect(ec.getApplicationContextPath() + "/userAccount.xhtml");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		super.notify("Invalid account", "");
	}

	public void logout() {
		if (login == true) {
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			try {
				login = false;
				System.out.println("Login:" + login);
				ec.redirect(ec.getApplicationContextPath() + "/mainpage.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
	}

	public boolean getLogin() {
		return login;
	}
	public boolean getIsRegister() {
		return isRegister;
	}
	public boolean getUpdateAccount() {
		return updateAccount;
	}

	public void update(){
		updateAccount = true;
		showProfile = false;
	}
	public boolean getShowProfile() {
		return showProfile;
	}
	

}
