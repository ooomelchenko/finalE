package delivery.exceptions;

public class WrongCommandException extends RuntimeException {
    public WrongCommandException(){
        super();
    }
    public WrongCommandException(String command){
        super(command);
    }
}
