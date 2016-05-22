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
	private Map<Integer,Boolean> prodChecked = new HashMap<Integer,Boolean>();

	
    @ManagedProperty("#{productList}")
	private ProductList productService = new ProductList();
	
	
	private List<String> category;
	private String selectedCat;
	private Product prod;
	private Map<Integer,Boolean> checkedProducts = new HashMap<Integer,Boolean>();
	
	// for quantities in adminOrderPage
	private Map<Integer,Integer> quantities = new HashMap<Integer,Integer>();

	
	private static final long serialVersionUID = 1L;
	
	public void test(Integer id){
		System.out.println("ID: " + id);
		System.out.println("Invoked Map Quantity:" + quantities.get(id));
	}
	
	public void init() {
		setOrders();
		for (Order o : orders) checked.put(o.getOrderId(), false);
		for (Product p : products) quantities.put(p.getId(), 1);
		setStatus();
		products = productService.getProducts();
		for (Product p : products) checkedProducts.put(p.getId(), false);
	}
	
	/* ******************************* admin Products **************************************** */
	
	public void adminAddProduct(){
		prod = new Product();
		products = productService.getProducts();
	}
	public void adminSaveProduct(){
		
		for(int i = 0; i<products.size();i++){
			if(prod.getId()==products.get(i).getId()){
				updateDB(prod);
			}
				else if(i==products.size()-1){
					insertDB(prod);
			}
		}
		
		products = productService.getProducts();
		prod = new Product();
	}
	
	public void adminClearInputs(){
		prod = new Product();
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
		adminClearInputs();
	}
	
	public void adminDeleteProduct(String id){
		for (Order o : orders) {
			for (Product p : o.getOrderList()) {
				if (p.getId() == Integer.parseInt(id)) {
					super.notify("Error", "You can not delete a product which is currently ordered. This is included in the order: " + o.getOrderId());
					return;
				}
			}
		}		
		conn.fetch("DELETE FROM product WHERE id="+id);
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
	
	
	
	/* *************************************** Admin Order Methods ******************************* */
	
	public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Order Edited", "Selected Order: " + Integer.toString(((Order) event.getObject()).getOrderId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
        super.updateDB(((Order) event.getObject()));
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", "Selected Order: " +Integer.toString(((Order) event.getObject()).getOrderId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    
    public void addNewOrder(){
    	for (int i=0;i<nOrder.getOrderList().size();i++) {
    		if (quantities.containsKey(nOrder.getOrderList().get(i).getId())) {
    			nOrder.getOrderList().get(i).setQuantity(quantities.get(nOrder.getOrderList().get(i).getId()));
    			System.out.println("ID: " + nOrder.getOrderList().get(i).getId());
    			System.out.println("Quantities map: " + quantities.get(nOrder.getOrderList().get(i).getId()));
            	System.out.println("Quantities produt: " + nOrder.getOrderList().get(i).getQuantity());
    		}
    	}
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
	
	public Map<Integer,Integer> getQuantities(){
		return quantities;
	}

	
}
