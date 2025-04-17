package tests;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.OrderProcessorRepositoryImpl;
import entity.model.ProductManagement;

public class ProductCreationTest {
	ProductManagement pm;
	
	@BeforeEach
	public void init() {
		pm = new ProductManagement();
	}

	@Test
    public void productCreate() {    	
    	String name = "Laptop";
    	double price = 60000;
    	String description = "Professional Laptop";
    	int stock = 8;
    	
    	pm.setProductName(name);
    	pm.setPrice(price);
    	pm.setDescription(description);
    	pm.setStockQuantity(stock);
    	
    	OrderProcessorRepositoryImpl ol = new OrderProcessorRepositoryImpl();
    	boolean isCreated = ol.createProduct(pm);
    	
    	assertTrue(isCreated, "Product created successfully");
    	
    	int productId = pm.getProductId();
    	boolean isPresent = ol.isProductExist(productId);
    	assertTrue(isPresent, "Product present in database");
    }
}

