package main;
import java.util.*;

import dao.OrderProcessorRepositoryImpl;
import entity.model.CustomerManagement;
import entity.model.ProductManagement;
import exception.CustomerNotFoundException;
import exception.OrderNotFoundException;
import exception.ProductNotFoundException;


public class EcomApp{
	

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int ch = 0;
		boolean status = false;	
		OrderProcessorRepositoryImpl imp = new OrderProcessorRepositoryImpl();

		
		do {
			System.out.println("\n------------ Ecom Application ------------");
			System.out.println("1. Register Customer");
			System.out.println("2. Create Product");
			System.out.println("3. Delete Product");
			System.out.println("4. Add to Cart");
			System.out.println("5. View Cart");
			System.out.println("6. Place Order");
			System.out.println("7. View Customer Order");
			System.out.println("8. EXIT");
			System.out.println("Choose any one option:");
			
			ch = sc.nextInt();		
			switch (ch) {
					
				case 1:
					System.out.println("Enter Customer ID: ");
					int id = sc.nextInt();
					sc.nextLine();
					System.out.println("Enter Customer name: ");
					String name = sc.nextLine();
					System.out.println("Enter Customer email: ");
					String email = sc.nextLine();
					System.out.println("Enter Customer password: ");
					String password = sc.nextLine();
					
					CustomerManagement cm = new CustomerManagement();
					cm.setCustomerId(id);
					cm.setName(name);
					cm.setEmail(email);
					cm.setPassword(password);
					
					status = imp.createCustomer(cm);
					if(status) {
						System.out.println("Customer Registered successfully !");
					}
					else {
						try {
							throw new CustomerNotFoundException();
						}
						catch(CustomerNotFoundException e) {
							System.out.println(e.getMessage());
						}
					}
					break;
					
					
				case 2: 
					System.out.println("Enter Product ID: ");
					int productId = sc.nextInt();
					sc.nextLine();
					System.out.println("Enter Product name: ");
					String productName = sc.nextLine();
					System.out.println("Enter Product price: ");
					double price = sc.nextDouble();
					sc.nextLine();
					System.out.println("Enter Product description: ");
					String description = sc.nextLine();
					System.out.println("Enter Product Stock Quantity: ");
					int quantity = sc.nextInt();
					
					ProductManagement pm = new ProductManagement();
					pm.setProductId(productId);
					pm.setProductName(productName);
					pm.setPrice(price);
					pm.setDescription(description);
					pm.setStockQuantity(quantity);
					
					status = imp.createProduct(pm);
					if(status) {
						System.out.println("Product Created successfully !");
					}
					else {
						try {
							throw new ProductNotFoundException();
						}
						catch(ProductNotFoundException e) {
							System.out.println(e.getMessage());
						}
					}
					break;
					
					
				case 3:
					System.out.println("Enter Product Id: ");
					int productId1 = sc.nextInt();
					try {
			     		status  = imp.deleteProduct(productId1);
						if(!status) {
							throw new ProductNotFoundException();
						}
						System.out.println("Product Deleted successfully !");
					}
					catch(ProductNotFoundException e) {
						System.out.println(e.getMessage());
					}
					break;
					
					
				case 4:
					System.out.println("Enter Customer ID: ");
					int id1 = sc.nextInt();
					
					CustomerManagement cm1 = new CustomerManagement();
					cm1.setCustomerId(id1);
					
					System.out.println("Enter Product ID: ");
					int productId2 = sc.nextInt();
					
					System.out.println("Enter Product Stock Quantity: ");
					int quantity1 = sc.nextInt();
					
					ProductManagement pm1 = new ProductManagement();
					pm1.setProductId(productId2);
					pm1.setStockQuantity(quantity1);
					
					status = imp.addToCart(cm1, pm1, quantity1);
					
					if(status) {
						System.out.println("Product Added to Cart successfully !");
					}
					else {
						try {
							throw new ProductNotFoundException("Invalid Customer ID or Product ID!");
						}
						catch(ProductNotFoundException e) {
							System.out.println(e.getMessage());
						}
					}
					break;
					
					
				case 5:
					System.out.println("Enter Customer ID:");
					int customerID = sc.nextInt();
					
					CustomerManagement cm2 = new CustomerManagement();
					cm2.setCustomerId(customerID );
					
					List<ProductManagement> products = imp.getAllFromCart(cm2);
					
					for(ProductManagement p : products) {
						System.out.println(p.getProductName()+" "+p.getStockQuantity());
						int pid = p.getProductId();
						if(!(pid>=0)) {
							try {
								throw new ProductNotFoundException();
							}
							catch(ProductNotFoundException e) {
								System.out.println(e.getMessage());
							}
						}
					}
				    break;
				    
				    
				case 6:	
				    System.out.println("Enter Customer ID:");
				    int customerId = sc.nextInt();
				    sc.nextLine();
				    System.out.println("Enter Shipping Address: ");
				    String shippingAdd = sc.nextLine();
				    
				    CustomerManagement cm3 = new CustomerManagement();
				    cm3.setCustomerId(customerId);
				    
				    List<ProductManagement> customerCart = imp.getAllFromCart(cm3);
				    Map<ProductManagement, Integer> itemsOrdered = new HashMap<>();
				    List<Map<ProductManagement, Integer>> list = new ArrayList<>();
				    
				    for(ProductManagement p : customerCart) {
				    	itemsOrdered.put(p, p.getStockQuantity());
				    }
				    list.add(itemsOrdered);
				    status = imp.placeOrder(cm3, list, shippingAdd);
				    

				    if(status) {
						System.out.println("Product Deleted successfully !");
					}
					else {
						try {
							throw new ProductNotFoundException();
						}
						catch(ProductNotFoundException e) {
							System.out.println(e.getMessage());
						}
					}
					break;
					
					
				case 7:
					try {
						System.out.println("Enter Customer Id:");
						int customerId1 = sc.nextInt();
						
						List<Map<ProductManagement,Integer>> customerOrder = imp.getOrdersByCustomer(customerId1);
						System.out.println("Orders retrieved: " + customerOrder.size());
	
						for(Map<ProductManagement,Integer> map : customerOrder) {
							for(Map.Entry<ProductManagement, Integer> obj: map.entrySet()) {
								ProductManagement p = obj.getKey();
								System.out.println("Product ID: "+p.getProductId());
								System.out.println("Product Name: "+p.getProductName());
								System.out.println("Product Price: "+p.getPrice());
								System.out.println("Product Description: "+p.getDescription());
								System.out.println("Product Stock Quantity: "+p.getStockQuantity());
	
							}
						}
					}
					catch(OrderNotFoundException oe) {
						System.out.println("Error: "+oe.getMessage());
					}
					break;
					
				case 8:
					break;
					
				default:
					System.out.println("Invalid Option!");
			}
		}while(ch!=8);
		sc.close();
	}

}
