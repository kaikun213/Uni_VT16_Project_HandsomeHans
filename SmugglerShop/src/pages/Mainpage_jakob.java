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
public class Mainpage_jakob extends Page implements Serializable {
	
	private List<Product> products = new ArrayList<Product>();
	
	/**
	 * Default serialVersionID generated from eclipse
	 */
	private static final long serialVersionUID = 1L;

	public void setProducts(String sql) {
		setContent(sql);
		try {
			products = toProducts(content);
			conn.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Product> getProducts(){
		setProducts("select * from webshopDB.product");
		products.add(new Product("test", "test", 123, "test", null, 300));
		return products;
	}	
}
