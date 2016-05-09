/**
 * 
 */
package pages;

import java.io.Serializable;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import baseClasses.Order;
import baseClasses.Order.OrderStatus;
import baseClasses.Page;
import baseClasses.Product;
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
	private String searchOrder = "";
	private Order searchedOrder;
	

	public void setName(String u) {
		user.setName(u);
	}
	
	public void setEmail(String e){
		user.setEmail(e);
	}
	
	public void setOrderOnUser(String o){
		
	}
	
	public String submitOrder(){
		// if all fields are filled ---------------------------------------- Missing
		
		// if no items are in the basket
		if (Basket.products.size() <= 0) {
			super.notify("Error!", "You don't have Items in your basket!");
			return "contactForm";
		}

		Order o = new Order(Basket.products,new Date(),OrderStatus.IN_PROCESS);
		super.insertDB(o);
		
		// update DB quantities
		for (int i=0;i<Basket.products.size();i++) {
			int id = Basket.products.get(i).getId();
			int q = super.getQuantity(Integer.toString(id)) - Basket.products.get(i).getQuantity();
			super.updateDB("UPDATE product SET quantity="+q+" WHERE id="+id +";");
		}
		// clean basket
    	Basket.products = new ArrayList<Product>();
		
		// search generated OrderNumber and print message successful
		super.setContent("SELECT * FROM orders;");
		String oID = "";
		try {
			content.last();
			oID = content.getString("id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		super.notify("Successfully!", "New Order placed! Your Order Number:" + oID);
		
		return "mainpage";
	}
	
	public void setSearchOrder(String s){
		searchOrder = s;
	}
	
	public  String getSearchOrder(){
		return searchOrder;
	}
	
	public void setSearchedOrder(Order o){
		searchedOrder = o;
	}
	
	public Order getSearchedOrder(){
		return searchedOrder;
	}
	
	public void searchOrder(){
		if (searchOrder.isEmpty()) searchedOrder = null;
		else if (!isInteger(searchOrder,10) ) {
			super.notify("Error", "Just Integers are allowed as Order number");
			searchedOrder = null;
		}
		else {
			setContent("SELECT * FROM orders WHERE id="+searchOrder+";");
			ArrayList<Order> orders = new ArrayList<Order>();
			try {
				orders = toOrders(content);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (orders.isEmpty()) super.notify("Unfortunatelly!", "Order not found");
			else searchedOrder = orders.get(0);
		}
	}
	
	
	// Helpmethod to identify if a String is just out of numbers
	private static boolean isInteger(String s, int radix) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),radix) < 0) return false;
	    }
	    return true;
	}

	
	
	
}
