package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Buildings.BuildingType;

import java.util.ArrayList;
import java.util.Objects;

public class MatchBarJFX {
    private AnchorPane viewPane;
    private AnchorPane mainBarPane, normalBuildingsPane, gateHousePane, industrialCenterPane1, industrialCenterPane2, industrialCenterPane3, recruitmentCenterPane, storagePane, trapPane;
    private double selectBarWidth, selectBarHeight, selectBarX, selectBarY;
    private ImagePattern selectedBuildingImagePattern;

    public MatchBarJFX(AnchorPane viewPane) {
        this.viewPane = viewPane;
        selectedBuildingImagePattern = null;
        setMainBarPane();
        setNormalBuildingsPane();
        setGateHousePane();
        setIndustrialCenterPane1();
        setIndustrialCenterPane2();
        setIndustrialCenterPane3();
        setRecruitmentCenterPane();
        setStoragePane();
        setTrapPane();
    }

    private void addClickableToRectangle(AnchorPane pane, ImagePattern... imagePatterns) {
        double clickableWidths = 0;
        ArrayList<Rectangle> rectangles = new ArrayList<>();
        for (ImagePattern imagePattern : imagePatterns) {
            clickableWidths += imagePattern.getImage().getWidth();
        }
        double distance = (pane.getPrefWidth() - clickableWidths) / (imagePatterns.length + 1);
        double lastX = 0;
        for (int i = 0; i < imagePatterns.length; i++) {
            rectangles.add(new Rectangle(distance + lastX, pane.getPrefHeight() / 2 - imagePatterns[i].getImage().getHeight() / 2, imagePatterns[i].getImage().getWidth(), imagePatterns[i].getImage().getHeight()));
            lastX = rectangles.get(i).getX() + rectangles.get(i).getWidth();
            int finalI = i;
            rectangles.get(i).hoverProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                    if (t1) {
                        rectangles.get(finalI).setOpacity(1);
                    } else {
                        rectangles.get(finalI).setOpacity(0.5);
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

    public void setSelectedBuildingImagePattern(ImagePattern selectedBuildingImagePattern) {
        this.selectedBuildingImagePattern = selectedBuildingImagePattern;
    }

    private void setMainBarPane() {
        ImagePattern mainBarImage = new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/menu/menu.png")).toExternalForm()));
        mainBarPane = new AnchorPane();
        mainBarPane.setPrefWidth(viewPane.getPrefWidth());
        mainBarPane.setPrefHeight(mainBarImage.getImage().getHeight() / mainBarImage.getImage().getWidth() * viewPane.getPrefWidth());
        mainBarPane.setLayoutX(0);
        mainBarPane.setLayoutY(viewPane.getPrefHeight() - mainBarPane.getPrefHeight());
        mainBarPane.setBackground(Background.fill(mainBarImage));
        selectBarY = mainBarPane.getLayoutY();
        selectBarX = mainBarPane.getLayoutX() + mainBarPane.getPrefWidth() / 3;
        selectBarHeight = mainBarPane.getHeight();
        selectBarWidth = mainBarPane.getPrefWidth() / 3;
        // TODO add buttons
        viewPane.getChildren().add(mainBarPane);
    }

    private AnchorPane getBarPane() {
        AnchorPane pane = new AnchorPane();
        pane.setPrefWidth(selectBarWidth);
        pane.setPrefHeight(selectBarHeight);
        pane.setLayoutX(selectBarX);
        pane.setLayoutY(selectBarY);
        return pane;
    }

    public void setNormalBuildingsPane() {
        normalBuildingsPane = getBarPane();
        normalBuildingsPane.setBackground(Background.fill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/menu/normalBuildings.png")).toExternalForm()))));
        normalBuildingsPane.setVisible(false);
        addClickableToRectangle(normalBuildingsPane, BuildingType.getImagePattern("Drawbridge"),
                BuildingType.getImagePattern("Hovel"), BuildingType.getImagePattern("Shop"), BuildingType.getImagePattern("Inn"));
        mainBarPane.getChildren().add(normalBuildingsPane);
    }

    private void setGateHousePane() {
        gateHousePane = getBarPane();
        gateHousePane.setBackground(Background.fill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/menu/adf.png")).toExternalForm()))));
        gateHousePane.setVisible(false);
        addClickableToRectangle(gateHousePane, BuildingType.getImagePattern("Tall Wall"), BuildingType.getImagePattern("Short Wall"),
                BuildingType.getImagePattern("Small Stone Gatehouse"), BuildingType.getImagePattern("Large Stone Gatehouse"),
                BuildingType.getImagePattern("Lookout Tower"), BuildingType.getImagePattern("Perimeter Tower"), BuildingType.getImagePattern("Defence Turret"),
                BuildingType.getImagePattern("Square Tower"), BuildingType.getImagePattern("Round Tower"));
        mainBarPane.getChildren().add(gateHousePane);
    }

    private void setIndustrialCenterPane1() {
        industrialCenterPane1 = getBarPane();
        industrialCenterPane1.setBackground(Background.fill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/menu/industrialCenter.png")).toExternalForm()))));
        industrialCenterPane1.setVisible(false);
        addClickableToRectangle(gateHousePane, BuildingType.getImagePattern("Stable"), BuildingType.getImagePattern("Mill"),
                BuildingType.getImagePattern("Iron Mine"), BuildingType.getImagePattern("Pitch Rig"),
                BuildingType.getImagePattern("Quarry"), BuildingType.getImagePattern("Woodcutter"), BuildingType.getImagePattern("Oil Smelter"));
        mainBarPane.getChildren().add(industrialCenterPane1);
    }

    private void setIndustrialCenterPane2() {
        industrialCenterPane2 = getBarPane();
        industrialCenterPane2.setBackground(Background.fill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/menu/industrialCenter.png")).toExternalForm()))));
        industrialCenterPane2.setVisible(false);
        addClickableToRectangle(gateHousePane, BuildingType.getImagePattern("Apple Orchard"), BuildingType.getImagePattern("Diary Farmer"),
                BuildingType.getImagePattern("Hops Farmer"), BuildingType.getImagePattern("Wheat Farmer"));
        mainBarPane.getChildren().add(industrialCenterPane2);
    }

    private void setIndustrialCenterPane3() {
        industrialCenterPane3 = getBarPane();
        industrialCenterPane3.setBackground(Background.fill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/menu/industrialCenter.png")).toExternalForm()))));
        industrialCenterPane3.setVisible(false);
        addClickableToRectangle(gateHousePane, BuildingType.getImagePattern("Armourer"), BuildingType.getImagePattern("Blacksmith"),
                BuildingType.getImagePattern("Fletcher"), BuildingType.getImagePattern("Pole Turner"));
        mainBarPane.getChildren().add(industrialCenterPane3);
    }

    private void setRecruitmentCenterPane() {
        recruitmentCenterPane = getBarPane();
        recruitmentCenterPane.setBackground(Background.fill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/menu/recruitmentCenter.png")).toExternalForm()))));
        recruitmentCenterPane.setVisible(false);
        addClickableToRectangle(normalBuildingsPane, BuildingType.getImagePattern("Barrack"), BuildingType.getImagePattern("Mercenary Post"), BuildingType.getImagePattern("Engineer Guild"));
        mainBarPane.getChildren().add(recruitmentCenterPane);
    }

    private void setStoragePane() {
        storagePane = getBarPane();
        storagePane.setBackground(Background.fill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/menu/storage.png")).toExternalForm()))));
        storagePane.setVisible(false);
        addClickableToRectangle(normalBuildingsPane, BuildingType.getImagePattern("Armoury"), BuildingType.getImagePattern("Stockpile"), BuildingType.getImagePattern("Granary"));
        mainBarPane.getChildren().add(storagePane);
    }

    private void setTrapPane() {
        trapPane = getBarPane();
        trapPane.setBackground(Background.fill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/menu/trap.png")).toExternalForm()))));
        trapPane.setVisible(false);
        addClickableToRectangle(normalBuildingsPane, BuildingType.getImagePattern("Killing Pit"), BuildingType.getImagePattern("Pitch Ditch"));
        mainBarPane.getChildren().add(trapPane);
    }
}
