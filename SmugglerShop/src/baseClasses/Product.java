package baseClasses;

/**
 * This class represent a product.
 * 
 * @author SarpreetSingh
 *
 */
public class Product {

	// Fields
	private String name;
	private String category;
	private double price;
	private String image;
	private String description;
	private int quantity;
	private int id;
	

	public int getId() {
		return id;
	}


	/**
	 * Empty Constructor
	 */
	public Product() {
		name = "";
		category = "";
		price = 0;
		description = "";
		image = null;
		quantity = 0;
		id = 0;
	}
	
	/**
	 * Full Constructor
	 */
	public Product(String pname, String cat, double pric, String descr,String img, int amount, int id) {
		if(price < 0 || amount < 0) throw new IllegalArgumentException("Price and quantity must be greater or equal than 0.");

		name = pname;
		category = cat;
		price = pric;
		description = descr;
		image = img;
		quantity = amount;
		this.id = id;
		
	}

	/**
	 * This method returns product name
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set product name
	 * 
	 * @param productName
	 */
	public void setName(String productName) {
		this.name = productName;
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
		if (price < 0.0)
			throw new IllegalArgumentException(
					"Please check the product price. Product name: " + this.name + "  Price: " + price);
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
	public String getImage() {
		return image;
	}

	/**
	 * Set product image
	 * 
	 * @param image
	 */
	public void setImage(String image) {
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
			throw new IllegalArgumentException("Please check the product quantity.  Product name: " + this.name
					+ "  Quantity: " + quantity);
		this.quantity = quantity;
	}

	/**
	 * Return true if object is equals to product else false.
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Product) {
			Product product = (Product) o;
			return this.name.equals(product.name) && this.price == product.price
					&& this.category.equals(product.category);
		}
		return false;
	}

	/**
	 * Represent a nice print out of product
	 */
	@Override
	public String toString() {
		return name + "  " + category + "  " + price + "\n" + description;
	}

}