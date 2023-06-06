import controller.Controller;
import controller.LoginMenuController;
import javafx.stage.Stage;
import model.User;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

public class LoginTest {
    private final Controller controller = new Controller(new Stage());
    private final LoginMenuController loginMenuController = new LoginMenuController(controller);


    public LoginTest() throws IOException {
        controller.getGame().addUser(new User("rahim", "RahimKh2000$", "rahimKh@ap.com", "8 silandr", "What is your pet's name?", "abolfazl", "micharknoonamo micharkham"));
    }

    @org.junit.jupiter.api.Test
    public void incorrectUsernameTest() throws IOException {
        String output = loginMenuController.login("karim", "RahimKh2000$", false);
        Assertions.assertEquals(output, "Username and password didn’t match!");
    }

    @org.junit.jupiter.api.Test
    public void incorrectPasswordTest() throws IOException {
        String output = loginMenuController.login("rahim", "12345", false);
        Assertions.assertEquals(output, "Username and password didn’t match!");
    }

    @org.junit.jupiter.api.Test
    public void incorrectCaptchaAnswerTest() throws IOException {
        loginMenuController.login("rahim", "RahimKh2000$", false);
        String output = loginMenuController.finalStep("XD");
        Assertions.assertEquals(output, "captcha answer is incorrect!");
    }

    @org.junit.jupiter.api.Test
    public void refreshCaptchaTest() throws IOException {
        loginMenuController.login("rahim", "RahimKh2000$", false);
        String output = loginMenuController.finalStep("refresh captcha");
        Assertions.assertNotEquals(output, "captcha answer is incorrect!");
    }

    @org.junit.jupiter.api.Test
    public void correctLoginAttemptTest() throws IOException {
        loginMenuController.login("rahim", "RahimKh2000$", false);
        String output = loginMenuController.finalStep(controller.getCaptchaAnswer());
        Assertions.assertEquals(output, "logged in successfully!\n" + controller.enterMainMenu());
    }
}
