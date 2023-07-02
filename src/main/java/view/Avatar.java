package view;

import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Avatar extends Circle {
    public static final int PROFILE_RADIUS = 30;
    private final ImagePattern imagePattern;

    public Avatar(double x, double y, ImagePattern imagePattern) {
        super(x, y, PROFILE_RADIUS);
        this.imagePattern = imagePattern;
        this.setFill(imagePattern);
        this.setStroke(Color.BLACK);
        this.setStrokeWidth(3);
    }

    public ImagePattern getImagePattern() {
        return imagePattern;
    }
}
