package dao;
import java.util.*;
import java.util.Map;

import entity.model.CustomerManagement;
import entity.model.ProductManagement;
import exception.CustomerNotFoundException;
import exception.OrderNotFoundException;
import exception.ProductNotFoundException;

public interface OrderProcessorRepository {
	boolean createProduct(ProductManagement product);
	boolean createCustomer(CustomerManagement cutomer);
	boolean deleteProduct(int productId) throws ProductNotFoundException;
	boolean deleteCustomer(int customerId) throws CustomerNotFoundException;
	boolean addToCart(CustomerManagement customer, ProductManagement product, int quantity);
	boolean removeFromCart(CustomerManagement customer, ProductManagement product);
	List<ProductManagement> getAllFromCart(CustomerManagement customer);
	boolean placeOrder(CustomerManagement customer, List<Map<ProductManagement,Integer>> products, String shippingAddress);
	List<Map<ProductManagement, Integer>> getOrdersByCustomer(int customerId) throws OrderNotFoundException;
}
