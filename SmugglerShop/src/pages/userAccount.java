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
	private AddRemoveAdmin adminClass = new AddRemoveAdmin();

	public void init() {

	}

	public void addUser() {
		if (!adminClass.ifUserExist(nUser)) {
			nUser.setAdmin(false);
			nUser.setOrders(new ArrayList<Order>());
			if (nUser.getPhone().length() == 0)
				nUser.setPhone("---"); // otherwise, you cannot edit the field
			super.insertDB(nUser);
			super.notify("Registered Successfully", "");
			nUser = new User();
		} else
			super.notify("User Already Exists", "Change username");
	}

	public void deleteUser(User u) {
		super.deleteDB(u);
		super.notify("" + u.getName(), "Removed");
	}

	public void update() {
			super.updateDB(AuthenticationBean.activeUser);
			super.notify("Updated Successfully", "");
	}

	public User getUser() {
		return nUser;
	}

	public void setUser(User u) {
		nUser = u;
	}
}
