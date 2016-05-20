package pages;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import authentification.AuthenticationBean;
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

	private AuthenticationBean authentication = new AuthenticationBean();
	private User user = new User();
	private ArrayList<Order> orderList = new ArrayList<Order>();
	private List<User> users = new ArrayList<User>();
	private boolean showProfile = true;

	public void init() {
		setContent("SELECT * FROM user WHERE admin='0';");
		try {
			users = toUsers(content);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getName().equals(authentication.getName())
					&& users.get(i).getPassword().equals(authentication.getPassword())) {
				user = users.get(i);
				break;
			}
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
		super.notify("Registered Successfully", "");
		user = new User();
		init();
	}

	public ArrayList<Order> getOrderList() {
		return orderList;
	}

	public void deleteUser(User u) {
		super.deleteDB(u);
		super.notify("" + u.getName(), "Removed");
		init();
	}

	public void changeViewForUser() {
		showProfile = false;
	}
	
	public void changeViewForAdmin() {
		showProfile = false;
		user = new User();
	}

	public boolean getShowProfile() {
		return showProfile;

	}

	public void update(User u) {
		if (!this.user.getName().isEmpty())
			u.setName(this.user.getName());
		if (!this.user.getEmail().isEmpty())
			u.setEmail(this.user.getEmail());
		if (!this.user.getPassword().isEmpty())
			u.setPassword(this.user.getPassword());
		super.updateDB(u);
		super.notify("Updated Successfully", "");
		showProfile = true;
		authentication.setName(u.getName()); //display name
		init();
	}

	public void update() {
		super.updateDB(user);
		super.notify("Updated Successfully", "");
		showProfile = true;
		authentication.setName(user.getName()); //display name
		init();

	}

}
