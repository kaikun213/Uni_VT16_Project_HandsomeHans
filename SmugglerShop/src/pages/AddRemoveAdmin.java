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

/**
 * @author
 *
 */

@Named
@SessionScoped
public class AddRemoveAdmin extends Page implements Serializable {

	private  AuthenticationBean authentication = new  AuthenticationBean ();
	private User admin = new User();
	private ArrayList<Order> arr = new ArrayList<Order>();
	private List<User> users = new ArrayList<User>();
	private boolean showProfile = true;
	/**
	 * Default serialVersionID generated from eclipse
	 */
	private static final long serialVersionUID = 1L;

	public void init() {
		setContent("SELECT * FROM user WHERE admin='1';");
		try {
			users = toUsers(content);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<User> getUsers() {
		System.out.println(users.size());
		return users;
	}

	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User u) {
		admin = u;
	}

	public void addUser() {
		if (admin.getEmail().isEmpty() || admin.getPassword().isEmpty() || admin.getName().isEmpty())
			super.notify("Please", "Fill all required fields");
		else {
			admin.setAdmin(true);
			admin.setOrders(arr);
			super.insertDB(admin);
			super.notify("" + this.admin.getName(), "added as admin");
			admin = new User();
			init();
		}
	}

	public void removeUser(User u) {
		if(u.getName().equals(authentication.getName()) && u.getPassword().equals(authentication.getPassword())){
			super.notify("Unfortunately", "you cannot remove your own account");
		} else {
			super.deleteDB(u);
			super.notify("" + u.getName(), "Removed");
			// load DB Admin list new
			init();
		}
	}

	public boolean getShowProfile() {
		return showProfile;
	}

	public void changeView() {
		showProfile = false;
	}

	public void update() {
		admin.setAdmin(true);
		admin.setOrders(arr);
		super.updateDB(admin);
		super.notify("Updated", "successfully");
		showProfile = true;
		init();

	}

}
