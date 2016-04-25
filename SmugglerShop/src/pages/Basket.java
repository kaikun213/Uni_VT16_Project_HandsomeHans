package pages;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import baseClasses.Page;
import baseClasses.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    /**
     *
     ** @param  Product updated
     */

    public void editQuantity(Product old, int quantity){
        int place = products.indexOf(old);
        products.get(place).setQuantity(quantity);
    }

    /**
     * @param selected
     *
     */

    public void remove(Product old){
        int place = products.indexOf(old);
        products.remove(place);
    }

    /**
     *
     * @param Product item
     */
    public void add(Product item){
        products.add(item);
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
