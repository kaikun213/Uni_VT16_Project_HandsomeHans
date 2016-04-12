/**
 * 
 */
package baseClasses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
		// if (!products.getMetaData().getColumnName(2).equals("category")) throw new SQLException("This is not a product List");
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
	
	/*
	protected ArrayList<Order> toOrders(ResultSet orders){
		ArrayList<Product> arr = new ArrayList<Product>();
		try {
			while (orders.next()) {
			    Order o = new Order(orders.getLong("orderId"),
			    					orders.getString("products"),
			    					orders.get);
			  arr.add(o);    
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}
	*/
	/*
	protected ArrayList<Admin> toAdmins(ResultSet admins){
		ArrayList<Product> arr = new ArrayList<Product>();
		try {
			while (products.next()) {
			    Product p = new Product(products.getString("name"),
			    						products.getString("category"),
			    						products.getDouble("price"),
			    						products.getString("description"),
			    						null,
			    						products.getInt("quantity"));
			  arr.add(p);    
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}
	*/

}

