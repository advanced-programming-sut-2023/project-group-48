package controller;

import model.Game;
import model.MapMethods;
import model.Match.Cell;
import model.Match.LandType;
import model.SavableMap;
import model.User;

import java.io.IOException;
import java.util.*;

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

    public String getRandomPassword() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digit = "0123456789";
        String symbol = "`~!@#$%^&*()-_=+[]{}\\|'\";:,<.>/?";
        Random rand = new Random();
        int length = MIN_PASSWORD_LENGTH + rand.nextInt(MAX_PASSWORD_LENGTH - MIN_PASSWORD_LENGTH + 1);
        LinkedList<Character> randomPasswords = new LinkedList<>();
        String result = "";
        randomPasswords.add(alphabet.charAt(rand.nextInt(alphabet.length())));
        randomPasswords.add(alphabet.charAt(rand.nextInt(alphabet.length())));
        randomPasswords.add(Character.toLowerCase(alphabet.charAt(rand.nextInt(alphabet.length()))));
        randomPasswords.add(digit.charAt(rand.nextInt(digit.length())));
        randomPasswords.add(symbol.charAt(rand.nextInt(symbol.length())));
        for (int i = 0; i < length - 3; i++) {
            randomPasswords.add(rand.nextInt(randomPasswords.size()) + 1, characters.charAt(rand.nextInt(characters.length())));
        }

        for (Character character : randomPasswords) {
            result += character;
        }
        return result;
    }

    public String getSuggestedUsername(String username) {
        String additionalUsername = "";
        Random random = new Random();
        int length = random.nextInt(5) + 1;
        for (int i = 0; i < length; i++) {
            additionalUsername += characters.charAt(random.nextInt(characters.length()));
        }
        return username + additionalUsername;
    }

    public String checkUsername(String username) {
        if (User.isUsernameNotValid(username)) return "not a valid username!";
        if (controller.getGame().getUserByUsername(username) != null)
            return "username already exists!\n" + "Our suggestion for username is \"" + getSuggestedUsername(username) + "\"";
        return "";
    }

    public String checkPassword(String password) {
        return User.isPasswordWeak(password);
    }

    public String checkEmail(String email) {
        if (User.isEmailNotValid(email)) return "not a valid email!";
        return "";
    }

    public ArrayList<String> getDefaultSlogans() {
        //HHHH

        return User.getSlogans();
    }

    public ArrayList<String> getDefaultRecoveryQuestions() {
        return User.getQuestions();
    }

    public void createUserJFX(String username, String password, String email, String nickname, String recoveryQuestion, String recoveryAnswer, String slogan) throws IOException {
        controller.getGame().addUser(new User(username, password, email, nickname, recoveryQuestion, recoveryAnswer, slogan));
    }

    public String createUserStep(String username, String password, String passwordConfirmation, String email, String nickname, String... slogan) {
        String result = "";
        if (username.isEmpty() || password.isEmpty() || email.isEmpty() || nickname.isEmpty() || (slogan.length != 0 && slogan[0].isEmpty()))
            return "a filed is empty!";

        if (User.isUsernameNotValid(username)) return "not a valid username!";

        if (controller.getGame().getUserByUsername(username) != null)
            return "username already exists!\n" + "Our suggestion for username is \"" + getSuggestedUsername(username) + "\"";

        if (!(result = User.isPasswordWeak(password)).isEmpty() && !password.equals("random"))
            return "password is weak: " + result;

        if ((passwordConfirmation != null && !passwordConfirmation.isEmpty() && !password.equals(passwordConfirmation)) ||
                ((passwordConfirmation == null || passwordConfirmation.isEmpty()) && !password.equals("random")))
            return "password confirmation is not correct!";

        if (controller.getGame().getUserByEmail(email) != null) return "email already exists!";

        if (User.isEmailNotValid(email)) return "email is not valid!";

        if (slogan.length != 0 && slogan[0].equals("random")) {
            String randSlogan = User.getRandomSlogan();
            result = "Your slogan is: \"" + randSlogan + "\"";
            userDetails.put("slogan", randSlogan);
        }

        if ((passwordConfirmation == null || passwordConfirmation.isEmpty()) && password.equals("random")) {
            String randPassword = getRandomPassword();
            if (result.startsWith("no")) result = "";
            result += (result.isEmpty() ? "" : "\n") + "Your random password is: " + randPassword + "\nPlease re-enter your password here:";
            userDetails.put("password", randPassword);
        }
        userDetails.put("username", username);
        if (!password.equals("random")) userDetails.put("password", password);
        userDetails.put("email", email);
        userDetails.put("nickname", nickname);
        if (slogan.length != 0 && !(slogan[0]).equals("random")) userDetails.put("slogan", slogan[0]);
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

    private String generateCaptcha() {
        String[] captcha = Game.getRandomCaptcha();
        captchaAnswer = captcha[1];
        return captcha[0] + "enter the number: ";
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
        step = 0;
        return "user created successfully!";
    }

    public int getStep() {
        return step;
    }
}