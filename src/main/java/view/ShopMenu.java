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

    @Override
    public void run() {
        while (true){
            String command = scanner.nextLine().trim();
            Matcher matcher;
            if (command.matches("^back$")) {
                System.out.println(controller.enterMatchMenu());
                break;
            }
            else if (command.matches("^show\\s+price\\s+list$")){
                System.out.println(shopMenuController.showPriceList());
            }
            else if ((matcher = ShopMenuCommands.getMatcher(command, ShopMenuCommands.BUY)) != null){
                System.out.println(shopMenuController.buy(Controller.getRemovedQuotationMarks(matcher.group("itemname")), Integer.parseInt(matcher.group("itemamount"))));
            }
            else if ((matcher = ShopMenuCommands.getMatcher(command, ShopMenuCommands.SELL)) != null){
                System.out.println(shopMenuController.sell(Controller.getRemovedQuotationMarks(matcher.group("itemname")), Integer.parseInt(matcher.group("itemamount"))));
            }
            else
                System.out.println("Invalid Command!");
        }
    }
}
