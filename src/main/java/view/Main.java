package view;

import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Controller controller = new Controller(stage);
        controller.run();
    }
}