package controller;

import model.Game;
import model.User;

import java.util.*;

// Parsa
public class SignUpMenuController {
    private static final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789`~!@#$%^&*()-_=+[]{}\\|'\";:,<.>/?";
    private final Controller controller;
    private static final int MIN_PASSWORD_LENGTH = 6;
    private static final int MAX_PASSWORD_LENGTH = 12;
    private HashMap<String, String> temporaryUserDetails = new HashMap<>();
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

    public String createUser(String username, String password, String passwordConfirmation, String email, String nickname, String... slogan) {
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
        temporaryUserDetails.put("username", username);
        temporaryUserDetails.put("password", password);
        temporaryUserDetails.put("email", email);
        temporaryUserDetails.put("nickname", nickname);
        if (slogan.length != 0) temporaryUserDetails.put("slogan", slogan[0]);
        step++;
        return result;
    }

    public String postCreateUser(String... passwordConfirmation) {
        String result = "Pick your security question :";
        if (passwordConfirmation.length != 0 && !passwordConfirmation[0].equals(temporaryUserDetails.get(1)))
            return "password confirmation is not correct!";
        for (int index = 0; index < 3; index++) {
            result += "\n" + (index + 1) + "\\. " + User.getQuestionByIndex(index);
        }
        step++;
        return result;
    }

    public String finalCreateUser(String question, String answer) {
        User user;
        controller.getGame().addUser(user = new User(temporaryUserDetails.get("username"), temporaryUserDetails.get("password"), temporaryUserDetails.get("email"), temporaryUserDetails.get("nickname"), temporaryUserDetails.get("slogan")));
        user.setPasswordQuestion(question);
        user.setPasswordAnswer(answer);
        step = 0;
        temporaryUserDetails = new HashMap<>();
        return "user created successfully!";
    }

    public int getStep() {
        return step;
    }
}