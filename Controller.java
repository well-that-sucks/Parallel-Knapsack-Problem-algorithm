import java.util.Scanner;

public class Controller {
    private final Model model;
    private final View view;
    private final InputValidator inputValidator;

    public Controller() {
        model = new Model();
        view = new View();
        inputValidator = new InputValidator();
    }

    public Controller(Model model, View view, InputValidator validator) {
        this.model = model;
        this.view = view;
        inputValidator = validator;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        int enteredOption = readNumberUntilCorrect(sc, view.MENU_MSG, inputValidator::validateAndReturnMenuItem);
        while (enteredOption != 4) {
            switch (enteredOption) {
                case 1:
                    processSingleOptions(sc, new SequentialKnapsackSolver());
                    break;
                case 2:
                    processSingleOptions(sc, new ParallelKnapsackSolver(readNumberUntilCorrect(sc,
                            view.THREAD_NUMBER_ENTER_MSG, inputValidator::validateAndReturnThreadNumber)));
                    break;
                case 3:
                    processComparisonOption(
                            readNumberUntilCorrect(sc, view.VALUES_AUTOTEST_ENTER_MSG,
                                    inputValidator::validateAndReturnItemNumberAuto),
                            new SequentialKnapsackSolver(),
                            new KnapsackSolver[] { new ParallelKnapsackSolver(readNumberUntilCorrect(sc,
                                    view.THREAD_NUMBER_ENTER_MSG, inputValidator::validateAndReturnThreadNumber)) });
                    break;
                default:
                    view.printMessage(view.INVALID_OPTION_MSG);
                    break;
            }
            enteredOption = readNumberUntilCorrect(sc, view.MENU_MSG, inputValidator::validateAndReturnMenuItem);
        }
    }

    public int[] IntegerToInt(Integer[] array) {
        int[] res = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            res[0] = array[0];
        }
        return res;
    }

    public InputValues readItemsNumber(Scanner sc, int itemsNum) {
        return new InputValues(itemsNum,
                readNumberUntilCorrect(sc, view.MAX_CAPACITY_ENTER_MSG, inputValidator::validateAndReturnCapacity),
                readArrayUntilCorrect(sc, itemsNum, view.WORTH_ENTER_MSG, inputValidator::validateValuesArray),
                readArrayUntilCorrect(sc, itemsNum, view.WEIGHTS_ENTER_MSG, inputValidator::validateWeightsArray));
    }

    public void processSingleOptions(Scanner sc, KnapsackSolver solver) {
        model.setNewSolver(solver);
        int submenuOption = readNumberUntilCorrect(sc, view.SUBMENU_MSG, inputValidator::validateAndReturnSubMenuItem);
        int n, maxCapacity;
        int[] values, weights;
        InputValues res;
        switch (submenuOption) {
            case 1:
                n = readNumberUntilCorrect(sc, view.VALUES_MANUAL_ENTER_MSG, inputValidator::validateAndReturnItemNumberManual);
                res = readItemsNumber(sc, n);
                break;
            case 2:
                n = readNumberUntilCorrect(sc, view.VALUES_AUTOGENERATED_ENTER_MSG, inputValidator::validateAndReturnItemNumberAuto);
                res = model.generateInputValues(n);
                break;
            default:
                view.printMessage(view.INVALID_OPTION_MSG);
                n = 0;
                res = null;
                break;
        }
        if (res != null) {
            maxCapacity = res.getMaxCapacity();
            values = res.getValues();
            weights = res.getWeights();
            view.printSolution(model.solve(n, values, weights, maxCapacity));
        }
    }

    public void processComparisonOption(int maxItemNumber, KnapsackSolver referenceSolver,
            KnapsackSolver[] comparedSolvers) {
        model.setNewSolver(referenceSolver);
        view.printExecutionStatus(referenceSolver);
        model.addTestSolutions(model.runTestBatches(model.generateTestBatches(maxItemNumber)));
        for (var solver : comparedSolvers) {
            model.setNewSolver(solver);
            model.addTestSolutions(model.runTestBatches());
            view.printExecutionStatus(referenceSolver);
        }
        view.printTestSolutions(model.getTestSolutions(), maxItemNumber);
        view.printMessage(
                model.checkIfEverythingIsIntact(model.getTestSolutions()) ? view.TESTS_OK_MSG : view.TESTS_FAILURE_MSG);
    }

    public int readNumberUntilCorrect(Scanner sc, String promptLine, ItemValidationFunction validationFunction) {
        while (true) {
            try {
                view.printMessage(promptLine);
                String input = sc.nextLine().trim();
                int validatedInput = validationFunction.validateAndReturn(input);
                return validatedInput;
            } catch (InvalidInputException | NumberFormatException e) {
                // For debugging purposes
                // view.printMessage(e.getMessage());
                view.printMessage(view.TRY_AGAIN_MSG);
            }
        }
    }

    public int[] readArrayUntilCorrect(Scanner sc, int itemNumber, String promptLine,
            ArrayValidationFunction validationFunction) {
        int[] res = new int[itemNumber];
        while (true) {
            try {
                view.printMessage(promptLine);
                view.printMessage(view.WORTH_ENTER_MSG);
                for (int i = 0; i < itemNumber; i++) {
                    res[i] = sc.nextInt();
                }
                validationFunction.validate(res);
                return res;
            } catch (InvalidInputException e) {
                // For debugging purposes
                // view.printMessage(e.getMessage());
                view.printMessage(view.TRY_AGAIN_MSG);
            }
        }
    }
}
