/**
 * 
 */
package pages;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import baseClasses.Order.OrderStatus;
import baseClasses.Order;
import baseClasses.Page;
import baseClasses.Product;
import baseClasses.User;

/**
 * @author kaikun
 *
 */

@Named
@SessionScoped
public class AdminPages extends Page implements Serializable {
	
	/**
	 * Default serialVersionID generated from eclipse
	 */
	private static final long serialVersionUID = 1L;
	
	public void test(){
		Product p = new Product("test","1", 123, "", "description", 100, 10);
		ArrayList<Product> arr = new ArrayList<Product>();
		arr.add(p);
		Order o = new Order(100,arr,"date",OrderStatus.IN_PROCESS);
		ArrayList<Order> arr2 = new ArrayList<Order>();
		arr2.add(o);
		User u = new User("test",arr2,"Testemail", "password", false);
		String s = super.toSQL(u);
		super.updateDB(s);
	}	
	
	
}
