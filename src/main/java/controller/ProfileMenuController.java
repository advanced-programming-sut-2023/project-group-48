package controller;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.Game;
import model.User;

import java.util.ArrayList;

public class ProfileMenuController {
    private final Controller controller;
    private String currentPassword;
    private String captchaAnswer;
    private int step = 0;

    public ProfileMenuController(Controller controller) {
        this.controller = controller;
    }

    public int getStep() {
        return step;
    }

    private String generateCaptcha() {
        String[] captcha = Game.getRandomCaptcha();
        captchaAnswer = captcha[1];
        return captcha[0] + "enter the number: ";
    }

    public String changeUserInfo(char infoType, String... info) {
        if (infoType == 'u') {
            if (User.isUsernameNotValid(info[0])) return "not a valid username!";
            controller.getGame().getCurrentUser().setUsername(info[0]);
            return "username changed successfully!";
        } else if (infoType == 'n') {
            controller.getGame().getCurrentUser().setNickname(info[0]);
            return "nickname changed successfully!";
        } else if (infoType == 'p') {
            controller.getGame().getCurrentUser().setPassword(info[0]);
            return "password changed successfully!";
        } else if (infoType == 'e') {
            if (User.isEmailNotValid(info[0])) return "email is not valid!";
            controller.getGame().getCurrentUser().setEmail(info[0]);
            return "email changed successfully!";
        } else if (infoType == 's') {
            controller.getGame().getCurrentUser().setSlogan(info[0]);
            return "slogan changed successfully!";
        } else {
            return "invalid info!";
        }
    }

    public String captchaStep(String userCaptchaAnswer) {
        if (userCaptchaAnswer.equals("refresh captcha")) {
            return generateCaptcha();
        }
        if (!userCaptchaAnswer.equals(captchaAnswer)) return "captcha answer is incorrect!";
        step = 2;
        return "Please enter your new password again";
    }

    public String finalStep(String newPassword, String newPasswordAgain) {
        String result;
        if (controller.getGame().getCurrentUser().isPasswordNotCorrect(currentPassword))
            return "Current password is incorrect!";
        if (!controller.getGame().getCurrentUser().isPasswordNotCorrect(newPassword))
            return "Please enter a new password!";
        if (!newPasswordAgain.equals(newPassword))
            return "Password confirmation is not correct!";
        if (!(result = User.isPasswordWeak(newPassword)).isEmpty()) return "new password is weak: " + result;
        controller.getGame().getCurrentUser().setPassword(newPassword);
        step = 0;
        return "password changed successfully!";
    }

    public void removeSlogan() {
        controller.getGame().getCurrentUser().removeSlogan();
    }

    public String displayHighScore() {
        return "Your highscore is: " + controller.getGame().getCurrentUser().getHighScore();
    }

    public String displayRank() {
        return "Your rank is: " + controller.getGame().getCurrentUser().getRank();
    }

    public String displaySlogan() {
        if (controller.getGame().getCurrentUser().getSlogan() == null) return "You don't have a slogan!";
        return "Your slogan is: " + controller.getGame().getCurrentUser().getSlogan();
    }

    public String profileDisplay() {
        return "username: " + controller.getGame().getCurrentUser().getUsername() + "\n" +
                "email: " + controller.getGame().getCurrentUser().getEmail() + "\n" +
                "nickname: " + controller.getGame().getCurrentUser().getNickname() + "\n" +
                "slogan: " + controller.getGame().getCurrentUser().getSlogan() + "\n" +
                "highscore: " + controller.getGame().getCurrentUser().getHighScore() + "\n" +
                "rank: " + controller.getGame().getCurrentUser().getRank();
    }

    public ArrayList<String> getDefaultSlogans() {
        return User.getSlogans();
    }

    public String changeAvatar(ImagePattern imagePattern) {
        controller.getGame().getCurrentUser().setAvatarUrl(imagePattern.getImage().getUrl());
        return "changed successfully!";
    }

    public ImagePattern getAvatarImagePattern() {
        return new ImagePattern(new Image(controller.getGame().getCurrentUser().getAvatarUrl()));
    }

    public void setAvatar(String avatarUrl) {
        controller.getGame().getCurrentUser().setAvatarUrl(avatarUrl);
    }

    public String checkOldPassword(String text) {
        if (controller.getGame().getCurrentUser().isPasswordNotCorrect(text)) return "old password is incorrect!";
        return "old password is correct!";
    }

    public String checkUsername(String username) {
        if (User.isUsernameNotValid(username)) return "not a valid username!";
        if (controller.getGame().getUserByUsername(username) != null)
            return "username already exists!\n";
        return "";
    }

    public String checkEmail(String email) {
        if (User.isEmailNotValid(email)) return "not a valid email!";
        return "";
    }

    public ArrayList<User> getScoreBoard() {
        return controller.getGame().getScoreBoard();
    }

    public String getInfo() {
        return "highscore: " + controller.getGame().getCurrentUser().getHighScore() + "\n" +
                        "rank: " + controller.getGame().getCurrentUser().getRank();
    }

    public String getUsername() {
        return "username: " + controller.getGame().getCurrentUser().getUsername();
    }

    public String getEmail() {
        return "email: " + controller.getGame().getCurrentUser().getEmail();
    }

    public String getNickname() {
        return "nickname: " + controller.getGame().getCurrentUser().getNickname();
    }

    public User getCurrentUser() {
        return controller.getGame().getCurrentUser();
    }
}