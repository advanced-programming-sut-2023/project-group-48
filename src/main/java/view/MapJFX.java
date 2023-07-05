package view;

import controller.Controller;
import controller.MapMenuController;
import controller.MatchMenuController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Buildings.Building;
import model.Buildings.BuildingType;
import model.Match.LandType;
import model.People.People;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class MapJFX {
    private final Controller controller;
    private final MapMenuController mapMenuController;
    private final MatchMenuController matchMenuController;
    private final MatchMenuJFX matchMenuJFX;
    private final SetTextureJFX setTextureJFX;
    private final BuildingSelectionJFX buildingSelectionJFX;
    private AnchorPane viewPane, tileStatusPane, peopleStatusPane;
    private Label tileStatus, peopleStatus;
    private final Pane mapPane;
    private Tile[][] map;
    private ArrayList<Tile> cells;
    private Rectangle selectionArea;
    private ArrayList<Tile> selectedTiles;
    private ArrayList<PeopleShape> selectedPeopleShapes;
    private Tile selectedSingleTile;
    private BuildingShape selectedSingleBuilding;
    private Rectangle miniMap;
    private final int MAP_SIZE = 200;
    private int firstI = MAP_SIZE * 2 - 2, lastI = 0, firstJ = MAP_SIZE - 1, lastJ = 0;

    public MapJFX(Controller controller, MapMenuController mapMenuController, MatchMenuController matchMenuController,
                  MatchMenuJFX matchMenuJFX, AnchorPane viewPane) throws IOException {
        this.controller = controller;
        this.mapMenuController = mapMenuController;
        this.matchMenuController = matchMenuController;
        this.matchMenuJFX = matchMenuJFX;
        this.buildingSelectionJFX = new BuildingSelectionJFX(controller, matchMenuController, this);
        this.viewPane = viewPane;
        this.mapPane = new Pane();
        setTileStatusProperties();
        setPeopleStatusProperties();
        setMapPane();
        setMap();
        setCells();
        setSelectionArea();
        setMiniMap();
        this.setTextureJFX = new SetTextureJFX(matchMenuController, viewPane, mapPane);
    }

    private void setPeopleStatusProperties() {
        peopleStatusPane = new AnchorPane();
        peopleStatusPane.setPrefWidth(200);
        peopleStatusPane.setPrefHeight(200);
        peopleStatusPane.setBackground(Background.fill(Color.PERU));
        peopleStatus = new Label();
        peopleStatus.setLayoutX(0);
        peopleStatus.setLayoutY(0);
        peopleStatus.setPrefWidth(200);
        peopleStatus.setPrefHeight(200);
        peopleStatus.setAlignment(Pos.CENTER);
        peopleStatusPane.getChildren().add(peopleStatus);
    }

    private void setMiniMap() {
        miniMap = new Rectangle(140, 70);
        mapPane.getChildren().add(miniMap);
        refreshMiniMap();
    }

    public void refreshMiniMap() {
        miniMap.setFill(new ImagePattern(matchMenuJFX.getScene().snapshot(new WritableImage((int) viewPane.getPrefWidth(),
                                (int) (viewPane.getPrefHeight() - 200)))));
        miniMap.setLayoutX(viewPane.getPrefWidth() - mapPane.getLayoutX() - miniMap.getWidth());
        miniMap.setLayoutY(- mapPane.getLayoutY());
        miniMap.toFront();
    }

    private void setTileStatusProperties() {
        tileStatusPane = new AnchorPane();
        tileStatusPane.setPrefWidth(200);
        tileStatusPane.setPrefHeight(200);
        tileStatusPane.setBackground(Background.fill(Color.PERU));
        tileStatus = new Label();
        tileStatus.setLayoutX(0);
        tileStatus.setLayoutY(0);
        tileStatus.setPrefWidth(200);
        tileStatus.setPrefHeight(200);
        tileStatus.setAlignment(Pos.CENTER);
        tileStatusPane.getChildren().add(tileStatus);
    }

    private void setMapPane() {
        mapPane.setPrefWidth((MAP_SIZE + 0.5) * Tile.WIDTH);
        mapPane.setPrefHeight((MAP_SIZE * 2) * Tile.HEIGHT / 2);
        mapPane.setLayoutX(viewPane.getPrefWidth() / 2 - mapPane.getPrefWidth() / 2);
        mapPane.setLayoutY(viewPane.getPrefHeight() / 2 - mapPane.getPrefHeight() / 2);
        Rectangle rectangle = new Rectangle(0, 0, mapPane.getPrefWidth(), mapPane.getPrefHeight());
        rectangle.setStroke(Color.BLACK);
        mapPane.getChildren().add(rectangle);
        viewPane.getChildren().add(mapPane);

        setMapActions();
    }

    private void setMapActions() {
        mapPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                System.out.println(keyEvent.getCode().toString());
                switch (keyEvent.getCode()) {
                    case RIGHT:
                        mapPane.setLayoutX(mapPane.getLayoutX() - Tile.WIDTH);
                        break;
                    case LEFT:
                        mapPane.setLayoutX(mapPane.getLayoutX() + Tile.WIDTH);
                        break;
                    case UP:
                        mapPane.setLayoutY(mapPane.getLayoutY() + Tile.HEIGHT / 2);
                        break;
                    case DOWN:
                        mapPane.setLayoutY(mapPane.getLayoutY() - Tile.HEIGHT / 2);
                        break;
                    case Z:
                        mapPane.setScaleX(mapPane.getScaleX() > 0.59049 ? 0.9 * mapPane.getScaleX() : 1 * mapPane.getScaleX());
                        mapPane.setScaleY(mapPane.getScaleY() > 0.59049 ? 0.9 * mapPane.getScaleY() : 1 * mapPane.getScaleY());
                        break;
                    case X:
                        mapPane.setScaleX(mapPane.getScaleX() < 1.693508780843029 ? 1 / 0.9 * mapPane.getScaleX() : 1 * mapPane.getScaleX());
                        mapPane.setScaleY(mapPane.getScaleY() < 1.693508780843029 ? 1 / 0.9 * mapPane.getScaleY() : 1 * mapPane.getScaleY());
                        break;
                    case T:
                        if (!selectedTiles.isEmpty()) {
                            setTextureJFX.setSelectedTiles(selectedTiles);
                            setTextureJFX.popOutSetTexture();
                        }
                        break;
                    case ESCAPE:
                        deSelect();
                        break;
                    case C:
                        if (selectedSingleBuilding != null)
                            matchMenuJFX.getMatchBarJFX().addToClipBoard(selectedSingleBuilding);
                        break;
                    case V:
                        paste();
                        break;
                }
                if (keyEvent.getCode().equals(KeyCode.RIGHT) || keyEvent.getCode().equals(KeyCode.DOWN)
                        || keyEvent.getCode().equals(KeyCode.LEFT) || keyEvent.getCode().equals(KeyCode.UP)) {
                    addNewCells(keyEvent);
                    removeOldCells(keyEvent);
                    bringBuildingsToFront();
                    refreshMiniMap();
                }
            }
        });
    }

    private void paste() {
        if (selectedSingleTile != null) {
            placeBuilding(selectedSingleTile);
        }
    }

    private void setMap() {
        map = new Tile[MAP_SIZE * 2 - 1][MAP_SIZE + 1];
        cells = new ArrayList<>();
        for (int i = 0; i < MAP_SIZE * 2 - 1; i++) {
            for (int j = 0; j < MAP_SIZE + 1; j++) {
                double x = Tile.WIDTH / 2 + j * Tile.WIDTH + ((i % 2 == 0) ? 0 : Tile.WIDTH / 2);
                double y = mapPane.getPrefHeight() - Tile.HEIGHT / 2 - i * (Tile.HEIGHT / 2);
                map[i][j] = new Tile(x, y, i, j);
                map[i][j].setOpacity(0.4);

                setTile(map[i][j]);

                if (isInBorder(map[i][j])) {
                    mapPane.getChildren().add(map[i][j]);
                    firstI = Math.min(firstI, i);
                    firstJ = Math.min(firstJ, j);
                    lastI = Math.max(lastI, i);
                    lastJ = Math.max(lastJ, j);
                }
            }
        }
    }

    private void setTile(Tile tile) {
        tile.setFill(LandType.LAND.getImagePattern());
        tile.setStroke(Color.BLACK);
        tile.setStrokeWidth(0);
    }

    private void setCells() {
        for (int row = 1; row <= MAP_SIZE; row++) {
            for (int column = 1; column <= MAP_SIZE; column++) {
                int[] coordinates = getCoordinates(row, column);
                Tile tile = map[coordinates[0]][coordinates[1]];
                tile.setCell(controller.getGame().getCurrentMatch().getCell(row, column));
                tile.setOpacity(1);
                cells.add(map[coordinates[0]][coordinates[1]]);
                setCell(tile);
            }
        }
    }

    private void setCell(Tile tile) {
        tile.hoverProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (!t1) {
                    mapPane.getChildren().remove(tileStatusPane);
                    tile.setOpacity(1);
                } else if (!mapPane.getChildren().contains(selectionArea) && tile.getCell() != null) {
                    tileStatus.setText(mapMenuController.showCellDetails(tile.getCell().getRow(), tile.getCell().getColumn()));
                    locatePane(tileStatusPane, tile.getX(), tile.getY());
                    mapPane.getChildren().add(tileStatusPane);
                    tile.setOpacity(0.8);
                }
            }
        });
        tile.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (matchMenuJFX.getMatchBarJFX().getSelectedBuildingImagePattern() != null) {
                    placeBuilding(tile);
                } else if (!selectedPeopleShapes.isEmpty()) {
                    movePeople(tile);
                } else {
                    deSelect();
                    selectedSingleTile = tile;
                    selectedSingleTile.setStrokeWidth(1);
                }
            }
        });
    }

    private void placeBuilding(Tile tile) {
        matchMenuController.dropBuilding(tile.getCell().getRow(), tile.getCell().getColumn(),
                BuildingType.getTypeByImagePattern(matchMenuJFX.getMatchBarJFX().getSelectedBuildingImagePattern()));
        Building building = tile.getCell().getBuilding();
        addBuildingToMap(tile.getI(), tile.getJ(), matchMenuJFX.getMatchBarJFX().getSelectedBuildingImagePattern(), building);
    }

    private void placePeople(Tile tile) {
        String type = Pattern.compile(".+Color \\d/(?<type>.+)/.+").
                matcher(matchMenuJFX.getMatchBarJFX().getSelectedBuildingImagePattern().getImage().getUrl()).group("type");
        matchMenuController.createUnit(type, 1);
        People people = tile.getCell().getPeople().get(tile.getCell().getPeople().size() - 1);
        addPeopleToMap(tile.getI(), tile.getJ(), matchMenuJFX.getMatchBarJFX().getSelectedBuildingImagePattern(), people);
    }

    private void movePeople(Tile tile) {
        matchMenuController.moveUnit(tile.getCell().getRow(), tile.getCell().getColumn());
        for (PeopleShape selectedPerson : selectedPeopleShapes) {
            // Animations
        }
    }

    private void selectBuilding(Tile tile) {
        matchMenuController.selectBuilding(tile.getCell().getRow(), tile.getCell().getColumn());
    }

    private void setSelectionArea() {
        this.selectedTiles = new ArrayList<>();
        this.selectedPeopleShapes = new ArrayList<>();
        this.selectionArea = new Rectangle();
        selectionArea.setFill(Color.DARKGRAY);
        selectionArea.setOpacity(0.1);
        final MouseEvent[] pressedEvent = {null};
        mapPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                pressedEvent[0] = mouseEvent;
                selectionArea.setX(mouseEvent.getX());
                selectionArea.setY(mouseEvent.getY());
                mapPane.getChildren().add(selectionArea);
                selectionArea.toFront();
            }
        });
        mapPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selectionArea.setWidth(Math.abs(mouseEvent.getX() - pressedEvent[0].getX()));
                selectionArea.setHeight(Math.abs(mouseEvent.getY() - pressedEvent[0].getY()));
                selectionArea.setX(Math.min(mouseEvent.getX(), pressedEvent[0].getX()));
                selectionArea.setY(Math.min(mouseEvent.getY(), pressedEvent[0].getY()));
                select();
            }
        });
        mapPane.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selectionArea.setWidth(0);
                selectionArea.setHeight(0);
                mapPane.getChildren().remove(selectionArea);
            }
        });
    }

    private void select() {
        for (Tile cellTile : cells) {
            if (selectionArea.getBoundsInParent().intersects(cellTile.getBoundsInParent())) {
                selectedTiles.add(cellTile);
                selectedPeopleShapes.addAll(cellTile.getPeopleShapes());
            }
        }
        if (selectedPeopleShapes.isEmpty()) {
            for (Tile selectedTile : selectedTiles) {
                selectedTile.setOpacity(0.8);
            }
        } else {
            matchMenuController.selectUnit(selectedPeopleShapes);
            for (PeopleShape selectedPersonShape : selectedPeopleShapes) {
                selectedPersonShape.setOpacity(0.8);
            }
            selectedTiles.clear();
        }
    }

    public void deSelect() {
        if (selectedPeopleShapes.isEmpty()) {
            for (Tile selectedTile : selectedTiles) {
                selectedTile.setOpacity(1);
            }
            selectedTiles.clear();
        } else {
            matchMenuController.disbandUnit();
            for (PeopleShape selectedPersonShape : selectedPeopleShapes) {
                selectedPersonShape.setOpacity(1);
            }
            selectedPeopleShapes.clear();
        }
        if (selectedSingleBuilding != null) selectedSingleBuilding.setOpacity(1);
        selectedSingleBuilding = null;
        if (selectedSingleTile != null) {
            selectedSingleTile.setOpacity(1);
            selectedSingleTile.setStrokeWidth(0);
        }
        selectedSingleTile = null;
        matchMenuJFX.getMatchBarJFX().deselect();
        buildingSelectionJFX.popOff();
        mapPane.requestFocus();
    }

    private void locatePane(Pane pane, double locationX, double locationY) {
        double x = Math.max(locationX - tileStatusPane.getPrefWidth(), mapPane.getLayoutX());
        x = Math.min(x, mapPane.getPrefWidth() - tileStatusPane.getPrefWidth());
        tileStatusPane.setLayoutX(x);
        double y = Math.max(locationY - tileStatusPane.getPrefHeight(), mapPane.getLayoutY());
        y = Math.min(y, mapPane.getPrefHeight() - tileStatusPane.getPrefHeight());
        tileStatusPane.setLayoutY(y);
    }

    public Pane getMapPane() {
        return mapPane;
    }

    public void addBuildingToMap(int i, int j, ImagePattern imagePattern, Building building) {
        BuildingShape rectangle = new BuildingShape(Tile.WIDTH, imagePattern.getImage().getHeight() / imagePattern.getImage().getWidth() * Tile.WIDTH, building);
        rectangle.setLayoutX(map[i][j].getX() - rectangle.getWidth() / 2);
        rectangle.setLayoutY(map[i][j].getY() + Tile.HEIGHT / 2 - rectangle.getHeight());
        rectangle.setFill(imagePattern);
        map[i][j].setRectangle(rectangle);
        int index = mapPane.getChildren().size();
        if (i % 2 == 0 && map[i - 1][j - 1].getRectangle() != null)
            index = Math.min(index, mapPane.getChildren().indexOf(map[i - 1][j - 1].getRectangle()));
        if (i % 2 == 0 && map[i - 1][j].getRectangle() != null)
            index = Math.min(index, mapPane.getChildren().indexOf(map[i - 1][j].getRectangle()));
        if (i % 2 == 1 && map[i - 1][j].getRectangle() != null)
            index = Math.min(index, mapPane.getChildren().indexOf(map[i - 1][j].getRectangle()));
        if (i % 2 == 1 && map[i - 1][j + 1].getRectangle() != null)
            index = Math.min(index, mapPane.getChildren().indexOf(map[i - 1][j + 1].getRectangle()));
        if (index != mapPane.getChildren().size()) mapPane.getChildren().add(index, rectangle);
        else mapPane.getChildren().add(rectangle);
        rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                deSelect();
                selectedSingleBuilding = rectangle;
                matchMenuController.selectBuilding(rectangle.getBuilding().getRow(), rectangle.getBuilding().getColumn());
                selectedSingleBuilding.setOpacity(0.8);
                buildingSelectionJFX.popOut(rectangle.getLayoutX(), rectangle.getLayoutY(), selectedSingleBuilding);
            }
        });
    }

    public void addPeopleToMap(int i, int j, ImagePattern imagePattern, People people) {
        PeopleShape rectangle = new PeopleShape(Tile.WIDTH, imagePattern.getImage().getHeight() / imagePattern.getImage().getWidth() * Tile.WIDTH, people);
        rectangle.setLayoutX(map[i][j].getX() - rectangle.getWidth() / 2);
        rectangle.setLayoutY(map[i][j].getY() + Tile.HEIGHT / 2 - rectangle.getHeight());
        rectangle.setFill(imagePattern);
        mapPane.getChildren().add(rectangle);
        map[i][j].addPeopleShape(rectangle);
        rectangle.hoverProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (!t1) {
                    mapPane.getChildren().remove(peopleStatusPane);
                    rectangle.setOpacity(1);
                } else {
                    peopleStatus.setText(matchMenuController.showPeopleDetails(rectangle.getPeople()));
                    locatePane(peopleStatusPane, rectangle.getLayoutX(), rectangle.getLayoutY());
                    mapPane.getChildren().add(peopleStatusPane);
                    rectangle.setOpacity(0.8);
                }
            }
        });
        rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                deSelect();

                for (PeopleShape personShape : map[i][j].getPeopleShapes()) {
                    selectedPeopleShapes.add(personShape);
                    personShape.setOpacity(0.8);
                }
            }
        });
    }

    public void bringBuildingsToFront() {
        for (int i = firstI; i <= lastI; i++) {
            for (int j = firstJ; j <= lastJ; j++) {
                if (map[i][j].getRectangle() != null) {
                    map[i][j].getRectangle().toFront();
                }
            }
        }
    }

    public void addNewCells(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.RIGHT) || keyEvent.getCode().equals(KeyCode.LEFT)) {
            boolean isRight = keyEvent.getCode().equals(KeyCode.RIGHT);
            for (int j = isRight ? lastJ : firstJ; j != (isRight ? 200 : -1); j += isRight ? 1 : -1) {
                for (int i = firstI; i <= lastI; i++) {
                    if (isInBorder(map[i][j])) {
                        if (!mapPane.getChildren().contains(map[i][j])) addCellToPane(map[i][j]);
                    } else {
                        if (isRight) lastJ = j - 1;
                        else firstJ = j + 1;
                        return;
                    }
                }
            }
        } else {
            boolean isUp = keyEvent.getCode().equals(KeyCode.UP);
            for (int i = isUp ? lastI : firstI; i != (isUp ? 200 : -1); i += isUp ? 1 : -1) {
                for (int j = firstJ; j <= lastJ; j++) {
                    if (isInBorder(map[i][j])) {
                        if (!mapPane.getChildren().contains(map[i][j])) addCellToPane(map[i][j]);
                    } else {
                        if (isUp) lastI = i - 1;
                        else firstI = i + 1;
                        return;
                    }
                }
            }
        }
    }

    private void addCellToPane(Tile tile) {
        mapPane.getChildren().add(tile);
        if (tile.getRectangle() != null) {
            mapPane.getChildren().add(tile.getRectangle());
        }
    }

    public void removeOldCells(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.RIGHT) || keyEvent.getCode().equals(KeyCode.LEFT)) {
            boolean isRight = keyEvent.getCode().equals(KeyCode.RIGHT);
            for (int j = isRight ? firstJ : lastJ; j != (isRight ? 200 : -1); j += isRight ? 1 : -1) {
                for (int i = 0; i < 200; i++) {
                    if (!isInBorder(map[i][j])) removeCellFromPane(map[i][j]);
                    else {
                        if (isRight) firstJ = j;
                        else lastJ = j;
                        return;
                    }
                }
            }
        } else {
            boolean isUp = keyEvent.getCode().equals(KeyCode.UP);
            for (int i = isUp ? firstI : lastI; i != (isUp ? 200 : -1); i += isUp ? 1 : -1) {
                for (int j = 0; j < 200; j++) {
                    if (!isInBorder(map[i][j])) removeCellFromPane(map[i][j]);
                    else {
                        if (isUp) firstI = i;
                        else lastI = i;
                        return;
                    }
                }
            }
        }
    }

    private void removeCellFromPane(Tile tile) {
        mapPane.getChildren().remove(tile);
        if (tile.getRectangle() != null) {
            mapPane.getChildren().remove(tile.getRectangle());
            if (tile.getRectangle() != null) mapPane.getChildren().remove(tile);
        }
    }

    private boolean isInBorder(Tile tile) {
        return (tile.getX() + mapPane.getLayoutX() >= -Tile.WIDTH) && (tile.getX() + mapPane.getLayoutX() - viewPane.getPrefWidth() <= Tile.WIDTH)
                && (tile.getY() + mapPane.getLayoutY() >= -Tile.HEIGHT) && (tile.getY() + mapPane.getLayoutY() - viewPane.getPrefHeight() <= Tile.HEIGHT);
    }

    public int[] getCoordinates(int row, int column) {
        int i = MAP_SIZE - 1, j = 0;
        for (int k = 0; k < row - 1; k++) {
            j += (i % 2 == 0) ? 0 : 1;
            i++;
        }
        for (int k = 0; k < column - 1; k++) {
            j += (i % 2 == 0) ? 0 : 1;
            i--;
        }
        return new int[]{i, j};
    }

    public Tile getTile(int i, int j) {
        return map[i][j];
    }

    public void setTileSicked(int row, int column) {
        int[] coordinates = getCoordinates(row, column);
        map[coordinates[0]][coordinates[1]].setStroke(Color.RED);
        map[coordinates[0]][coordinates[1]].setStrokeWidth(2);
    }

    public void setTileCured(int row, int column) {
        int[] coordinates = getCoordinates(row, column);
        map[coordinates[0]][coordinates[1]].setStroke(Color.BLACK);
        map[coordinates[0]][coordinates[1]].setStrokeWidth(0);
    }
}