package pages;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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
public class AdminAccounts extends Page implements Serializable {

	private User nAdmin = new User();
	private ArrayList<Order> arr = new ArrayList<Order>();
	private List<User> allUsers = new ArrayList<User>();
	private List<User> allAdmins = new ArrayList<User>();

	/**
	 * Default serialVersionID generated from eclipse
	 */
	private static final long serialVersionUID = 1L;

	public void init() {
		setContent("SELECT * FROM user WHERE admin='1';");
		try {
			setAllAdmins(toUsers(content));
			setContent("SELECT * FROM user WHERE admin='0';");
			setAllUsers(toUsers(content));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public User getAdmin() {
		return nAdmin;
	}

	public void setAdmin(User u) {
		nAdmin = u;
	}

	public void addUser() {
		if (nAdmin.getEmail().isEmpty() || nAdmin.getPassword().isEmpty() || nAdmin.getName().isEmpty())
			super.notify("Please", "Fill all required fields");
		if (ifUserExist(nAdmin)) {
			super.notify("User Already Exists", "Change username");
		} else {
			nAdmin.setOrders(arr);
			super.insertDB(nAdmin);
			super.notify("" + this.nAdmin.getName(), "added with privileges " + nAdmin.getAdmin());
			nAdmin = new User();
			init();
		}
	}

	public void removeUser(User u) {
		if (u.getName().equals(AuthenticationBean.activeUser.getName()) && u.getPassword().equals(AuthenticationBean.activeUser.getPassword())) {
			super.notify("Unfortunately", "you cannot remove your own account");
		} else {
			super.deleteDB(u);
			super.notify("" + u.getName(), "Removed");
			init();
		}
	}

	public void update(User u) {
		super.updateDB(u);
		super.notify("Updated", "successfully");
		init();
	}

	public List<User> getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}

	public List<User> getAllAdmins() {
		return allAdmins;
	}

	public void setAllAdmins(List<User> allAdmins) {
		this.allAdmins = allAdmins;
	}

	public boolean ifUserExist(User u) {
		return super.exist("SELECT * FROM user WHERE name=\"" + u.getName() + "\";");
	}
	
	@SuppressWarnings("unchecked")
	public void sortByName(boolean b){
		if (b) Collections.sort(allAdmins);
		else Collections.sort(allUsers);
	}
	
}