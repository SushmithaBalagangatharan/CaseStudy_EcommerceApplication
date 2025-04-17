package exception;

public class CustomerNotFoundException extends Exception{
	public CustomerNotFoundException() {
		super("Customer Id does not exist");
	}	
	
	public CustomerNotFoundException(String msg) {
		super(msg);
	}
}
