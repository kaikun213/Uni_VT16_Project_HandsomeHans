package pages;

import java.io.Serializable;
import java.util.ArrayList;

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

	private User nUser = new User();

	public void init() {
	
	}

	public void addUser() {
		nUser.setAdmin(false);
		nUser.setOrders(new ArrayList<Order>());
		super.insertDB(nUser);
		super.notify("Registered Successfully", "");
		nUser = new User();
		init();
	}

	public void deleteUser(User u) {
		super.deleteDB(u);
		super.notify("" + u.getName(), "Removed");
		init();
	}

	public void update(User u) {
		System.out.println("NaME: " + u.getName());
		System.out.println("EMAIL: " + u.getEmail());
		System.out.println("PASS: " + u.getPassword());
		super.updateDB(u);
		super.notify("Updated Successfully", "");
		init();
	}

	public void update() {
		super.updateDB(AuthenticationBean.activeUser);
		super.notify("Updated Successfully", "");
		init();
	}

	public User getUser() {
		return nUser;
	}

	public void setUser(User u) {
		nUser = u;
	}

}
