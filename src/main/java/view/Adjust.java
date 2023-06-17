package view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Adjust {


    public static void adjustLabel(Label label, double ratioX, double ratioY) {
        label.setPrefWidth(label.getPrefWidth() * ratioX);
        label.setPrefHeight(label.getPrefHeight() * ratioY);
        label.setLayoutX(label.getLayoutX() * ratioX);
        label.setLayoutY(label.getLayoutY() * ratioY);
        label.setFont(Font.font(ratioX * label.getFont().getSize()));
    }

    public static void adjustTextField(TextField textField, double ratioX, double ratioY) {
        textField.setPrefWidth(textField.getPrefWidth() * ratioX);
        textField.setPrefHeight(textField.getPrefHeight() * ratioY);
        textField.setLayoutX(textField.getLayoutX() * ratioX);
        textField.setLayoutY(textField.getLayoutY() * ratioY);
        textField.setFont(Font.font(ratioX * textField.getFont().getSize()));
    }

    public static void adjustPasswordField(PasswordField passwordField, double ratioX, double ratioY) {
        passwordField.setPrefWidth(passwordField.getPrefWidth() * ratioX);
        passwordField.setPrefHeight(passwordField.getPrefHeight() * ratioY);
        passwordField.setLayoutX(passwordField.getLayoutX() * ratioX);
        passwordField.setLayoutY(passwordField.getLayoutY() * ratioY);
        passwordField.setFont(Font.font(ratioX * passwordField.getFont().getSize()));
    }

    public static void adjustChoiceBox(ChoiceBox box, double ratioX, double ratioY) {
        box.setPrefWidth(box.getPrefWidth() * ratioX);
        box.setPrefHeight(box.getPrefHeight() * ratioY);
        box.setLayoutX(box.getLayoutX() * ratioX);
        box.setLayoutY(box.getLayoutY() * ratioY);
    }

    public static void adjustCheckBox(CheckBox box, double ratioX, double ratioY) {
        System.out.println("Before box" + box.getPrefWidth() + " " + box.getPrefHeight());
        box.setPrefWidth(box.getPrefWidth() * ratioX);
        box.setPrefHeight(box.getPrefHeight() * ratioY);
        box.setLayoutX(box.getLayoutX() * ratioX);
        box.setLayoutY(box.getLayoutY() * ratioY);
        System.out.println("After box" + box.getPrefWidth() + " " + box.getPrefHeight());
    }

    public static void adjustHyperlink(Hyperlink link, double ratioX, double ratioY) {
        System.out.println("Before link" + link.getPrefWidth() + " " + link.getPrefHeight());
        link.setPrefWidth(link.getPrefWidth() * ratioX);
        link.setPrefHeight(link.getPrefHeight() * ratioY);
        link.setLayoutX(link.getLayoutX() * ratioX);
        link.setLayoutY(link.getLayoutY() * ratioY);
        link.setFont(Font.font(ratioX * link.getFont().getSize()));
        System.out.println("After link" + link.getPrefWidth() + " " + link.getPrefHeight());
    }

    public static void adjustRectangle(Rectangle rectangle, double ratioX, double ratioY) {
        rectangle.setWidth(rectangle.getWidth() * ratioX);
        rectangle.setHeight(rectangle.getHeight() * ratioY);
        rectangle.setLayoutX(rectangle.getLayoutX() * ratioX);
        rectangle.setLayoutY(rectangle.getLayoutY() * ratioY);
    }
    public static void adjustCircle(Circle circle, double ratioX, double ratioY) {
        circle.setRadius(circle.getRadius() * ratioX);
        circle.setLayoutX(circle.getLayoutX() * ratioX);
        circle.setLayoutY(circle.getLayoutY() * ratioY);
    }

    public static void adjustButton(Button button, double ratioX, double ratioY) {
        button.setPrefWidth(button.getPrefWidth() * ratioX);
        button.setPrefHeight(button.getPrefHeight() * ratioY);
        button.setLayoutX(button.getLayoutX() * ratioX);
        button.setLayoutY(button.getLayoutY() * ratioY);
        button.setFont(Font.font(ratioX * button.getFont().getSize()));
    }

    public static void adjustPane(Pane pane, double ratioX, double ratioY) {
        pane.setPrefWidth(pane.getPrefWidth() * ratioX);
        pane.setPrefHeight(pane.getPrefHeight() * ratioY);
        pane.setLayoutX(pane.getLayoutX() * ratioX);
        pane.setLayoutY(pane.getLayoutY() * ratioY);
    }
}