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
 * View for watching a selected item from the shop
 * Henry and Ben 22-04-16
 */

@Named("productView")
@SessionScoped
public class ProductView extends Page implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// Array with hopefully 1 product
	private List<Product> products = new ArrayList<Product>();
	private int id;
	
	public void init() {
		setProduct(id);
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public int getID(){
		return id;
	}
	
	/**
	 * Sets the productView product array to input product ID. Array will be size of 1, if the product exists 
	 * @param product ID you want to fetch
	 */
	public void setProduct(int id) {
		try {
			setContent("SELECT * FROM product WHERE id="+id);
			products = toProducts(content);
			products.get(0).setQuantity(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates a product array with ID wanting to fetch, return product at index 0
	 * @param product ID you want to fetch
	 * @return product that fits ID, otherwise dummy
	 */
	public Product getOneProduct(int id){
		
		//Dummy class if enter illegal ID
		if(products.size()==0){
			Product prod = new Product();			
			return prod;
		}
		return products.get(0);
	}	
	/**
	 * returns the total quantity for the product defined by the ID (Stock in the database)
	 */
	@Override
    public int getQuantity(String productID){
		if (productID.isEmpty()) return 0;
    	return super.getQuantity(productID);
    }
	
	public void notifyQuantity(){
		if (Basket.productInBasket(Integer.toString(id))) {
			if (Basket.productFromBasket(Integer.toString(id)).getQuantity() > getQuantity(Integer.toString(products.get(0).getId()))) {
				Basket.productFromBasket(Integer.toString(id)).setQuantity(getQuantity(Integer.toString(products.get(0).getId())));
	    		super.notify("Error","Sorry, we do not have this amount on stock. The quantity got reset to the maximum.");
			}
		}
		else if (products.get(0).getQuantity() > getQuantity(Integer.toString(products.get(0).getId()))) {
    		products.get(0).setQuantity(getQuantity(Integer.toString(products.get(0).getId())));
    		super.notify("Error","Sorry, we do not have this amount on stock. The quantity got reset to the maximum.");
    	}
	}
	
}
