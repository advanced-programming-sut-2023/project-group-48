package view;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Objects;

public class FireAnimation extends Transition {
    private static final ArrayList<ImagePattern> fireImages = new ArrayList<>();

    static {
        for (int i = 0; i < 16; i++) {
            fireImages.add(new ImagePattern(new Image(Objects.requireNonNull(FireAnimation.class.getResource("/fire/Image" + (i + 1) + ".png")).toExternalForm())));
        }
    }

    private ArrayList<Rectangle> rectangles;

    public FireAnimation() {
        rectangles = new ArrayList<>();
        this.setCycleDuration(Duration.millis(10000));
        this.setCycleCount(1);
    }

    @Override
    protected void interpolate(double v) {
        for (Rectangle rectangle : rectangles) {
            rectangle.setFill(fireImages.get(fireImages.indexOf((ImagePattern) rectangle.getFill()) + 1));
        }
    }

    public void addRectangle(Rectangle rectangle) {
        rectangles.add(rectangle);
        rectangle.setFill(fireImages.get(0));
    }
}
