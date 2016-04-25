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
import baseClasses.Product;

/**
 * @author kaikun
 *
 */

@Named
@SessionScoped
public class ContactPage extends Page implements Serializable {
		
	/**
	 * Default serialVersionID generated from eclipse
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Takes the contact details from the user to create an order
	 */
	public void setOrder() {
		
	}
	
	/**	Get the content of the users basket
	 * 
	 * @return the items of the basket and the total price.
	 */
	public List<Product> getBasket(){
		return null;
	}	
	
	public int getPrice(){
		return 0;
	}
	
}
