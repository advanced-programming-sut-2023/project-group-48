package view;

import controller.Controller;
import controller.MapMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.Objects;

public class MapMenuJFX extends Application {
    private Controller controller;
    private MapMenuController mapMenuController;
    private AnchorPane viewPane, MapPane;
    private Polygon[][] map;

    private Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        viewPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/SignUpMenu.fxml")));
    }
}
