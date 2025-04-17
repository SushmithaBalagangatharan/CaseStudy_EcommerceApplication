package tests;

import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.OrderProcessorRepositoryImpl;
import exception.CustomerNotFoundException;
import exception.ProductNotFoundException;

public class CorrectExceptionThrownTest {

	OrderProcessorRepositoryImpl ol;
	@BeforeEach
	public void init() {
		ol = new OrderProcessorRepositoryImpl();
	}
	
	@Test
	public void isProductExceptionThrown() {
		int productId = 1;
		assertThrows(ProductNotFoundException.class ,() -> ol.deleteProduct(productId));
	}
	
	@Test
	public void isCustomerExceptionThrown() {
		int customerId = 1;
		assertThrows(CustomerNotFoundException.class, () -> ol.deleteCustomer(customerId));
	}
}


