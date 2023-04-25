package view;

import controller.Controller;
import controller.MapMenuController;

import java.util.ArrayList;

public class MapMenu extends Menu {
    private final MapMenuController mapMenuController;
    public MapMenu(Controller controller) {
        super(controller);
        this.mapMenuController = new MapMenuController(controller);
    }

    /*private enum Commands {
        LOGIN(new ArrayList<>("command", "-i asd", "-jasd"));

        private final ArrayList<String> regex;

        private Commands(ArrayList<String> regex) {
            this.regex = regex;
        }

        public static getMatcher(String input, Command command) {

        }
    }*/

    @Override
    public void run() {

    }
}

