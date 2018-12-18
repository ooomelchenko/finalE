package delivery.controller.exceptions;

public class RoleAccessDeniedCommandException extends RuntimeException {
    public RoleAccessDeniedCommandException(){
        super();
    }
    public RoleAccessDeniedCommandException(String command){
        super(command);
    }
}
