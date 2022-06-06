public class Controller {
    private final Model model;
    private final View view;

    public Controller() {
        model = new Model();
        view = new View();
    }

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void run() {
        // TDB
    }
}
