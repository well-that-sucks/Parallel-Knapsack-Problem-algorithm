public class InputValidator {
    private String menuItemRegex;
    private String submenuItemRegex;
    private String itemNumberRegex;
    private int itemNumberManualMin = 1;
    private int itemNumberManualMax = 20;
    private int itemNumberAutoMin = 1;
    private int itemNumberAutoMax = 32768;
    private String capacityRegex;
    private int capacityMin = 1;
    private int capacityMax = 32768;
    // private String valuesRegex;
    private int valuesMin = 1;
    private int valuesMax = 32768;
    // private String weightsRegex;
    private int weightsMin = 1;
    private int weightsMax = 32768;
    private String threadsRegex;
    private int threadsMin = 1;
    private int threadsMax = 20;

    public InputValidator(String menuItemRegex, String submenuItemRegex, String itemNumberRegex, String capacityRegex, String threadsRegex/*, String valuesRegex, String weightsRegex*/) {
        this.menuItemRegex = menuItemRegex;
        this.submenuItemRegex = submenuItemRegex;
        this.itemNumberRegex = itemNumberRegex;
        this.capacityRegex = capacityRegex;
        this.threadsRegex = threadsRegex;
        // this.valuesRegex = valuesRegex;
        // this.weightsRegex = weightsRegex;
    }

    public InputValidator() {
        menuItemRegex = "^[1-4]$";
        submenuItemRegex = "^[1-2]$";
        itemNumberRegex = "^[1-9][0-9]{0,4}$";
        capacityRegex = "^[1-9][0-9]{0,4}$";
        threadsRegex = "^[1-9][0-9]?$";
        // valuesRegex = "^[1-9][0-9]{0,4}$";
        // weightsRegex = "^[1-9][0-9]{0,4}$";
    }

    public int validateAndReturnMenuItem(String input) throws InvalidInputException, NumberFormatException {
        if (input.equals("")) {
            throw new InvalidInputException("field shouldn't be empty");
        } else {
            if (!input.matches(menuItemRegex)) {
                throw new InvalidInputException("incorrect input format");
            }
            return Integer.parseInt(input);
        }
    }

    public int validateAndReturnSubMenuItem(String input) throws InvalidInputException, NumberFormatException {
        if (input.equals("")) {
            throw new InvalidInputException("field shouldn't be empty");
        } else { 
            if (!input.matches(submenuItemRegex)) {
                throw new InvalidInputException("incorrect input format");
            }
            return Integer.parseInt(input);
        }
    }

    public int validateAndReturnThreadNumber(String input) throws InvalidInputException, NumberFormatException {
        if (input.equals("")) {
            throw new InvalidInputException("field shouldn't be empty");
        } else { 
            if (!input.matches(threadsRegex)) {
                throw new InvalidInputException("incorrect input format");
            }
            int threadNumber = Integer.parseInt(input);
            if (threadNumber >= threadsMin && threadNumber < threadsMax) {
                return threadNumber;
            }
            throw new InvalidInputException("incorrect input");
        }
    }

    public int validateAndReturnItemNumberManual(String input) throws InvalidInputException, NumberFormatException {
        if (input.equals("")) {
            throw new InvalidInputException("field shouldn't be empty");
        } else {
            if (!input.matches(itemNumberRegex)) {
                throw new InvalidInputException("incorrect input format");
            }
            int itemNumber = Integer.parseInt(input);
            if (itemNumber >= itemNumberManualMin && itemNumber < itemNumberManualMax) {
                return itemNumber;
            }
            throw new InvalidInputException("incorrect input");
        }
    }

    public int validateAndReturnItemNumberAuto(String input) throws InvalidInputException, NumberFormatException {
        if (input.equals("")) {
            throw new InvalidInputException("field shouldn't be empty");
        } else {
            if (!input.matches(itemNumberRegex)) {
                throw new InvalidInputException("incorrect input format");
            }
            int itemNumber = Integer.parseInt(input);
            if (itemNumber >= itemNumberAutoMin && itemNumber < itemNumberAutoMax) {
                return itemNumber;
            }
            throw new InvalidInputException("incorrect input");
        }
    }

    public int validateAndReturnCapacity(String input) throws InvalidInputException, NumberFormatException {
        if (input.equals("")) {
            throw new InvalidInputException("field shouldn't be empty");
        } else {
            if (!input.matches(capacityRegex)) {
                throw new InvalidInputException("incorrect input format");
            }
            int capacity = Integer.parseInt(input);
            if (capacity >= capacityMin && capacity < capacityMax) {
                return capacity;
            }
            throw new InvalidInputException("incorrect input");
        }
    }

    public void validateValuesArray(int[] values) throws InvalidInputException {
        for (var value : values) {
            if (value < valuesMin || value >= valuesMax) {
                throw new InvalidInputException("incorrect input");
            }
        }
    }

    public void validateWeightsArray(int[] weights) throws InvalidInputException {
        for (var weight : weights) {
            if (weight < weightsMin || weight >= weightsMax) {
                throw new InvalidInputException("incorrect input");
            }
        }
    }
}
