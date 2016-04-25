package pages;

import javax.inject.Named;

import baseClasses.Product;
import baseClasses.Page;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
/**
 * Henry and Ben 22-04-16
 */

@Named("productView")
@SessionScoped
public class ProductView extends Page implements Serializable {

	private static final long serialVersionUID = 1L;
	private Product prod;
	private int pId;
	
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
	public int getId(){
		return pId;
	}
	/*
	 * Get int value ID from xhtml and fetch the product 
	 */
	public Product getOneProduct(int id){
		setProduct(id);
		
		//Dummy class if enter illegal ID
		if(products.size()==0){
			Product prodTemp = new Product();
			prod = prodTemp;
			return prod;
		}
	
		prod = products.get(0);
		pId = id;
		return prod;
	}
	private String message = "messagages";
	
    public String getMessage() {
    	
        return message;
    }
	
	public void addToBasket(AjaxBehaviorEvent event){
		message = "productview id:"+pId+" prod id: "+prod.getId() +" new id:";
		//pId = prod.getId();
		Basket bas = new Basket();
		bas.add(pId);
	}


	
}
