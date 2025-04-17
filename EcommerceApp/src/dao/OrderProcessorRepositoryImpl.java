package dao;
import java.sql.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.DatabaseConnection;

import entity.model.ProductManagement;
import exception.CustomerNotFoundException;
import exception.OrderNotFoundException;
import exception.ProductNotFoundException;
import entity.model.CustomerManagement;


public class OrderProcessorRepositoryImpl implements OrderProcessorRepository{
	
	@Override
	public boolean createProduct(ProductManagement product){
		try {
			String query = "insert into Products values (?,?,?,?,?);";
			
		    Connection con = DatabaseConnection.getConnection();

			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setInt(1, product.getProductId());
			pst.setString(2, product.getProductName());
			pst.setDouble(3, product.getPrice());
			pst.setString(4, product.getDescription());
			pst.setInt(5, product.getStockQuantity());
			
			int row_num = pst.executeUpdate();
			
			return row_num > 0;
		}
		catch(Exception e) {
			System.out.print(e.toString());
			return false;
		}

	}
	
	@Override
	public boolean createCustomer(CustomerManagement customer){
		try {
		
			
			String query = "insert into Customer values (?,?,?,?);";
			
		    Connection con = DatabaseConnection.getConnection();

			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setInt(1, customer.getCustomerId());
			pst.setString(2, customer.getName());
			pst.setString(3, customer.getEmail());
			pst.setString(4, customer.getPassword());
			
			int row_num = pst.executeUpdate();
			
			return row_num > 0;
		}
		catch(Exception e) {
			System.out.print(e.toString());
			return false;
		}

	}
	
	@Override
	public boolean deleteProduct(int productId) throws ProductNotFoundException{
		try {			
			String query = "delete from Products where product_id = "+productId;
			
		    Connection con = DatabaseConnection.getConnection();

			PreparedStatement pst = con.prepareStatement(query);
			
			int row_num = pst.executeUpdate();
			
			if(row_num == 0) {
				throw new ProductNotFoundException("Product with "+productId+" not found");
			}
			
			return row_num > 0;
		}
		catch(Exception e) {
			System.out.print(e.toString());
			return false;
		}

	}
	
	@Override
	public boolean deleteCustomer(int customerId) throws CustomerNotFoundException{
		try {
		    Connection con = DatabaseConnection.getConnection();
			
			String query = "delete from Customer where customer_id = "+customerId;
			
			PreparedStatement pst = con.prepareStatement(query);
			
			int row_num = pst.executeUpdate();
			
			if(row_num == 0) {
				throw new CustomerNotFoundException();
			}
			
			return row_num > 0;
		}
		catch(Exception e) {
			System.out.print(e.toString());
			return false;
		}

	}
	
	public boolean addToCart(CustomerManagement customer, ProductManagement product, int quantity){
		try {
			
			String query = "insert into Cart (customer_id, product_id, quantity)  values (?,?,?);";
			
		    Connection con = DatabaseConnection.getConnection();
			
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setInt(1, customer.getCustomerId());
			pst.setInt(2, product.getProductId());
			pst.setInt(3, quantity);
			
			int row_num = pst.executeUpdate();
			
			return row_num > 0;
		}
		catch(Exception e) {
			System.out.print(e.toString());
			return false;
		}

	}
	
	@Override
	public boolean removeFromCart(CustomerManagement customer, ProductManagement product){
		try {
			String query = "delete from Cart where customer_id = ? and product_id = ?";
			
		    Connection con = DatabaseConnection.getConnection();

			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setInt(1, customer.getCustomerId());
			pst.setInt(2, product.getProductId());
			
			int row_num = pst.executeUpdate();
			
			return row_num > 0;
		}
		catch(Exception e) {
			System.out.print(e.toString());
			return false;
		}

	}
	
	@Override
	public List<ProductManagement>  getAllFromCart(CustomerManagement customer){
		try {
		
			String query = "select p.* from Products as p inner join Cart c on p.product_id = c.product_id where customer_id = ?;";
			
		    Connection con = DatabaseConnection.getConnection();
			
			 //list the product in cart for a customer.
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setInt(1, customer.getCustomerId());
			
			ResultSet rs = pst.executeQuery();
			
			List<ProductManagement> pml = new ArrayList<>();
			while(rs.next()) {
				ProductManagement pm = new ProductManagement();
				pm.setProductId(rs.getInt("product_id"));
				pm.setProductName(rs.getString("product_name"));
				pm.setPrice(rs.getDouble("price"));
				pm.setDescription(rs.getString("product_description"));
				pm.setStockQuantity(rs.getInt("stock_quantity"));
				pml.add(pm);
			}
			
			return pml;
		}
		catch(Exception e) {
			System.out.print(e.toString());
			return null;
		}

	}
	
