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
            if (command.matches("back")) {
                System.out.println(controller.enterMatchMenu());
            }
            else if ((matcher = TradeMenuCommands.getMatcher(command, TradeMenuCommands.TRADE)) != null){
                System.out.println(tradeMenuController.addTrade(matcher.group("username"), matcher.group("resourcetype"),
                                   Integer.parseInt(matcher.group("resourceamount")), Integer.parseInt(matcher.group("price")),
                                   matcher.group("message")));
            }
            else if (command.matches("^trade\\s+list$")){
                System.out.println(tradeMenuController.tradeList());
            }
            else if ((matcher = TradeMenuCommands.getMatcher(command, TradeMenuCommands.ANSWERTRADE)) != null){
                System.out.println(tradeMenuController.answerRequest(Integer.parseInt(matcher.group("id")), matcher.group("message"), matcher.group("answer")));
            }
            else if (command.matches("^trade\\s+history$")){
                System.out.println(tradeMenuController.tradeHistory());
            }
            else
                System.out.println("Invalid Command!");
        }
    }
}
