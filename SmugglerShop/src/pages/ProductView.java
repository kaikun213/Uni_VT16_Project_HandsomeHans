package pages;

import javax.inject.Named;

import baseClasses.ConnectionClass;
import baseClasses.Product;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;

@Named("productView")
@SessionScoped
public class ProductView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	public Product magic(int id){
		String command = "SELECT * FROM webshopDB.product WHERE id="+id;
		ConnectionClass conn = new ConnectionClass();
		ResultSet rs = conn.fetch(command);
		
		return new Product(rs);
	}

}
