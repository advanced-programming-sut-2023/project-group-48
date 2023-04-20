package view;

import controller.Controller;

import java.util.Scanner;

public abstract class Menu {
    final static Scanner scanner = new Scanner(System.in);
    final Controller controller;

    protected Menu(Controller controller) {
        this.controller = controller;
    }

    public abstract void run();
}