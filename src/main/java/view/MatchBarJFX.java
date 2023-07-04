package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Buildings.BuildingType;
import model.People.PeopleType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class MatchBarJFX {
    private final MatchMenuJFX matchMenuJFX;
    private final AnchorPane viewPane;
    private AnchorPane mainBarPane, normalBuildingsPane, gateHousePane, industrialCenterPane1, industrialCenterPane2,
            industrialCenterPane3, recruitmentCenterPane, storagePane, trapPane, peoplePane1, peoplePane2, peoplePane3;
    private Rectangle normalButton, gateHouseButton, industrialButton, recruitmentButton, storageButton, trapButton;
    private ArrayList<AnchorPane> buildingAnchorPanes, peopleAnchorPanes;
    private double selectBarWidth, selectBarHeight, selectBarX, selectBarY;
    private ImagePattern selectedBuildingImagePattern, selectedPeopleImagePattern;
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
        setPeoplePanes();
        buildingAnchorPanes = new ArrayList<>(Arrays.asList(normalBuildingsPane, gateHousePane, industrialCenterPane1, recruitmentCenterPane, storagePane, trapPane));
    }

    private void addClickableToRectangle(AnchorPane pane, ImagePattern... imagePatterns) {
        double clickableWidths = 0;
        ArrayList<Rectangle> rectangles = new ArrayList<>();
        double lastX = 20, distance = 20;
        double ratio = 1;
        for (int i = 0; i < imagePatterns.length; i++) {
            rectangles.add(new Rectangle(imagePatterns[i].getImage().getWidth(), imagePatterns[i].getImage().getWidth()));
        }
        for (Rectangle rectangle : rectangles) {
            clickableWidths += rectangle.getWidth();
            ratio = Math.min(ratio, pane.getPrefHeight() / rectangle.getHeight());
        }

        ratio = Math.min(ratio, (pane.getPrefWidth() - (imagePatterns.length + 1) * 20) / (clickableWidths));

        for (int i = 0; i < imagePatterns.length; i++) {
            rectangles.get(i).setX(lastX);
            rectangles.get(i).setY(pane.getPrefHeight() / 2 - ratio * rectangles.get(i).getHeight() / 2);
            System.out.println(rectangles.get(i).getY() + ratio * rectangles.get(i).getHeight() / 2);
            rectangles.get(i).setWidth(ratio * rectangles.get(i).getWidth());
            rectangles.get(i).setHeight(ratio * rectangles.get(i).getHeight());
            lastX += rectangles.get(i).getWidth() + distance;

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
                    if (!peopleAnchorPanes.contains(currentPane)) selectedBuildingImagePattern = imagePatterns[finalI];
                    else selectedPeopleImagePattern = imagePatterns[finalI];
                }
            });
            rectangles.get(i).setFill(imagePatterns[i]);
            pane.getChildren().add(rectangles.get(i));
        }
    }

    public ImagePattern getSelectedBuildingImagePattern() {
        return selectedBuildingImagePattern;
    }

    public ImagePattern getSelectedPeopleImagePattern() {
        return selectedPeopleImagePattern;
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
        selectBarX = 318.6666666666667 / 1452.0 * viewPane.getPrefWidth();
        selectBarHeight = (203.12291666666658 - 73.78958333333333) / 720.0 * viewPane.getPrefHeight();
        selectBarWidth = (828.6666666666666 - 318.6666666666667) / 1452.0 * viewPane.getPrefWidth();

        System.out.println(viewPane.getPrefWidth() + " " + viewPane.getPrefHeight());

        addButtons();
        viewPane.getChildren().add(mainBarPane);
    }

    private void addButtons() {
        double lastX = selectBarX + 5;
        ArrayList<ImagePattern> buildingImagePatterns = new ArrayList<>();
        ArrayList<Rectangle> buildingRectangles = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            buildingImagePatterns.add(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/menu/" + (i * 3 + 1) + ".png")).toExternalForm())));
            buildingImagePatterns.add(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/menu/" + (i * 3 + 2) + ".png")).toExternalForm())));
            buildingImagePatterns.add(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/menu/" + (i * 3 + 3) + ".png")).toExternalForm())));
            buildingRectangles.add(new Rectangle(lastX, mainBarPane.getPrefHeight() - buildingImagePatterns.get(i * 3).getImage().getHeight(), buildingImagePatterns.get(i * 3).getImage().getWidth(), buildingImagePatterns.get(i * 3).getImage().getHeight()));
            buildingRectangles.get(i).setFill(buildingImagePatterns.get(i * 3));
            int finalI = i;
            buildingRectangles.get(i).hoverProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                    if (t1) {
                        buildingRectangles.get(finalI).setFill(buildingImagePatterns.get(finalI * 3 + 1));
                    } else {
                        buildingRectangles.get(finalI).setFill(buildingImagePatterns.get(finalI * 3));
                    }
                }
            });
            buildingRectangles.get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    buildingRectangles.get(finalI).setFill(buildingImagePatterns.get(finalI * 3 + 2));
                    if (currentPane != null) currentPane.setVisible(false);
                    currentPane = buildingAnchorPanes.get(finalI);
                    currentPane.setVisible(true);
                }
            });
            mainBarPane.getChildren().add(buildingRectangles.get(i));
            lastX += buildingRectangles.get(i).getWidth() * 3 / 2;
        }

        ArrayList<ImagePattern> peopleImagePatterns = new ArrayList<>();
        ArrayList<Rectangle> peopleRectangles = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            peopleImagePatterns.add(PeopleType.getStandingImage(PeopleType.getTroopType(i * 6), 1));
            peopleRectangles.add(new Rectangle(5 + i * (peopleImagePatterns.get(i).getImage().getWidth() + 5),
                    mainBarPane.getPrefHeight() - peopleImagePatterns.get(i).getImage().getHeight() - 10,
                    peopleImagePatterns.get(i).getImage().getWidth(), peopleImagePatterns.get(i).getImage().getHeight()));
            peopleRectangles.get(i).setFill(peopleImagePatterns.get(i));
            int finalI = i;
            peopleRectangles.get(i).hoverProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                    peopleRectangles.get(finalI).setOpacity(1.8 - peopleRectangles.get(finalI).getOpacity());
                }
            });
            peopleRectangles.get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (currentPane != null) currentPane.setVisible(false);
                    currentPane = peopleAnchorPanes.get(finalI);
                    currentPane.setVisible(true);
                }
            });
            mainBarPane.getChildren().add(peopleRectangles.get(i));
        }
    }

    private AnchorPane getBarPane() {
        AnchorPane pane = new AnchorPane();
        pane.setPrefWidth(selectBarWidth);
        pane.setPrefHeight(selectBarHeight - 40);
        pane.setLayoutX(selectBarX);
        pane.setLayoutY(selectBarY);
        return pane;
    }

    public void setNormalBuildingsPane() {
        normalBuildingsPane = getBarPane();
        normalBuildingsPane.setVisible(false);
        addClickableToRectangle(normalBuildingsPane, BuildingType.getImagePattern("Drawbridge"),
                BuildingType.getImagePattern("Hovel"), BuildingType.getImagePattern("Shop"), BuildingType.getImagePattern("Inn"));
        mainBarPane.getChildren().add(normalBuildingsPane);
    }

    private void setGateHousePane() {
        gateHousePane = getBarPane();
        gateHousePane.setVisible(false);
        addClickableToRectangle(gateHousePane, BuildingType.getImagePattern("Tall Wall"), BuildingType.getImagePattern("Short Wall"),
                BuildingType.getImagePattern("Small Stone Gatehouse"), BuildingType.getImagePattern("Large Stone Gatehouse"),
                BuildingType.getImagePattern("Lookout Tower"), BuildingType.getImagePattern("Perimeter Tower"), BuildingType.getImagePattern("Defence Turret"),
                BuildingType.getImagePattern("Square Tower"), BuildingType.getImagePattern("Round Tower"));
        mainBarPane.getChildren().add(gateHousePane);
    }

    private void setIndustrialCenterPane1() {
        industrialCenterPane1 = getBarPane();
        industrialCenterPane1.setVisible(false);
        addClickableToRectangle(industrialCenterPane1, BuildingType.getImagePattern("Stable"), BuildingType.getImagePattern("Mill"),
                BuildingType.getImagePattern("Iron Mine"), BuildingType.getImagePattern("Pitch Rig"),
                BuildingType.getImagePattern("Quarry"), BuildingType.getImagePattern("Woodcutter"), BuildingType.getImagePattern("Oil Smelter"));
        mainBarPane.getChildren().add(industrialCenterPane1);
        addInnerButtons(industrialCenterPane1);
    }

    private void setIndustrialCenterPane2() {
        industrialCenterPane2 = getBarPane();
        industrialCenterPane2.setVisible(false);
        addClickableToRectangle(industrialCenterPane2, BuildingType.getImagePattern("Apple Orchard"), BuildingType.getImagePattern("Diary Farmer"),
                BuildingType.getImagePattern("Hops Farmer"), BuildingType.getImagePattern("Wheat Farmer"));
        mainBarPane.getChildren().add(industrialCenterPane2);
        addInnerButtons(industrialCenterPane2);
    }

    private void setIndustrialCenterPane3() {
        industrialCenterPane3 = getBarPane();
        industrialCenterPane3.setVisible(false);
        addClickableToRectangle(industrialCenterPane3, BuildingType.getImagePattern("Armourer"), BuildingType.getImagePattern("Blacksmith"),
                BuildingType.getImagePattern("Fletcher"), BuildingType.getImagePattern("Pole Turner"));
        mainBarPane.getChildren().add(industrialCenterPane3);
        addInnerButtons(industrialCenterPane3);
    }

    private void addInnerButtons(AnchorPane industrialCenterPane) {
        ArrayList<ImagePattern> industrialImagePatterns = new ArrayList<>();
        industrialImagePatterns.add(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/menu/" + (2 * 3 + 1) + ".png")).toExternalForm())));
        industrialImagePatterns.add(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/menu/" + (2 * 3 + 2) + ".png")).toExternalForm())));
        industrialImagePatterns.add(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResource("/menu/" + (2 * 3 + 3) + ".png")).toExternalForm())));
        ArrayList<Rectangle> industrialRectangles = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            industrialRectangles.add(new Rectangle(industrialCenterPane.getPrefWidth() -
                    industrialImagePatterns.get(0).getImage().getWidth(),
                    5 + i * (industrialImagePatterns.get(0).getImage().getHeight() + 5),
                    industrialImagePatterns.get(0).getImage().getWidth(),
                    industrialImagePatterns.get(0).getImage().getHeight()));
            industrialRectangles.get(i).setFill(industrialImagePatterns.get(0));
            int finalI = i;
            industrialRectangles.get(i).hoverProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                    if (t1) {
                        industrialRectangles.get(finalI).setFill(industrialImagePatterns.get(1));
                    } else {
                        industrialRectangles.get(finalI).setFill(industrialImagePatterns.get(0));
                    }
                }
            });
            industrialRectangles.get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    industrialRectangles.get(finalI).setFill(industrialImagePatterns.get(2));
                    if (currentPane != null) currentPane.setVisible(false);
                    switch (finalI) {
                        case 0:
                            currentPane = industrialCenterPane1;
                            break;
                        case 1:
                            currentPane = industrialCenterPane2;
                            break;
                        case 2:
                            currentPane = industrialCenterPane3;
                            break;
                    }
                    currentPane.setVisible(true);
                }
            });
            industrialCenterPane.getChildren().add(industrialRectangles.get(i));
        }
    }

    private void setRecruitmentCenterPane() {
        recruitmentCenterPane = getBarPane();
        recruitmentCenterPane.setVisible(false);
        addClickableToRectangle(recruitmentCenterPane, BuildingType.getImagePattern("Barrack"), BuildingType.getImagePattern("Mercenary Post"), BuildingType.getImagePattern("Engineer Guild"));
        mainBarPane.getChildren().add(recruitmentCenterPane);
    }

    private void setStoragePane() {
        storagePane = getBarPane();
        storagePane.setVisible(false);
        addClickableToRectangle(storagePane, BuildingType.getImagePattern("Armoury"), BuildingType.getImagePattern("Stockpile"), BuildingType.getImagePattern("Granary"));
        mainBarPane.getChildren().add(storagePane);
    }

    private void setTrapPane() {
        trapPane = getBarPane();
        trapPane.setVisible(false);


        addClickableToRectangle(trapPane, BuildingType.getImagePattern("Pitch Ditch")); // BuildingType.getImagePattern("Killing Pit")
        mainBarPane.getChildren().add(trapPane);
    }

    private void setPeoplePanes() {
        peoplePane1 = getBarPane();
        peoplePane2 = getBarPane();
        peoplePane3 = getBarPane();
        peopleAnchorPanes = new ArrayList<>(Arrays.asList(peoplePane1, peoplePane2, peoplePane3));
        for (int i = 0; i < peopleAnchorPanes.size(); i++) {
            ImagePattern[] peopleImages = new ImagePattern[6];
            for (int j = 0; j < peopleImages.length; j++) {
                peopleImages[j] = PeopleType.getStandingImage(PeopleType.getTroopType(i * 6 + j), 1);
            }
            addClickableToRectangle(peopleAnchorPanes.get(i), peopleImages);
            mainBarPane.getChildren().add(peopleAnchorPanes.get(i));
            peopleAnchorPanes.get(i).setVisible(false);
        }
    }

    public void deselect() {
        selectedBuildingImagePattern = null;
        selectedPeopleImagePattern = null;
    }
}