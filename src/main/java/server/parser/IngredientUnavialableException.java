package server.parser;

public class IngredientUnavialableException extends Exception {
    public IngredientUnavialableException() {
    }

    public IngredientUnavialableException(String message) {
        super(message);
    }

    public IngredientUnavialableException(String message, Throwable cause) {
        super(message, cause);
    }

    public IngredientUnavialableException(Throwable cause) {
        super(cause);
    }
}
