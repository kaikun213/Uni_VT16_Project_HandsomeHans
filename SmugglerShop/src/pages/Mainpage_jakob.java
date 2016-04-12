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
	
	private List<Product> allProducts = new ArrayList<Product>();
	
	/**
	 * Default serialVersionID generated from eclipse
	 */
	private static final long serialVersionUID = 1L;

	public void setAllProducts(String sql) {
		setContent(sql);
		try {
			allProducts = toProducts(content);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Product> getAllProducts(){
		setAllProducts("select * from webshopDB.product");
		return allProducts;
	}	
}
