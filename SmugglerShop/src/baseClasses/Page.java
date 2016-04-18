/**
 * 
 */
package baseClasses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import baseClasses.Order;



/**
 * @author kaikun
 * Abstract page class 
 */

/* unfinished ! */
public abstract class Page {
	
	/**
	 * Establishes a connection at creation
	 */
	protected ConnectionClass conn = new ConnectionClass();
	protected ResultSet content;
	
	/**
	 * 
	 * @param sql query to the database e.g. "SELECT * FROM product;" 
	 * @return the ResultSet of the requested rows, sets the content of the page
	 */
	protected void setContent(String sql) {
		content = conn.fetch(sql);
	}

	/**	converts a ResultSet into a List of products.
	 * 
	 * @param products give a certain content of products
	 * @return gives back an ArrayList<Product> from the given ResultSet.
	 * @throws SQLException when it is now a ResultSet from ordinary products
	 */
	protected ArrayList<Product> toProducts(ResultSet products) throws SQLException{
		if (!products.getMetaData().getTableName(1).equals("product")) throw new SQLException("This is not a product list");
		ArrayList<Product> arr = new ArrayList<Product>();
	while (products.next()) {
			Product p = new Product(products.getString("name"),
					products.getString("category"),
					products.getDouble("price"),
					products.getString("description"),
					null,
					products.getInt("quantity"));
			arr.add(p);
		}
		return arr;
	}
	
	/** converts a ResultSet into a List of orders.
	 * 
	 * @param orders 
	 * @return an Arraylist of Order
	 * @throws SQLException when it is not a ResultSet of the order table
	 */
	protected ArrayList<Order> toOrders(ResultSet orders) throws SQLException{
		if (!orders.getMetaData().getTableName(1).equals("order")) throw new SQLException("This is not a order list");
		ArrayList<Order> arr = new ArrayList<Order>();
		try {
			while (orders.next()) {
				// get the products from the database by the IDs 
				
				ResultSet products = conn.fetch("SELECT * FROM product WHERE " +
												"id=" + orders.getString("products") );
				ArrayList<Product> productList = toProducts(products);
				
				OrderStatus status;
				switch (orders.getInt("orderStatus")) {
				case 1 : status = OrderStatus.IN_PROCESS;
						break;
				case 2 : status = OrderStatus.SHIPPED;
						break;
				case 3 : status = OrderStatus.DELIVERED;
						break;
				default : status = OrderStatus.IN_PROCESS;
						break;
				}
								
			    Order o = new Order(orders.getLong("orderId"),
			    					productList,
			    					orders.getString("date"),
			    					status);
			  
			  arr.add(o);    
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}
	
	
	/** converts a ResultSet into a List of admins.
	 * 
	 * @param admins
	 * @return ArrayList of Admin
	 * @throws SQLException when it is not a ResultSet of the admin table
	 */
	protected ArrayList<Admin> toAdmins(ResultSet admins) throws SQLException{
		if (!admins.getMetaData().getTableName(1).equals("admin")) throw new SQLException("This is not a admin list");
		ArrayList<Admin> arr = new ArrayList<Admin>();
		try {
			while (admins.next()) {
			    Admin a = new Admin(admins.getString("name"),
			    					admins.getString("email"),
			    					admins.getString("password"));
			  arr.add(a);    
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}
	

}

