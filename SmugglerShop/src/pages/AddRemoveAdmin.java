package pages;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

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
	
	private User admin = new User();
	private ArrayList<Order> arr = new ArrayList<Order>();
	private List<User> users = new ArrayList<User>();
	/**
	 * Default serialVersionID generated from eclipse
	 */
	private static final long serialVersionUID = 1L;
	
	public void init(){
		setContent("SELECT * FROM user;");
		try {
			users = toUsers(content);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
    public List<User> getUsers(){
    	System.out.println(users.size());
        return users;
    }
	
	
	public User getAdmin(){
		return admin;
	}
	
	public void setAdmin(User u){
		admin = u;
	}
	
	public void addUser(){ 
		admin.setAdmin(true);
		admin.setOrders(arr);
		super.insertDB(admin);
		// reset fields
		admin = new User();
		// load DB Admin list new
		init();
	}
	
	public void removeUser(User u){
		super.deleteDB(u);
		// load DB Admin list new
		init();
	}
}
