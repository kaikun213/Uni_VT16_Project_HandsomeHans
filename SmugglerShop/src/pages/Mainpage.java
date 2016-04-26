/**
 * 
 */
package pages;

import java.io.Serializable;
import java.sql.ResultSet;
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
public class Mainpage extends Page implements Serializable {
	
	private List<Product> products = new ArrayList<Product>();
	private String category = "";
	
	/**
	 * Default serialVersionID generated from eclipse
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * sets the content to all available products from the web-shop
	 * creates objects for all products in content and puts them into the <products> list.
	 */
	public void setProducts(String s) {
		try {
			if (s.isEmpty()) setContent("select * from webshopDB.product");
			else setContent("select * from webshopDB.product WHERE category="+s);
			products = toProducts(content);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets the content and the product list.
	 * @return the list of products in the dataTease. 
	 */
	public List<Product> getProducts(){
		String id = "";
		if (category.isEmpty()) setProducts("");
		else {
			ResultSet rs = conn.fetch("SELECT * FROM webshopDB.category WHERE name=\""+category+"\";");
			try {
				rs.first();
				id = rs.getString("id");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			setProducts(id);
		}
		// Reset category to show all products by default again!
		category = "";
		return products;
	}	

	/**
	 * returns a List with all the category names
	 */
	@Override
	public List<String> getCategories(){
		List<String> categories = new ArrayList<String>();
		try {
			categories = super.getCategories();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}
	
	
	public void setCategory(String s){
		category = s;
	}
	
	public String getCategory(){
		return category;
	}
	
	
}
