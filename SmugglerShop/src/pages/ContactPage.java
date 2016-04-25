/**
 * 
 */
package pages;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import baseClasses.Page;
import baseClasses.User;

/**
 * @author kaikun
 *			Prototype for future site for adding users to the database.
 *			Missing: if a user already exists, always adds a new user
 */

@Named
@SessionScoped
public class ContactPage extends Page implements Serializable {
		
	/**
	 * Default serialVersionID generated from eclipse
	 */
	private static final long serialVersionUID = 1L;
	private User user = new User();
	

	public void setName(String u) {
		user.setUserName(u);
	}
	
	public void setEmail(String e){
		user.setEmail(e);
	}
	
	public void setOrder(String o){
		user.setOrder(user.getOrder() + ";" + o);
	}
	
	
}
