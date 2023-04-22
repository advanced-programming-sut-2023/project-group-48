package controller;

import model.Game;
import model.User;

import java.io.IOException;
import java.util.*;

// Parsa
public class SignUpMenuController {
    private static final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789`~!@#$%^&*()-_=+[]{}\\|'\";:,<.>/?";
    private static final int MIN_PASSWORD_LENGTH = 6;
    private static final int MAX_PASSWORD_LENGTH = 12;
    private final Controller controller;
    private HashMap<String, String> userDetails = new HashMap<>();
    private String captchaAnswer;
    private int step = 0;

    public SignUpMenuController(Controller controller) {
        this.controller = controller;
    }

    public static String getRandomPassword() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digit = "0123456789";
        String symbol = "`~!@#$%^&*()-_=+[]{}\\|'\";:,<.>/?";
        Random rand = new Random();
        int length = MIN_PASSWORD_LENGTH + rand.nextInt(MAX_PASSWORD_LENGTH - MIN_PASSWORD_LENGTH + 1);
        LinkedList<Character> randomPasswords = new LinkedList<>();
        randomPasswords.add(alphabet.charAt(rand.nextInt(alphabet.length())));
        randomPasswords.add(alphabet.charAt(rand.nextInt(alphabet.length())));
        randomPasswords.add(Character.toLowerCase(alphabet.charAt(rand.nextInt(alphabet.length()))));
        randomPasswords.add(digit.charAt(rand.nextInt(digit.length())));
        randomPasswords.add(symbol.charAt(rand.nextInt(symbol.length())));
        for (int i = 0; i < length - 3; i++) {
            randomPasswords.add(rand.nextInt(randomPasswords.size()) + 1, characters.charAt(rand.nextInt(characters.length())));
        }
        return randomPasswords.toString();
    }

    public static String getSuggestedUsername(String username) {
        String additionalUsername = "";
        Random random = new Random();
        int length = random.nextInt(5) + 1;
        for (int i = 0; i < length; i++) {
            additionalUsername += characters.charAt(random.nextInt(characters.length()));
        }
        return username + additionalUsername;
    }

    public static boolean isUsernameNotValid(String username) {
        return !username.matches("\\w+");
    }

    private static String isPasswordWeak(String password) {
        String result = "";
        if (password.length() < 6) result += "short password, ";
        if (!password.matches(".*[A-Z].*")) result += "no upper case letter, ";
        if (!password.matches(".*[a-z].*")) result += "no lower case letter, ";
        if (!password.matches(".*\\d.*")) result += "not digits, ";
        if (!password.matches(".*[^a-zA-Z0-9].*")) result += "no non letter character, ";
        if (result.endsWith(", ")) result = result.substring(0, result.lastIndexOf(','));
        return result;
    }

    private static boolean isEmailNotValid(String email) {
        return !email.matches("[\\w.]+\\@[\\w\\.]+\\.[\\w\\.]+");
    }

    public String createUserStep(String username, String password, String passwordConfirmation, String email, String nickname, String... slogan) {
        String result = "";
        if (username.isEmpty() || password.isEmpty() || email.isEmpty() || nickname.isEmpty() || (slogan.length != 0 && slogan[0].isEmpty()))
            return "a filed is empty!";
        if (isUsernameNotValid(username)) return "not a valid username!";
        if (controller.getGame().getUserByUsername(username) != null)
            return "username already exists!";
        if (!(result = isPasswordWeak(password)).isEmpty()) return "password is weak: " + result;
        if (passwordConfirmation != null && !password.equals(passwordConfirmation))
            return "password confirmation is not correct!";
        if (controller.getGame().getUserByEmail(email) != null) return "email already exists!";
        if (isEmailNotValid(email)) return "email is not valid!";

        if (slogan.length != 0 && slogan[0].equals("random"))
            result += "Your slogan is: \" " + User.getRandomSlogan() + " \"";
        if (passwordConfirmation == null && password.equals("random"))
            result += (result.isEmpty() ? "" : "\n") + "Your random password is: " + getRandomPassword() + "\n Please re-enter your password here:\n";
        userDetails.put("username", username);
        userDetails.put("password", password);
        userDetails.put("email", email);
        userDetails.put("nickname", nickname);
        if (slogan.length != 0) userDetails.put("slogan", slogan[0]);
        if (result.isEmpty()) {
            step = 1;
            return chooseQuestionStep();
        }
        step = 1;
        return result;
    }

    public String chooseQuestionStep(String... passwordConfirmation) {
        StringBuilder result = new StringBuilder("Pick your security question :");
        if (passwordConfirmation.length != 0 && !passwordConfirmation[0].equals(userDetails.get("password")))
            return "password confirmation is not correct!";
        for (int index = 0; index < 3; index++) {
            result.append("\n").append(index + 1).append("\\. ").append(User.getQuestionByIndex(index));
        }
        step = 2;
        return result.toString();
    }

    public String captchaStep(String question, String answer) {
        userDetails.put("securityQuestion", question);
        userDetails.put("securityAnswer", answer);
        step = 3;
        return generateCaptcha();
    }

    public String generateCaptcha() {
        String[] captcha = Game.getRandomCaptcha();
        captchaAnswer = captcha[1];
        return captcha[0] + "\nenter the number: ";
    }

    public String finalStep(String userCaptchaAnswer) throws IOException {
        if (userCaptchaAnswer.equals("refresh captcha")) {
            return generateCaptcha();
        }
        if (!userCaptchaAnswer.equals(captchaAnswer)) return "captcha answer is incorrect!";

        controller.getGame().addUser(new User(userDetails.get("username"), userDetails.get("password"),
                userDetails.get("email"), userDetails.get("nickname"),
                userDetails.get("securityQuestion"), userDetails.get("securityAnswer"),
                userDetails.get("slogan")));
        userDetails = new HashMap<>();
        return "user created successfully!";
    }

    public String enterLoginMenu() {
        return controller.enterLoginMenu();
    }

    public int getStep() {
        return step;
    }
}