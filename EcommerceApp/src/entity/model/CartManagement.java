package entity.model;

public class CartManagement {
	private int cart_id;
	private int customer_id;
	private int product_id;
	private int quantity;
	
	public int getCartId() {
		return cart_id;
	}
	public void setCartId(int cart_id) {
		this.cart_id = cart_id;
	}
	
	public int getCustomerId() {
		return customer_id;
	}
	public void setCustomerId(int customer_id) {
		this.customer_id = customer_id;
	}
	
	public int getProductId() {
		return product_id;
	}
	public void setProductId(int product_id) {
		this.product_id = product_id;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
