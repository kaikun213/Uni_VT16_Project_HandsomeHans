package pages;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.SessionScoped;
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

	private User nUser = new User();
	private boolean showProfile = true;

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

	public void changeViewForUser() {
		showProfile = false;
	}
	
	public void changeViewForAdmin() {
		showProfile = false;
		nUser = new User();
	}

	public boolean getShowProfile() {
		return showProfile;

	}

	public void update(User u) {
		if (!this.nUser.getName().isEmpty())
			u.setName(this.nUser.getName());
		if (!this.nUser.getEmail().isEmpty())
			u.setEmail(this.nUser.getEmail());
		if (!this.nUser.getPassword().isEmpty())
			u.setPassword(this.nUser.getPassword());
		super.updateDB(u);
		super.notify("Updated Successfully", "");
		showProfile = true;
		//authentication.setName(u.getName()); //display name
		init();
	}

	public void update() {
		super.updateDB(nUser);
		super.notify("Updated Successfully", "");
		showProfile = true;
		//authentication.setName(nUser.getName()); //display name
		init();

	}
	
	public User getUser(){
		return nUser;
	}
	
	public void setUser(User u){
		nUser = u;
	}

}
