package view;

import controller.Controller;
import controller.ProfileMenuController;
import view.Commands.ProfileMenuCommands;
import view.Messages.ProfileMenuMessages;

import java.util.regex.Matcher;

public class ProfileMenu extends Menu {
    private final ProfileMenuController profileMenuController;
    public ProfileMenu(Controller controller) {
        super(controller);
        this.profileMenuController = new ProfileMenuController(controller);
    }

    //private enum Commands {}

    @Override
    public void run() {
        while (true){
            String command = scanner.nextLine().trim();
            Matcher matcher;
            if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.USERNAMECHANGE)) != null){

            }
            else if((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.NICKNAMECHANGE)) != null){

            }
            else if((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.PASSWORDCHANGE)) != null){

            }
            else if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.EMAILCHANGE)) != null){

            }
            else if ((matcher = ProfileMenuCommands.getMatcher(command, ProfileMenuCommands.SLOGANCHANGE)) != null){

            }
            else if(command.matches("^Profile\\s+remove\\s+slogan$")){

            }
            else if (command.matches("^profile\\s+display\\s+highscore$")){

            }
            else if (command.matches("^profile\\s+display\\s+rank$")){

            }
            else if (command.matches("^profile\\s+display\\s+slogan$")){

            }
            else if (command.matches("^profile\\s+display$")){

            }
            else
                System.out.println("Invalid Command!");
        }
    }

}