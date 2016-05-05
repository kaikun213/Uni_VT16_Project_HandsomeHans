/**
 * 
 */
package baseClasses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import baseClasses.Order;
import baseClasses.Order.OrderStatus;



/**
 * @author kaikun
 * Abstract page class 
 */

/* untested toOrder() method */
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
		//null ResultSet
		if (products == null) return new ArrayList<Product>();
		//ResultSet from wrong type
		if (!products.getMetaData().getTableName(1).equals("product")) throw new SQLException("This is not a product list");
		ArrayList<Product> arr = new ArrayList<Product>();
		while (products.next()) {
			Product p = new Product(products.getString("name"),
					products.getString("category"),
					products.getDouble("price"),
					products.getString("description"),
					products.getString("image"),
					products.getInt("quantity"),
					products.getInt("id"));
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
		//null ResultSet
		if (orders == null) return new ArrayList<Order>();
		//ResultSet from wrong type
		if (!orders.getMetaData().getTableName(1).equals("orders")) throw new SQLException("This is not a order list");
		ArrayList<Order> arr = new ArrayList<Order>();
		try {
			while (orders.next()) {
				// get the products from the database by the IDs 
				StringBuilder sb = new StringBuilder(orders.getString("products"));
				StringBuilder sqlProducts = new StringBuilder("SELECT * FROM product WHERE ");
				int a = 0;
				for (int i=0;i<sb.length();i++) {
					if (Character.compare(sb.charAt(i), ':') == 0) sqlProducts.append("id="+ sb.substring(a, i) +"");
					if (Character.compare(sb.charAt(i), ';') == 0) {
						a=i+1;
						if (i+1<sb.length()) sqlProducts.append(" OR ");
					}
				}
				sqlProducts.append(";");
				ResultSet products = conn.fetch(sqlProducts.toString());
				ArrayList<Product> productList = toProducts(products);
				
				// change quantities from ordered productList (because products taken by ID from the database)
				a = 0;
				int nr = 0;
				for (int i=0;i<sb.length();i++) {
					if (Character.compare(sb.charAt(i), ':') == 0) a=i;
					if (Character.compare(sb.charAt(i), ';') == 0) productList.get(nr++).setQuantity(Integer.parseInt(sb.substring(a+1, i)));
				}
				
				OrderStatus status;
				switch (orders.getInt("orderStatus")) {
				case 1 : status = OrderStatus.IN_PROCESS;
						break;
				case 2 : status = OrderStatus.SHIPPED;
						break;
				case 3 : status = OrderStatus.DELAYED;
						break;
				default : status = OrderStatus.IN_PROCESS;
						break;
				}
								
			    Order o = new Order(orders.getInt("id"),
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
	
	
	/** converts a ResultSet into a List of users
	 * 
	 * @param users
	 * @return ArrayList of user
	 * @throws SQLException when it is not a ResultSet of the user table
	 */
	protected ArrayList<User> toUsers(ResultSet users) throws SQLException{
		//null ResultSet
		if (users == null) return new ArrayList<User>();
		//ResultSet from wrong type
		if (!users.getMetaData().getTableName(1).equals("user")) throw new SQLException("This is not a user list");
		ArrayList<User> arr = new ArrayList<User>();
		try {
			while (users.next()) {
				ArrayList<Order> orders = new ArrayList<Order>();
				
				StringBuilder sqlOrders = new StringBuilder("SELECT * FROM orders WHERE id=");
				StringBuilder sb = new StringBuilder(users.getString("orders"));
				int a = 0;
				for (int i=0;i<sb.length();i++) {
					if (Character.compare(sb.charAt(i), ';') == 0) {
					sqlOrders.append(sb.substring(a, i) + " OR id=");
					a=i+1;
					}
					orders.addAll(toOrders(conn.fetch(sqlOrders.toString())));
				}
				
			    User u = new User(	users.getInt("id"),
			    					users.getString("name"),
			    					orders,
			    					users.getString("email"),
			    					users.getString("password"),
			    					users.getBoolean("admin"));
			  arr.add(u);    
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}
	
	/** Method to get a List of the category names
	 * 
	 * @return all categories from the database
	 * @throws SQLException if the ResultSet is null
	 */
	protected List<String> getCategories() throws SQLException{
		ArrayList<String> categories = new ArrayList<String>();
		ResultSet rs = conn.fetch("SELECT * FROM category");
		while(rs.next()) categories.add(rs.getString("name"));
		return categories;
	}
	/** Method for a recent quantity check
	 * 
	 * @param id from the product you want to check the quantity
	 * @return quantity as integer
	 */
	protected int getQuantity(String id){
		ResultSet rs = conn.fetch("SELECT product.quantity from product WHERE id="+id);
		try {
			rs.first();
			return rs.getInt("quantity");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 *  Gets an update-able ResultSet for inserting new entities or updating old ones
	 */
	protected void updateDB(String s){
		conn.update(s);
	}
	
	/** Updates the given Object in the DB, if it doesnt exist it inserts a new one
	 * 
	 * @param o an object from type User, Product or Order which should be updated or inserted into the DB
	 * 
	 */
	protected void toSQL(Object o){
		StringBuilder sb = new StringBuilder();
		if (o instanceof Product) {
			updateDB("DELETE FROM product WHERE id="+ ((Product) o).getId() + ";");
			sb.append(" INSERT INTO  product (name,category,price,image,description,quantity) VALUES (\"");
			sb.append(((Product) o).getName() + "\",\"");
			sb.append(((Product) o).getCategory() +"\",");
			sb.append(((Product) o).getPrice() +",\"");
			sb.append(((Product) o).getImage() +"\",\"");
			sb.append(((Product) o).getDescription() + "\",");
			sb.append(((Product) o).getQuantity());
			sb.append(");");
		}
		else if (o instanceof Order){
			updateDB("DELETE FROM orders WHERE id="+ ((Order) o).getOrderId() + ";");
			sb.append("INSERT INTO orders (orderStatus, products, price, date) VALUES (");
			// orderStatus
			switch (((Order) o).getOrderStatus()) {
			case IN_PROCESS : sb.append("1,");
					break;
			case SHIPPED : sb.append("2,");
					break;
			case DELAYED : sb.append("3,");
					break;
			default : sb.append("1,");
					break;
			}
			// products 
			sb.append("\"");
			for (int i=0;i<((Order) o).getOrderList().size();i++) {
				Product p = ((Order) o).getOrderList().get(i);
				sb.append(p.getId() + ":" + p.getQuantity() + ";");
			}
			sb.append("\",");

			// price
			int p = 0;
			for (int i=0;i<((Order) o).getOrderList().size();i++) p+= ((Order) o).getOrderList().get(i).getPrice() * ((Order) o).getOrderList().get(i).getQuantity();
			sb.append(p+",");
			// date
			sb.append("\"");
			sb.append(((Order) o).getOrderDate());
			sb.append("\"");
			// finish request
			sb.append(");");
		}
		else if (o instanceof User){
			updateDB("DELETE FROM user WHERE id=" + ((User) o).getId() +";");
			sb.append("INSERT INTO user (name,orders,email,password,admin) VALUES (\"");
			sb.append(((User) o).getName() + "\",\"");
			for (int i=0;i<((User) o).getOrders().size();i++){
				sb.append( ((User) o).getOrders().get(i).getOrderId() + ";");
			}
			sb.append("\",\"");
			sb.append(((User) o).getEmail() + "\",\"");
			sb.append(((User) o).getPassword() + "\",");
			if (((User) o).getAdmin()) sb.append("1);");
			else sb.append("0);");
		}
		else System.err.println("This is not an updateable Object");
		updateDB(sb.toString());
	}
	
	/** Gives out an notification on a page with the given Strings. (Needs p:growl on the xhtml page)
	 * 
	 * @param s Headline
	 * @param s1 Text
	 */
	public void notify(String s, String s1) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(s, s1) );
    }
	

}

