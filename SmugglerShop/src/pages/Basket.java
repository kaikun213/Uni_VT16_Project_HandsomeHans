package pages;

import java.io.Serializable;
import java.sql.SQLException;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import baseClasses.Page;
import baseClasses.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
public class Basket extends Page implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static public List<Product> products = new ArrayList<Product>();

    /**
     *
     * @return List<Products>
     */
    public List<Product> getBasket(){
        return products;
    }

    /** Edit the quantity from the equal product in the basket
     *
     ** @param  Product updated
     */
    public void editQuantity(Product old, int quantity){
        int place = products.indexOf(old);
        products.get(place).setQuantity(quantity);
    }
    
    /**
     * Edit the quantity from the product given by the unique product ID
     * @param id
     * @param quantity
     */
    public void editQuantity(int id, int quantity){
    	for (int i=0;i<products.size();i++) if (products.get(i).getId() == id) products.get(i).setQuantity(quantity);
    }

    /** Remove an Item from the basket by ID
     * 
     * @param id
     */
    public void remove(int id){
    	for (int i=0;i<products.size();i++) if (products.get(i).getId() == id) products.remove(i);
    }
    
    /** Remove an Item from the basket, by giving the whole product to remove
     * @param selected
     *
     */
    public void remove(Product old){
        int place = products.indexOf(old);
        products.remove(place);
    }
    /**
     * Remove an Item and get the Item ID from an f:param by clicking the commandButton
     */
    public void remove(){
    	FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
    	String productID = params.get("pID");
    	for (int i=0;i<products.size();i++) if (products.get(i).getId() == Integer.parseInt(productID)) products.remove(i);
    }

    /** Add an Item to the basket, by just passing the individual item ID
     *
     * @param Product item
     */
    public void add(int productID){
    	// if product already exists in basket increase quantity
    	for (int i=0;i<products.size();i++) if (products.get(i).getId() == productID) {
    		products.get(i).setQuantity(products.get(i).getQuantity()+1);
    		return;
    	}
    	// otherwise add it to basket
		try {
	    	setContent("SELECT * FROM webshopDB.product WHERE id="+productID);
	    	ArrayList<Product> oneProduct = toProducts(content);
	    	oneProduct.get(0).setQuantity(1);
	    	products.add(oneProduct.get(0));
	    	
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    /**Add many items, by just calling the normal add function many times
     * Ben
     * @param Product item
     * @param Product item amount
     */
    public void addMany(int productID, int amount){
    	
    	for (int i=0; i<amount;i++){
    		add(productID);
    	}
    }
    
    
    /** Add item to the basket, by passing the whole new product
     * 
     * @param item
     */
    public void add(Product item){
    	for (int i=0;i<products.size();i++) if (products.get(i).getId() == item.getId()) {
    		products.get(i).setQuantity(products.get(i).getQuantity()+1);
    		return;
    	}
    	products.add(item);
    }
    /**
     * Adding an item to the basket with an f:param which will be read. E.g. a CommandButton
     */
    public void add(){
    	FacesContext fc = FacesContext.getCurrentInstance();
    	Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	  String productID = params.get("pID");
	  try {
	    	setContent("SELECT * FROM webshopDB.product WHERE id="+productID);
	    	ArrayList<Product> oneProduct = toProducts(content);
	    	if (oneProduct.size() > 0) products.add(oneProduct.get(0));
	    	else System.out.println("It is not working yet, product ID:" + productID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public int getTotalPrice(){
    	int result = 0;
    	Iterator<Product> it = products.iterator();
    	while (it.hasNext()) {
    		Product p = it.next();
    		result+=p.getPrice() * p.getQuantity();
    	}
    	return result;
    }
    
    public int size(){
    	return products.size();
    }
    
}
