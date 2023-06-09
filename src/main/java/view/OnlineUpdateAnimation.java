package view;

import controller.Controller;
import javafx.animation.Transition;
import javafx.scene.control.Menu;
import javafx.util.Duration;
import view.Online.OnlineChatMenuJFX;
import view.Online.OnlineFriendMenuJFX;
import view.Online.OnlineMapMenuJFX;
import view.Online.OnlineMenuJFX;

public class OnlineUpdateAnimation extends Transition {
    private final Controller controller;

    private boolean shouldRun = true;
    public OnlineUpdateAnimation(Controller controller) {
        this.controller = controller;
        this.setCycleCount(INDEFINITE);
        this.setCycleDuration(Duration.millis(1000));
        this.setOnFinished(actionEvent -> {
            shouldRun = true;
        }
        );
    }

    @Override
    protected void interpolate(double v) {
        if(shouldRun || v<0.0001) {
            if (controller.getGame().getCurrentMenuJFX() instanceof OnlineChatMenuJFX) {
                ((OnlineChatMenuJFX) controller.getGame().getCurrentMenuJFX()).refresh();
            } else if (controller.getGame().getCurrentMenuJFX() instanceof OnlineFriendMenuJFX) {
                ((OnlineFriendMenuJFX) controller.getGame().getCurrentMenuJFX()).refresh();
            } else if (controller.getGame().getCurrentMenuJFX() instanceof OnlineMapMenuJFX) {
                ((OnlineMapMenuJFX) controller.getGame().getCurrentMenuJFX()).refresh();
            } else if (controller.getGame().getCurrentMenuJFX() instanceof ScoreBoardJFX &&
                    !((ScoreBoardJFX) controller.getGame().getCurrentMenuJFX()).isOffline()) {
                ((ScoreBoardJFX) controller.getGame().getCurrentMenuJFX()).refresh();
            }
            shouldRun = false;
        }
    }
}