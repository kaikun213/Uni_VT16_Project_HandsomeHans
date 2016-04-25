package baseClasses;

public class User {

	private String userName;
	private String order;
	private String email;

	public User(String userName, String order, String email) {
		this.userName = userName;
		this.order = order;
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
		return "UserName: " + userName + " Order: " + order + "Email: " + email;
	}

}
