package entity.model;
import java.sql.Date;

public class OrderManagement {
	private int order_id;
	private int customer_id;
	private Date order_date;
	private double total_price;
	private String shipping_address;
	
	public int getOrderId() {
		return order_id;
	}
	public void setOrderId(int order_id) {
		this.order_id = order_id;
	}
	
	public int getCustomerId() {
		return customer_id;
	}
	public void setCustomerId(int customer_id) {
		this.customer_id = customer_id;
	}
	
	public Date getOrderDate() {
		return order_date;
	}
	public void setOrderDate(Date order_date) {
		this.order_date = order_date;
	}
	
	public double getTotalPrice() {
		return total_price;
	}
	public void setTotalPrice(double total_price) {
		this.total_price = total_price;
	}
	
	public String getShippingAddress() {
		return shipping_address;
	}
	public void getShippingAddress(String shipping_address) {
		this.shipping_address = shipping_address;
	}
}
