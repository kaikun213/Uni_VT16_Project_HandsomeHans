package pages;

import javax.inject.Named;

import baseClasses.Product;
import baseClasses.Page;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
/**
 * Henry and Ben 22-04-16
 */

@Named("productView")
@SessionScoped
public class ProductView extends Page implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// Array with hopefully 1 product
	private List<Product> products = new ArrayList<Product>();
	
	/*
	 * Command line to fetch the product
	 * "setContent" and "content" are from page class
	 */
	public void setProduct(int id) {
		try {
			setContent("SELECT * FROM webshopDB.product WHERE id="+id);
			products = toProducts(content);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Get int value ID from xhtml and fetch the product 
	 */
	public Product getOneProduct(int id){
		setProduct(id);
		
		//Dummy class if enter illegal ID
		if(products.size()==0){
			Product prod = new Product();			
			return prod;
		}
		return products.get(0);
	}	
	
}
