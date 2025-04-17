package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.OrderProcessorRepositoryImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;
import entity.model.*;
import exception.OrderNotFoundException;

public class ProductOrderedTest {
	OrderProcessorRepositoryImpl ol;
	@BeforeEach
	public void init() {
		ol = new OrderProcessorRepositoryImpl();
	}
	
	@Test
	public void isProductOrdered() throws OrderNotFoundException {
		int customerId = 1;
		int productId = 1;
		
		List<Map<ProductManagement, Integer>> customerOrders = ol.getOrdersByCustomer(customerId);
		
		for(Map<ProductManagement,Integer> map : customerOrders) {
			for(Map.Entry<ProductManagement,Integer> obj : map.entrySet()) {
				ProductManagement p = obj.getKey();
				assertEquals(productId, p.getProductId(), "Product is not Ordered");
			}
		}
	}
}
