package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import entity.model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.OrderProcessorRepositoryImpl;

public class InCartTest {
	OrderProcessorRepositoryImpl ol;
	CustomerManagement cm;
	
	@BeforeEach
	public void init() {
		ol = new OrderProcessorRepositoryImpl();
		cm = new CustomerManagement();
	}

	@Test
	public void productInCart() {
		
		int customerId = 1;
		int productId = 1;
		cm.setCustomerId(customerId);
		
		List<ProductManagement> products = ol.getAllFromCart(cm);
		
		for(ProductManagement p : products) {
			assertEquals(productId, p.getProductId());
		}
		
	}
}