	@Override
	public List<Map<ProductManagement, Integer>> getOrdersByCustomer(int customerId) throws OrderNotFoundException{
		try {
			
			String query = "SELECT p.*, oi.quantity " +
		               "FROM Order_items oi " +
		               "INNER JOIN Products p ON oi.product_id = p.product_id " +
		               "INNER JOIN Orders od ON od.order_id = oi.order_id " +
		               "WHERE od.customer_id = ?";
			
		    Connection con = DatabaseConnection.getConnection();
			
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, customerId);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs == null) {
				throw new OrderNotFoundException();
			}
			
			List<Map<ProductManagement, Integer>> ol = new ArrayList<>();
			
			while(rs.next()) {
				ProductManagement pm = new ProductManagement();
				pm.setProductId(rs.getInt("product_id"));
				pm.setProductName(rs.getString("product_name"));
				pm.setPrice(rs.getDouble("price"));
				pm.setDescription(rs.getString("product_description"));
				
				int quantity = rs.getInt("quantity");
				
				Map<ProductManagement, Integer> pml = new HashMap<>();
				pml.put(pm, quantity);
				ol.add(pml);
			}
			
			
			return ol;
		}
		catch(Exception e) {
			System.out.print(e.toString());
			return null;
		}

	}

	@Override
	public boolean placeOrder(CustomerManagement customer, List<Map<ProductManagement, Integer>> products,String shippingAddress) {
		try {
			
			String queryOrder = "insert into Orders (customer_id, order_date, total_price, shipping_address) values (?,CURDATE(),?,?);";
			String queryOrderItems = "insert into Order_items (order_id, product_id, quantity) values (?,?,?);";
			String queryCart = "delete from Cart where customer_id = ?";

	        
		    Connection con = DatabaseConnection.getConnection();
			
			PreparedStatement pst1 = con.prepareStatement(queryOrder, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement pst2 = con.prepareStatement(queryOrderItems);
			PreparedStatement pst3 = con.prepareStatement(queryCart);
						
			double total_price = 0;
			for(Map<ProductManagement, Integer> map : products) {
				for(Map.Entry<ProductManagement, Integer> entry : map.entrySet()) {
					ProductManagement p = entry.getKey();				
					total_price += p.getPrice() * entry.getValue();;
				}
			}
			
			pst1.setInt(1, customer.getCustomerId());
			pst1.setDouble(2, total_price);
			pst1.setString(3, shippingAddress);
			int row_num1 = pst1.executeUpdate();
			
			ResultSet rs = pst1.getGeneratedKeys();
			int orderId = -1;
			if(rs.next()) {
				orderId = rs.getInt(1);
			}
			else {
				throw new Exception("Order ID was not generted");
			}
			
			for(Map<ProductManagement, Integer> map : products) {
				for(Map.Entry<ProductManagement, Integer> entry : map.entrySet()) {
					pst2.setInt(1, orderId);
					ProductManagement p = entry.getKey();
					pst2.setInt(2,p.getProductId());
					pst2.setInt(3, entry.getValue());
					pst2.executeUpdate();
				}
			}
			
			
			pst3.setInt(1, customer.getCustomerId());
			int row_num3 = pst3.executeUpdate();
			
			return (row_num1 > 0 && row_num3 > 0);
		}
		catch(Exception e) {
			System.out.print(e.toString());
			return false;
		}
	}
	
	//For J-unit Test CASE(ProjectCreationTest) 
	public boolean isProductExist(int productId){
		try {
			
			String query = "select * from Products";
			
		    Connection con = DatabaseConnection.getConnection();

			PreparedStatement pst = con.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			
			boolean status = false;
			while(rs.next()) {
				if(productId == rs.getInt("product_id")) {
					status = true;
					break;
				}
			}
			
			return status;
		}
		catch(Exception e) {
			System.out.print(e.toString());
			return false;
		}

	}
	

}

