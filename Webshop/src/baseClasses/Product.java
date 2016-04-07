package baseClasses;

import javafx.scene.image.Image;

/**
 * This class represent a product.
 * 
 * @author SarpreetSingh
 *
 */
public class Product {

	// Fields
	private String productName;
	private String category;
	private double price;
	private String description;
	private Image image;
	private int quantity;

	/**
	 * Empty Constructor
	 */
	public Product() {
		productName = "";
		category = "";
		price = 0;
		description = "";
		image = null;
		quantity = 0;
	}

	/**
	 * Constructor With Parameters
	 * 
	 * @param productName
	 * @param category
	 * @param price
	 * @param quantity
	 */
	public Product(String productName, String category, double price, int quantity) {
		this.productName = productName;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
	}

	// Below are Getters and Setters

	/**
	 * This method returns product name
	 * 
	 * @return String
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * Set product name
	 * 
	 * @param productName
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * This method returns product category
	 * 
	 * @return String
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Set product category
	 * 
	 * @param category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * This method returns product price
	 * 
	 * @return double
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Set product price, throw error if price is less than or equal to zero
	 * 
	 * @param price
	 */
	public void setPrice(double price) {
		if (price <= 0.0)
			throw new IllegalArgumentException(
					"Please check the product price. Product name: " + this.productName + "  Price: " + price);
		this.price = price;
	}

	/**
	 * This method returns product description
	 * 
	 * @return String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set product description
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * This method returns product image
	 * 
	 * @return Image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Set product image, throw error if image is null.
	 * 
	 * @param image
	 */
	public void setImage(Image image) {
		if (image == null)
			throw new IllegalArgumentException("No product image. Product name: " + this.productName);
		this.image = image;
	}

	/**
	 * This method returns product quantity
	 * 
	 * @return int
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Set product quantity, throw error if quantity is less than zero
	 * 
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		if (quantity < 0)
			throw new IllegalArgumentException("Please check the product quantity.  Product name: " + this.productName
					+ "  Quantity: " + quantity);
		this.quantity = quantity;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Product) {
			Product product = (Product) o;
			return this.productName.equals(product.productName) && this.price == product.price
					&& this.category.equals(product.category);
		}
		return false;
	}

	@Override
	public String toString() {
		return productName + "  " + category + "  " + price + "\n" + description;
	}

}