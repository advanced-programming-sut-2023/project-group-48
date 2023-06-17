package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.Buildings.BuildingType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class MatchBarJFX {
    private final MatchMenuJFX matchMenuJFX;
    private final AnchorPane viewPane;
    private AnchorPane mainBarPane, normalBuildingsPane, gateHousePane, industrialCenterPane1, industrialCenterPane2, industrialCenterPane3, recruitmentCenterPane, storagePane, trapPane;
    private Rectangle normalButton, gateHouseButton, industrialButton, recruitmentButton, storageButton, trapButton;
    private ArrayList<AnchorPane> anchorPanes;
    private double selectBarWidth, selectBarHeight, selectBarX, selectBarY;
    private ImagePattern selectedBuildingImagePattern;
    private AnchorPane currentPane;

    public MatchBarJFX(MatchMenuJFX matchMenuJFX, AnchorPane viewPane) {
        this.matchMenuJFX = matchMenuJFX;
        this.viewPane = viewPane;
        selectedBuildingImagePattern = null;
        currentPane = null;
        setMainBarPane();
        setNormalBuildingsPane();
        setGateHousePane();
        setIndustrialCenterPane1();
        setIndustrialCenterPane2();
        setIndustrialCenterPane3();
        setRecruitmentCenterPane();
        setStoragePane();
        setTrapPane();
        anchorPanes = new ArrayList<>(Arrays.asList(normalBuildingsPane, gateHousePane, industrialCenterPane1, recruitmentCenterPane, storagePane, trapPane));
    }

    private void addClickableToRectangle(AnchorPane pane, ImagePattern... imagePatterns) {
        double clickableWidths = 0;
        ArrayList<Rectangle> rectangles = new ArrayList<>();
        double lastX = 0, distance = 20;
        double ratio = 1;
        for (int i = 0; i < imagePatterns.length; i++) {
            rectangles.add(new Rectangle(lastX, pane.getPrefHeight() / 2 - imagePatterns[i].getImage().getHeight() / 2, imagePatterns[i].getImage().getWidth(), imagePatterns[i].getImage().getWidth()));
            lastX = rectangles.get(i).getX() + rectangles.get(i).getWidth() + distance;
        }
        for (Rectangle rectangle : rectangles) {
            clickableWidths += rectangle.getWidth();
            ratio = Math.min(ratio, pane.getPrefHeight() / rectangle.getHeight());
        }

        ratio = Math.min(ratio, (pane.getPrefWidth() - (imagePatterns.length + 1) * 20) / (clickableWidths));

        for (int i = 0; i < imagePatterns.length; i++) {
            rectangles.get(i).setLayoutY(pane.getPrefHeight() / 2 - ratio * rectangles.get(i).getHeight() / 2);
            rectangles.get(i).setWidth(ratio * rectangles.get(i).getWidth());
            rectangles.get(i).setHeight(ratio * rectangles.get(i).getHeight());
            int finalI = i;
            rectangles.get(i).hoverProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                    if (t1) {
                        rectangles.get(finalI).setOpacity(0.5);
                    } else {
                        rectangles.get(finalI).setOpacity(1);
                    }
                }
            });
            rectangles.get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    selectedBuildingImagePattern = imagePatterns[finalI];
                }
            });
            rectangles.get(i).setFill(imagePatterns[i]);
            pane.getChildren().add(rectangles.get(i));
        }
    }

    public ImagePattern getSelectedBuildingImagePattern() {
        return selectedBuildingImagePattern;
    }

    private void setMainBarPane() {
        ImageView mainBarImageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/menu/menu.png")).toExternalForm()));
        mainBarPane = new AnchorPane();
        mainBarPane.setPrefWidth(viewPane.getPrefWidth());
        mainBarPane.setPrefHeight(mainBarImageView.getImage().getHeight() / mainBarImageView.getImage().getWidth() * viewPane.getPrefWidth());
        mainBarPane.setLayoutX(0);
        mainBarPane.setLayoutY(viewPane.getPrefHeight() - mainBarPane.getPrefHeight());
        mainBarPane.getChildren().add(mainBarImageView);
        mainBarImageView.setFitWidth(mainBarPane.getPrefWidth());
        mainBarImageView.setFitHeight(mainBarPane.getPrefHeight());
        mainBarPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println(mouseEvent.getX() + " " + mouseEvent.getY());
            }
        });

        selectBarY = 73.78958333333333 / 720.0 * viewPane.getPrefHeight();
        selectBarX = 318.6666666666667 / 1452.0 * viewPane.getPrefWidth() ;
        selectBarHeight = (203.12291666666658 - 73.78958333333333) / 720.0 * viewPane.getPrefHeight();
        selectBarWidth = (828.6666666666666 - 318.6666666666667) / 1452.0 * viewPane.getPrefWidth();

        System.out.println(viewPane.getPrefWidth() + " " + viewPane.getPrefHeight());

        addButtons();
        viewPane.getChildren().add(mainBarPane);
    }

    private void addButtons() {
        double lastX = selectBarX + 5;
        ArrayList<ImagePattern> imagePatterns = new ArrayList<>();
        ArrayList<Rectangle> rectangles = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            imagePatterns.add(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/menu/" + (i * 3 + 1) + ".png")).toExternalForm())));
            imagePatterns.add(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/menu/" + (i * 3 + 2) + ".png")).toExternalForm())));
            imagePatterns.add(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/menu/" + (i * 3 + 3) + ".png")).toExternalForm())));
            rectangles.add(new Rectangle(lastX, mainBarPane.getPrefHeight() - imagePatterns.get(i * 3).getImage().getHeight(), imagePatterns.get(i * 3).getImage().getWidth(), imagePatterns.get(i * 3).getImage().getHeight()));
            rectangles.get(i).setFill(imagePatterns.get(i * 3));
            int finalI = i;
            rectangles.get(i).hoverProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                    if (t1) {
                        rectangles.get(finalI).setFill(imagePatterns.get(finalI * 3 + 1));
                    } else {
                        rectangles.get(finalI).setFill(imagePatterns.get(finalI * 3));
                    }
                }
            });
            rectangles.get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    rectangles.get(finalI).setFill(imagePatterns.get(finalI * 3 + 2));
                    if (currentPane != null) currentPane.setVisible(false);
                    currentPane = anchorPanes.get(finalI);
                    currentPane.setVisible(true);
                }
            });
            mainBarPane.getChildren().add(rectangles.get(i));
            lastX += rectangles.get(i).getWidth() * 3 / 2;
        }
    }

    private AnchorPane getBarPane() {
        AnchorPane pane = new AnchorPane();
        pane.setPrefWidth(selectBarWidth);
        pane.setPrefHeight(selectBarHeight - 60);
        pane.setLayoutX(selectBarX);
        pane.setLayoutY(selectBarY);
        return pane;
    }

    public void setNormalBuildingsPane() {
        normalBuildingsPane = getBarPane();
//        normalBuildingsPane.setBackground(Background.fill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/menu/temp.png")).toExternalForm()))));
        normalBuildingsPane.setVisible(false);
        addClickableToRectangle(normalBuildingsPane, BuildingType.getImagePattern("Drawbridge"),
                BuildingType.getImagePattern("Hovel"), BuildingType.getImagePattern("Shop"), BuildingType.getImagePattern("Inn"));
        mainBarPane.getChildren().add(normalBuildingsPane);
    }

    private void setGateHousePane() {
        gateHousePane = getBarPane();
//        gateHousePane.setBackground(Background.fill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/menu/temp.png")).toExternalForm()))));
        gateHousePane.setVisible(false);
        addClickableToRectangle(gateHousePane, BuildingType.getImagePattern("Tall Wall"), BuildingType.getImagePattern("Short Wall"),
                BuildingType.getImagePattern("Small Stone Gatehouse"), BuildingType.getImagePattern("Large Stone Gatehouse"),
                BuildingType.getImagePattern("Lookout Tower"), BuildingType.getImagePattern("Perimeter Tower"), BuildingType.getImagePattern("Defence Turret"),
                BuildingType.getImagePattern("Square Tower"), BuildingType.getImagePattern("Round Tower"));
        mainBarPane.getChildren().add(gateHousePane);
    }

    private void setIndustrialCenterPane1() {
        industrialCenterPane1 = getBarPane();
//        industrialCenterPane1.setBackground(Background.fill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/menu/temp.png")).toExternalForm()))));
        industrialCenterPane1.setVisible(false);
        addClickableToRectangle(industrialCenterPane1, BuildingType.getImagePattern("Stable"), BuildingType.getImagePattern("Mill"),
                BuildingType.getImagePattern("Iron Mine"), BuildingType.getImagePattern("Pitch Rig"),
                BuildingType.getImagePattern("Quarry"), BuildingType.getImagePattern("Woodcutter"), BuildingType.getImagePattern("Oil Smelter"));
        mainBarPane.getChildren().add(industrialCenterPane1);
    }

    private void setIndustrialCenterPane2() {
        industrialCenterPane2 = getBarPane();
//        industrialCenterPane2.setBackground(Background.fill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/menu/temp.png")).toExternalForm()))));
        industrialCenterPane2.setVisible(false);
        addClickableToRectangle(industrialCenterPane2, BuildingType.getImagePattern("Apple Orchard"), BuildingType.getImagePattern("Diary Farmer"),
                BuildingType.getImagePattern("Hops Farmer"), BuildingType.getImagePattern("Wheat Farmer"));
        mainBarPane.getChildren().add(industrialCenterPane2);
    }

    private void setIndustrialCenterPane3() {
        industrialCenterPane3 = getBarPane();
//        industrialCenterPane3.setBackground(Background.fill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/menu/temp.png")).toExternalForm()))));
        industrialCenterPane3.setVisible(false);
        addClickableToRectangle(industrialCenterPane3, BuildingType.getImagePattern("Armourer"), BuildingType.getImagePattern("Blacksmith"),
                BuildingType.getImagePattern("Fletcher"), BuildingType.getImagePattern("Pole Turner"));
        mainBarPane.getChildren().add(industrialCenterPane3);
    }

    private void setRecruitmentCenterPane() {
        recruitmentCenterPane = getBarPane();
//        recruitmentCenterPane.setBackground(Background.fill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/menu/temp.png")).toExternalForm()))));
        recruitmentCenterPane.setVisible(false);
        addClickableToRectangle(recruitmentCenterPane, BuildingType.getImagePattern("Barrack"), BuildingType.getImagePattern("Mercenary Post"), BuildingType.getImagePattern("Engineer Guild"));
        mainBarPane.getChildren().add(recruitmentCenterPane);
    }

    private void setStoragePane() {
        storagePane = getBarPane();
//        storagePane.setBackground(Background.fill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/menu/temp.png")).toExternalForm()))));
        storagePane.setVisible(true);
        addClickableToRectangle(storagePane, BuildingType.getImagePattern("Armoury"), BuildingType.getImagePattern("Stockpile"), BuildingType.getImagePattern("Granary"));
        mainBarPane.getChildren().add(storagePane);
    }

    private void setTrapPane() {
        trapPane = getBarPane();
//        trapPane.setBackground(Background.fill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/menu/temp.png")).toExternalForm()))));
        trapPane.setVisible(false);
        addClickableToRectangle(trapPane, BuildingType.getImagePattern("Pitch Ditch")); // BuildingType.getImagePattern("Killing Pit")
        mainBarPane.getChildren().add(trapPane);
    }
}