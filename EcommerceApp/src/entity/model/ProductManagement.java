package entity.model;

public class ProductManagement {
	private int product_id;
	private String name;
	private double price;
	private String description;
	private int stockQuantity;
	
	public int getProductId() {
		return product_id;
	}
	public void setProductId(int customer_id) {
		this.product_id = customer_id;
	}
	
	public String getProductName() {
		return name;
	}
	public void setProductName(String name) {
		this.name = name;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
}
