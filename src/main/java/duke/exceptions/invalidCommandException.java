package duke.exceptions;

public class invalidCommandException extends Exception {
    public invalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
