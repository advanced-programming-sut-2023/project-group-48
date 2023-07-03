package view;

import controller.MatchMenuController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;

public class PopularityJFX {
    private final MatchMenuController matchMenuController;
    private AnchorPane popularityPane;
    private Label myInfoLabel, popularityFactorsLabel, foodRateLabel, foodRateErrorLabel, fearRateLabel, fearRateErrorLabel,
            taxRateLabel, taxRateErrorLabel;
    private TextField foodRateTextField, fearRateTextField, taxRateTextField;
    private Button setFoodRateButton, setFearRateButton, setTaxRateButton;
    private Separator[] separators;
    private ScrollPane foodListScrollPane;
    private Circle closeButton;

    public PopularityJFX(MatchMenuController matchMenuController) throws IOException {
        this.matchMenuController = matchMenuController;
        popularityPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Popularity.fxml")));
        setMyInfo();
        setPopularityFactors();
        setFoodRate();
        setFearRate();
        setTaxRate();
        setCloseButton();
        setSeparators();
        refresh();
    }

    private void setMyInfo() {
        myInfoLabel = (Label) popularityPane.getChildren().get(0);
    }

    private void setPopularityFactors() {
        popularityFactorsLabel = (Label) popularityPane.getChildren().get(1);
    }

    private void refresh() {
        popularityFactorsLabel.setText(matchMenuController.showPopularityFactors());
        myInfoLabel.setText(matchMenuController.showMyInfo());
    }

    private void setFoodRate() {
        foodListScrollPane = (ScrollPane) popularityPane.getChildren().get(2);
        foodRateLabel = (Label) popularityPane.getChildren().get(3);
        foodRateTextField = (TextField) popularityPane.getChildren().get(4);
        setFoodRateButton = (Button) popularityPane.getChildren().get(5);
        foodRateErrorLabel = (Label) popularityPane.getChildren().get(6);
        setFoodRateButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String result = matchMenuController.setFoodRate(Integer.parseInt(foodRateTextField.getText()));
                foodRateErrorLabel.setText(result);
                if (!result.contains("successfully"))
                    foodRateErrorLabel.setTextFill(Color.RED);
                else
                    foodRateErrorLabel.setTextFill(Color.GREEN);
            }
        });
        foodRateTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) { foodRateErrorLabel.setText(""); }
        });
        setFoodList();
    }

    private void setFoodList() {
        String foodList = matchMenuController.showFoodList();
        foodList.lines().forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                Label label = new Label(s);
                label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        String result = matchMenuController.setFoodRate(Integer.parseInt(s.split(" : ")[1]));
                        foodRateErrorLabel.setText(result);
                        if (!result.contains("successfully"))
                            foodRateErrorLabel.setTextFill(Color.RED);
                        else
                            foodRateErrorLabel.setTextFill(Color.GREEN);
                    }
                });
                foodListScrollPane.setContent(label);
            }
        });
    }

    private void setFearRate() {
        fearRateLabel = (Label) popularityPane.getChildren().get(7);
        fearRateTextField = (TextField) popularityPane.getChildren().get(8);
        setFearRateButton = (Button) popularityPane.getChildren().get(9);
        fearRateErrorLabel = (Label) popularityPane.getChildren().get(10);
        setFearRateButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String result = matchMenuController.setFearRate(Integer.parseInt(fearRateTextField.getText()));
                fearRateErrorLabel.setText(result);
                if (!result.contains("successfully"))
                    fearRateErrorLabel.setTextFill(Color.RED);
                else
                    fearRateErrorLabel.setTextFill(Color.GREEN);
            }
        });
        fearRateTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) { fearRateErrorLabel.setText(""); }
        });
    }

    private void setTaxRate() {
        taxRateLabel = (Label) popularityPane.getChildren().get(11);
        taxRateTextField = (TextField) popularityPane.getChildren().get(12);
        setTaxRateButton = (Button) popularityPane.getChildren().get(13);
        taxRateErrorLabel = (Label) popularityPane.getChildren().get(14);
        setTaxRateButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String result = matchMenuController.setTaxRate(Integer.parseInt(taxRateTextField.getText()));
                taxRateErrorLabel.setText(result);
                if (!result.contains("successfully"))
                    taxRateErrorLabel.setTextFill(Color.RED);
                else
                    taxRateErrorLabel.setTextFill(Color.GREEN);
            }
        });
        taxRateTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                taxRateErrorLabel.setText("");
            }
        });
    }

    private void setCloseButton() {
        closeButton = (Circle) popularityPane.getChildren().get(15);
        closeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                popOff();
            }
        });
    }

    private void setSeparators() {
        separators = new Separator[4];
        for (int i = 0; i < 4; i++) {
            separators[i] = (Separator) popularityPane.getChildren().get(i + 16);
        }
    }

    public void popOut() {
    }

    public void popOff() {
        ;
    }
}
