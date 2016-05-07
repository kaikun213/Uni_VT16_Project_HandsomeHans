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
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import baseClasses.Order.OrderStatus;
import baseClasses.Order;
import baseClasses.Page;
import baseClasses.Product;
import baseClasses.ProductList;
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
	
	private List<Order> orders = new ArrayList<Order>();
	private List<OrderStatus> status = new ArrayList<OrderStatus>();
	private Order nOrder =  new Order();
	private String searchOrder = "";
	
	private List<Product> products = new ArrayList<Product>();
	
    @ManagedProperty("#{productList}")
	private ProductList productService = new ProductList();
	
	
	private List<String> category;
	private String selectedCat;
	private Product prod;
	
	private static final long serialVersionUID = 1L;
	
	public void init() {
		setOrders();
		setStatus();
		products = productService.getProducts();
		System.out.println(products.size());
	}
	
	public void adminAddProduct(){
		insertDB(prod);
		
	}
	public void adminUpdateProduct(){
		updateDB(prod);

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
	
	/* ******************************* admin Products **************************************** */

	public void adminClearInputs(){
		Product tempProd = new Product();
		prod = tempProd;
	}
	
	
	public Product getProd() {
		return prod;
	}

	public void setProd(Product prod) {
		this.prod = prod;
	}	
	
	public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Product Selected", Integer.toString(((Product) event.getObject()).getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Product Unselected", Integer.toString(((Product) event.getObject()).getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	// Set & Get Methods
	
	public List<Order> getOrders(){
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
	
	public Order getNewOrder(){
		return nOrder;
	}
	
	public void setNewOrder(Order o){
		nOrder = o;
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
	
	public String getSearchOrder(){
		return searchOrder;
	}
	
	public void setSearchOrder(String s){
		searchOrder = s;
	}
	
	/* *************************************** Admin Order Methods ******************************* */
	
	public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Order Edited", "Selected Order: " + Integer.toString(((Order) event.getObject()).getOrderId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
        System.out.println(((Order) event.getObject()).getOrderDate());
        super.updateDB(((Order) event.getObject()));
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", "Selected Order: " +Integer.toString(((Order) event.getObject()).getOrderId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void addNewOrder(){
    	System.out.println("New Order: Date:" + nOrder.getOrderDate() + " , Status:" + nOrder.getOrderStatus());
    	super.insertDB(nOrder);
    }
    
    public void searchOrder(){
    	
    }
    
    /* ************************************** Methods for SelectProductsMenu *************************************************** */
    public void setProductList(ProductList service) {
        this.productService = service;
    }
    
    public List<Product> getProducts(){
    	return products;
    }

	
}
