package baseClasses;

import java.util.ArrayList;

public class User {

	private int id;
	private String name;
	private ArrayList<Order> orders;
	private String email;
	private String password;
	private boolean admin = false;
	
	public User(){};

	public User(int id, String userName, ArrayList<Order> orders, String email, String password, boolean b) {
		this.id = id;
		this.name = userName;
		this.orders = orders;
		this.email = email;
		this.password = password;
		this.admin = b;
	}
	
	public User(String userName, ArrayList<Order> orders, String email, String password, boolean b) {
		this.name = userName;
		this.orders = orders;
		this.email = email;
		this.password = password;
		this.admin = b;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id=id;
	}
	
	public boolean getAdmin() {
		return admin;
	}
	
	public void setAdmin(boolean x){
		admin = x;
	}

	public String getName() {
		return name;
	}

	public void setName(String Name) {
		this.name = Name;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserName: " + name + " Order: " + orders.toString() + "Email: " + email + "Password: " + password;
	}
	
	/**
	 * This method returns admin password
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set the admin password
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/** Checks if the User is complete and valid for the DB
	 * 
	 * @return
	 */
	public boolean isComplete(){
		if (name == null || password == null || email == null) return false;
		return ((!name.isEmpty()) && (!password.isEmpty()) && (!email.isEmpty()));
	}

	public User copy(){
		return new User(this.id,this.name,this.getOrders(),this.email,this.password,this.admin);
	}

}
