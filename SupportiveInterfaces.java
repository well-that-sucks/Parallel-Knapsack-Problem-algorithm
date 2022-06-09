interface ItemValidationFunction {
    int validateAndReturn(String input) throws InvalidInputException, NumberFormatException;
}

interface ArrayValidationFunction {
    void validate(int[] array) throws InvalidInputException;
}
