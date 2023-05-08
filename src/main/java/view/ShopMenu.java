package view;

import controller.Controller;
import controller.ShopMenuController;
import view.Commands.ShopMenuCommands;
import view.Messages.ShopMenuMessages;

import java.util.regex.Matcher;

public class ShopMenu extends Menu {
    private final ShopMenuController shopMenuController;
    public ShopMenu(Controller controller) {
        super(controller);
        this.shopMenuController = new ShopMenuController(controller);
    }

    //private enum Commands {}

    @Override
    public void run() {
        while (true){
            String command = scanner.nextLine().trim();
            Matcher matcher;
            if (command.matches("^show\\s+price\\s+list$")){

            }
            else if ((matcher = ShopMenuCommands.getMatcher(command, ShopMenuCommands.BUY)) != null){

            }
            else if ((matcher = ShopMenuCommands.getMatcher(command, ShopMenuCommands.SELL)) != null){

            }
            else
                System.out.println("Invalid Command!");
        }
    }
}
