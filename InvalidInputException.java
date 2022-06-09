public class InvalidInputException extends Exception {
    public InvalidInputException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return ("InvalidInputException: " + getMessage());
    }
}
