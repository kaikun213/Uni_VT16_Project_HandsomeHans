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
	
	
	/* ******************************* admin Products **************************************** */

	public void adminClearInputs(){
		Product tempProd = new Product();
		prod = tempProd;
	}
	
	public void adminDeleteProduct(){
		deleteDB(prod);
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
    	init();
    	super.notify("Successful added.", "Order Number: " + orders.get(orders.size()-1).getOrderId());
    }
    
    public void searchOrder(){
		setContent("SELECT * FROM orders WHERE id="+ searchOrder+";");
		try {
			orders = toOrders(content);
		} catch (SQLException e) {
			e.printStackTrace();
		}

    }
    
    public void deleteOrder(Order o){
    	super.deleteDB(o);
    	init();
    }
    
    /* ************************************** Methods for SelectProductsMenu *************************************************** */
    public void setProductList(ProductList service) {
        this.productService = service;
    }
    
    public List<Product> getProducts(){
    	return products;
    }

	
}
