package baseClasses;

/**
 * This class represents an admin.
 * 
 * @author SarpreetSingh
 *
 */
public class Admin {
	// Fields
	private String userName = "";
	private String email = "";
	private String password = "";

	/**
	 * Empty constructor
	 */
	public Admin() {
	}

	/**
	 * Full constructor
	 * 
	 * @param userName
	 * @param email
	 * @param password
	 */
	public Admin(String userName, String email, String password) {
		this.userName = userName;
		this.email = email;
		this.password = password;
	}

	/**
	 * This method returns admin username
	 * 
	 * @return String
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Set admin username
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * This method returns admin email
	 * 
	 * @return String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set admin email
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
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

	/**
	 * Represent a print out
	 */
	@Override
	public String toString() {
		return "userName: " + userName + "\n email: " + email + "\n password: " + password;
	}

}
