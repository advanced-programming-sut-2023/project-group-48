package view;

import controller.Controller;
import controller.TradeMenuController;
import view.Commands.TradeMenuCommands;
import view.Messages.TradeMenuMessages;

import java.util.regex.Matcher;

public class TradeMenu extends Menu {
    private final TradeMenuController tradeMenuController;
    public TradeMenu(Controller controller) {
        super(controller);
        this.tradeMenuController = new TradeMenuController(controller);
    }

    @Override
    public void run() {
        while(true){
            String command = scanner.nextLine().trim();
            Matcher matcher;
            if ((matcher = TradeMenuCommands.getMatcher(command, TradeMenuCommands.TRADE)) != null){

            }
            else if (command.matches("^trade\\s+list$")){

            }
            else if ((matcher = TradeMenuCommands.getMatcher(command, TradeMenuCommands.TRADEACCEPT)) != null){

            }
            else if (command.matches("^trade\\s+history$")){

            }
            else
                System.out.println("Invalid Command!");
        }
    }
}
