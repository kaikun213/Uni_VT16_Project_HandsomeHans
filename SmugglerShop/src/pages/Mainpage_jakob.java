/**
 * 
 */
package pages;

import java.io.Serializable;
import java.sql.ResultSet;
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

	/**
	 * Default serialVersionID generated from eclipse
	 */
	private static final long serialVersionUID = 1L;

	public void setContent(String sql) {
		content = conn.fetch(sql);
	}
	
	public ResultSet getContent(){
		setContent("SELECT * FROM webshopDB.product;");
		return content;
	}
	
	public List<Product> getProducts(){
		List<Product> arr = new ArrayList<Product>();
		Product test = new Product();
		test.setProductName("test");
		test.setDescription("Describe the product");
		arr.add(test);
		
		Product test1 = new Product();
		test1.setProductName("test");
		test1.setDescription("Describe the product");
		arr.add(test1);
		
		
		Product test2 = new Product();
		test2.setProductName("test");
		test2.setDescription("Describe the product");
		arr.add(test2);
		
		Product test3 = new Product();
		test3.setProductName("test");
		test3.setDescription("Describe the product");
		arr.add(test3);
		
		return arr;
	}

}
