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
	
	private List<Order> orders;
	private Order selectedOrder;
	
	private static final long serialVersionUID = 1L;
	
	public void test(){
		Product p = new Product("test","1", 123, "", "description", 100, 10);
		ArrayList<Product> arr = new ArrayList<Product>();
		arr.add(p);
		Order o = new Order(100,arr,"date",OrderStatus.IN_PROCESS);
		ArrayList<Order> arr2 = new ArrayList<Order>();
		arr2.add(o);
		User u = new User(-99,"test",arr2,"Testemail", "password", false);
		toSQL(u);
		toSQL(p);
		toSQL(o);
	}	
	
	// Set & Get Methods
	public List<Order> getOrders(){
		return orders;
	}
	
	public void setOrders(){
		setContent("SELECT * FROM orders;");
		try {
			orders = toOrders(content);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Order getSelectedOrder(){
		return selectedOrder;
	}
	
	public void setSelectedOrder(Order o){
		selectedOrder = o;
	}
	
}
