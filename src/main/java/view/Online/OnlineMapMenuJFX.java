package view.Online;

import controller.Controller;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import view.MenuJFX;

import java.io.IOException;
import java.util.Objects;

public class OnlineMapMenuJFX extends Application implements MenuJFX {
    private Controller controller;
    private AnchorPane mapMenuPane, mapsContent;
    private ScrollPane mapsScrollPane;
    private Button backButton;
    private Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        mapMenuPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/OnlineMap.fxml")));
        mapMenuPane.setBackground(Background.fill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/backgrounds/9.jpg")).toExternalForm()))));

        mapsScrollPane = (ScrollPane) mapMenuPane.getChildren().get(0);
        mapsContent = (AnchorPane) mapsScrollPane.getContent();
        setMapsContent();

        backButton = (Button) mapMenuPane.getChildren().get(1);
        setBackButton();
        Scene scene = new Scene(mapMenuPane);
        stage.setScene(scene);
        stage.show();
    }

    private void setMapsContent() {
        // TODO
    }

    private void setBackButton() {
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    controller.enterOnlineMenuJFX();
                    stop();
                    controller.getGame().getCurrentMenuJFX().start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void adjust(double ratioX, double ratioY) {

    }

    @Override
    public void adjustWithScene() {

    }
}
