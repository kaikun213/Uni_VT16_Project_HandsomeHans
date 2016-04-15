package baseClasses;

/**
 * This class represents an admin.
 * 
 * @author SarpreetSingh
 *
 */
public class Admin {
	// Fields
	private String userName= "";
	private String email= "";
	private String password= "";

	/**
	 * Empty constructor
	 */
	public Admin() {
	}

	/**
	 * Full constructor, throw error if given email and username belong to other
	 * user or null as well as if password is null.
	 * 
	 * @param userName
	 * @param email
	 * @param password
	 */
	public Admin(String userName, String email, String password) {
		if (userName == null || email == null || password == null)
			throw new IllegalArgumentException(
					"Invalid details.\nUSERNAME: " + userName + "\nEmail: " + email + "\nPASSWORD: " + password);
		else if (checkUserName(userName) && checkEmail(email) == true) {
			this.userName = userName;
			this.email = email;
			this.password = password;
		} else
			throw new IllegalArgumentException("Change the details.");
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
	 * Set admin username, throw error if given username belongs to other user
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		if (checkUserName(userName) == false)
			throw new IllegalArgumentException("Change username.");
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
	 * Set admin email, throw error if given email belongs to other user
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		if (checkEmail(email) == false)
			throw new IllegalArgumentException("Change email address.");
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
		if (password == null)
			throw new IllegalArgumentException("Please set the password.");
		this.password = password;
	}

	/**
	 * Remove the admin
	 */
	public void removeAdmin() {
		this.userName = "";
		this.email = "";
		this.password = "";
	}

	/**
	 * Represent a print out
	 */
	@Override
	public String toString() {
		return "userName: " + userName + "\n email: " + email + "\n password: " + password;
	}

	/**
	 * Return true if given username does not belong to other user, else false.
	 * 
	 * @param userName
	 * @return boolean
	 */
	private boolean checkUserName(String userName) {
		if (this.userName.equals(userName)) {
			System.err.println("Given username is already selected by other user.");
			return false;
		} else
			return true;
	}

	/**
	 * Return true if given email does not belong to other user, else false.
	 * 
	 * @param email
	 * @return
	 */
	private boolean checkEmail(String email) {
		if (this.email.equals(email)) {
			System.err.println("Given email is already selected by other user.");
			return false;
		} else
			return true;
	}

}
