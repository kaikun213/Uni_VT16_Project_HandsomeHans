/**
 * 
 */
package pages;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import baseClasses.Order.OrderStatus;
import baseClasses.Order;
import baseClasses.Page;
import baseClasses.Product;
import baseClasses.User;

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
	
	private List<Order> orders;
	private List<OrderStatus> status = new ArrayList<OrderStatus>();
	private Order selectedOrder;
	private List<String> category;
	private String selectedCat;
	private Product prod;
	
	private static final long serialVersionUID = 1L;
	
	public void adminAddProduct(){
		insertDB(prod);
		Product temp = new Product();
		prod = temp;
	}
	
	public List<String> adminSetCategories(){
		try {
			category = super.getCategories();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return category;
	}
	
	public void test(){
		Product p = new Product("test","1", 123, "", "description", 100, 10);
		ArrayList<Product> arr = new ArrayList<Product>();
		arr.add(p);
		Order o = new Order(100,arr,"date",OrderStatus.IN_PROCESS);
		ArrayList<Order> arr2 = new ArrayList<Order>();
		arr2.add(o);
		User u = new User(-99,"test",arr2,"Testemail", "password", false);
		insertDB(u);
		insertDB(p);
		insertDB(o);
		updateDB(u);
		updateDB(p);
		updateDB(o);
	}

	public Product getProd() {
		return prod;
	}

	public void setProd(Product prod) {
		this.prod = prod;
	}	
	
	// Set & Get Methods
	public List<Order> getOrders(){
		setOrders();
		return orders;
	}
	
	public void setOrders(){
		setContent("SELECT * FROM orders;");
		try {
			orders = toOrders(content);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Order getSelectedOrder(){
		return selectedOrder;
	}
	
	public void setSelectedOrder(Order o){
		selectedOrder = o;
	}

	public List<String> getCategory() {
		return category;
	}

	public void setCategory(List<String> category) {
		this.category = category;
	}

	public String getSelectedCat() {
		return selectedCat;
	}

	public void setSelectedCat(String selectedCat) {
		this.selectedCat = selectedCat;
	}
	
	public List<OrderStatus> getStatus(){
		setStatus();
		return status;
	}
	
	public void setStatus(){
		if (status.size() > 0) return;
		status.add(OrderStatus.IN_PROCESS);
		status.add(OrderStatus.DELAYED);
		status.add(OrderStatus.SHIPPED);
	}
	
	public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Order Edited", "Selected Order: " + Integer.toString(((Order) event.getObject()).getOrderId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
        super.insertDB(((Order) event.getObject()));
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", "Selected Order: " +Integer.toString(((Order) event.getObject()).getOrderId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
}
