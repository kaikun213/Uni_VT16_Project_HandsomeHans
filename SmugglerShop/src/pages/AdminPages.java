/**
 * 
 */
package pages;

import java.io.Serializable;


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
public class AdminPages extends Page implements Serializable {
	
	/**
	 * Default serialVersionID generated from eclipse
	 */
	private static final long serialVersionUID = 1L;
	
	public void test(){
		Product p = new Product("test","1", 123, "", "description", 100, 10);
		String s = super.toSQL(p);
		super.updateDB(s);
	}	
	
	
}
