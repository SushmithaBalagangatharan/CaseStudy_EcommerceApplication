package exception;

public class OrderNotFoundException extends Exception{
	public OrderNotFoundException() {
		super("Order does not exist");
	}
	
	public OrderNotFoundException(String msg) {
		super(msg);
	}
}

