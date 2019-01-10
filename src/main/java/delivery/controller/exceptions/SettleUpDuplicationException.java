package delivery.controller.exceptions;

public class SettleUpDuplicationException extends RuntimeException {

    public SettleUpDuplicationException() {
        super();
    }

    public SettleUpDuplicationException(String message) {
        super(message);
    }
}
