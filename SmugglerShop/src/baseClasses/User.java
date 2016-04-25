package baseClasses;

public class User {

	private String name;
	private String order;
	private String email;
	
	public User(){};

	public User(String userName, String order, String email) {
		this.name = userName;
		this.order = order;
		this.email = email;
	}

	public String getUserName() {
		return name;
	}

	public void setUserName(String userName) {
		this.name = userName;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserName: " + name + " Order: " + order + "Email: " + email;
	}

}
