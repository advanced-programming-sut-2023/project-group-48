package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.Objects;


public class MatchMenuJFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        int height = 10;
        int width = 10;

        Pane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Game.fxml")));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
        Rotate rotateX = new Rotate();
        Rotate rotateZ = new Rotate();
        Point3D axisRotateZ = new Point3D(0, 0, 1);
        Point3D axisRotateX = new Point3D(1, -1, 0);
        rotateX.setAngle(60);
        rotateZ.setAngle(45);
        rotateX.setAxis(axisRotateX);
        rotateZ.setAxis(axisRotateZ);

        Rectangle[][] map = new Rectangle[height][width];
        double[][] verticalCoordinate = new double[height][width];
        double[][] horizontalCoordinate = new double[height][width];
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++) {
                map[i][j] = new Rectangle(j * 50, i * 50, 50, 50);
                pane.getChildren().add(map[i][j]);
                map[i][j].setFill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/tile/desert_tile.jpg")).toExternalForm())));
                map[i][j].getTransforms().addAll(rotateZ, rotateX);
                verticalCoordinate[i][j] = 50 * (i + j) / Math.sqrt(2) * Math.cos(60 * Math.PI / 180);
                horizontalCoordinate[i][j] = 50 * (j - i) / Math.sqrt(2) - 25 * Math.sqrt(2);
                int finalJ = j;
                int finalI = i;

                map[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        Rectangle building = new Rectangle(horizontalCoordinate[finalI][finalJ], verticalCoordinate[finalI][finalJ], 50 * Math.sqrt(2), 309 / 190 * (50 * Math.sqrt(2)));
                        building.setY(building.getY() - building.getHeight() + 50 * Math.sqrt(2) * Math.cos(60 * Math.PI / 180));
                        building.setFill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/tile/collection12.png")).toExternalForm())));
                        pane.getChildren().add(building);
                    }
                });
            }
    }
}
