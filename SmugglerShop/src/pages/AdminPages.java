/**
 * 
 */
package pages;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	// for change many states at once (checkboxes)
	private Map<Integer,Boolean> checked = new HashMap<Integer,Boolean>();
	private OrderStatus state = OrderStatus.IN_PROCESS;
	
	private List<Product> products = new ArrayList<Product>();
	private List<Product> selectedProducts = new ArrayList<Product>(); 
	private Map<Integer,Boolean> prodChecked = new HashMap<Integer,Boolean>();

	
    @ManagedProperty("#{productList}")
	private ProductList productService = new ProductList();
	
	
	private List<String> category;
	private String selectedCat;
	private Product prod;
	private Map<Integer,Boolean> checkedProducts = new HashMap<Integer,Boolean>();

	
	private static final long serialVersionUID = 1L;
	
	public void init() {
		setOrders();
		for (Order o : orders) checked.put(o.getOrderId(), false);
		for (Product p : products) checkedProducts.put(p.getId(), false);
		setStatus();
		products = productService.getProducts();
	}
	
	/* ******************************* admin Products **************************************** */
	
	 public void adminMultiSelect(){ 
		    prod = selectedProducts.get(0); 
		     
	 } 
	
	public void adminAddProduct(){
		insertDB(prod);		
		products = productService.getProducts();
	}
	public void adminUpdateProduct(){
		System.out.println(prod.getQuantity());
		updateDB(prod);
		products = productService.getProducts();
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
	
	public void adminClearInputs(){
		Product tempProd = new Product();
		prod = tempProd;
	}
	
	public void adminDeleteProduct(){
		for (Order o : orders) {
			for (Product p : o.getOrderList()) {
				if (p.getId() == prod.getId()) {
					super.notify("Error", "You can not delete a product which is currently ordered. This is included in the order: " + o.getOrderId());
					return;
				}
			}
		}
		deleteDB(prod);
		products = productService.getProducts();
	}
	
	public void adminDeleteProducts(){
		for (Product p : products) {
	    	if (checkedProducts.containsKey(p.getId())) {
	    		System.out.println("Checks orderboxes selected ...");
	        if (checkedProducts.get(p.getId())) {
	        	// try to delete product
	        	for (Order o : orders) {
	    			for (Product p1 : o.getOrderList()) {
	    				if (p1.getId() == prod.getId()) {
	    					super.notify("Error", "You can not delete a product which is currently ordered. This is included in the order: " + o.getOrderId());
	    					return;
	    				}
	    			}
	    		}
	    		deleteDB(prod);
	    		products = productService.getProducts();
	        }
	    	}
	    }
	    checked.clear();
		
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
    	if (ContactPage.isInteger(searchOrder, 10)) {
    		setContent("SELECT * FROM orders WHERE id="+ searchOrder+";");
    		try {
    			orders = toOrders(content);
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    	else super.notify("Error", "The Order ID needs to be an Integer");
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

	public List<Product> getSelectedProducts() {
		return selectedProducts;
	}

	public void setSelectedProducts(List<Product> selectedProducts) {
		this.selectedProducts = selectedProducts;
	}

	public Map<Integer,Boolean> getChecked() {
		return checked;
	}
	
	public void setChecked(Map<Integer,Boolean> checked){
		this.checked = checked;
	}

	public OrderStatus getState() {
		return state;
	}

	public void setState(OrderStatus state) {
		this.state = state;
	}
	
	public void changeStates(){
	    for (Order o : orders) {
	    	if (checked.containsKey(o.getOrderId())) {
	    		System.out.println("Checks orderboxes selected ...");
	        if (checked.get(o.getOrderId())) {
	    		System.out.println("change state of order: " + o.getOrderId());
	            o.setOrderStatus(state);
	            updateDB(o);
	        }
	    	}
	    }
	    checked.clear();
	}

	public Map<Integer,Boolean> getProdChecked() {
		return prodChecked;
	}

	public void setProdChecked(Map<Integer,Boolean> prodChecked) {
		this.prodChecked = prodChecked;
	}

	public Map<Integer,Boolean> getCheckedProducts() {
		return checkedProducts;
	}

	public void setCheckedProducts(Map<Integer,Boolean> checkedProducts) {
		this.checkedProducts = checkedProducts;
	}


	
}
