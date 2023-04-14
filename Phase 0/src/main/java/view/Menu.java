package view;

import java.util.Scanner;

public abstract class Menu {
    public final static Scanner scanner = new Scanner(System.in);
    public abstract void run();
}