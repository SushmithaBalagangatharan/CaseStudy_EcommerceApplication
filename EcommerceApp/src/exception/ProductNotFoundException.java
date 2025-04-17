package exception;

public class ProductNotFoundException extends Exception{
	public ProductNotFoundException() {
		super("Product does not exist!");
	}
	
	public ProductNotFoundException(String msg) {
		super(msg);
	}
}
