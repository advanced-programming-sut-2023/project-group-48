package model;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.Random;

public class User {
    private static final ArrayList<String> questions = new ArrayList<>();
    private static final ArrayList<String> slogans = new ArrayList<>();
    private String username;
    private String password;
    private String email;
    private String nickname;
    private final String securityQuestion;
    private final String securityAnswer;
    private String slogan;
    private int highScore;
    private int rank;

    static {
        questions.add("What is your favorite food?");
        questions.add("What is your best friend's name?");
        questions.add("What is your pet's name?");
    }

    public User(String username, String password, String email, String nickname, String securityQuestion, String securityAnswer, String... slogan) {
        this.username = username;
        this.password = getEncryptedString(password);
        this.email = email;
        this.nickname = nickname;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.slogan = slogan[0] != null ? slogan[0] : null;
    }

    public static boolean isUsernameNotValid(String username) {
        return !username.matches("\\w+");
    }

    public static String isPasswordWeak(String password) {
        String result = "";
        if (password.length() < 6) result += "short password, ";
        if (!password.matches(".*[A-Z].*")) result += "no upper case letter, ";
        if (!password.matches(".*[a-z].*")) result += "no lower case letter, ";
        if (!password.matches(".*\\d.*")) result += "not digits, ";
        if (!password.matches(".*[^a-zA-Z0-9].*")) result += "no non letter character, ";
        if (result.endsWith(", ")) result = result.substring(0, result.lastIndexOf(','));
        return result;
    }

    public static boolean isEmailNotValid(String email) {
        return !email.matches("[\\w.]+\\@[\\w\\.]+\\.[\\w\\.]+");
    }

    public static String getQuestionByIndex(int index) {
        return questions.get(index);
    }

    public static int questionsCount() {
        return questions.size();
    }

    public static String getRandomSlogan() {
        return slogans.get(new Random().nextInt(slogans.size()));
    }

    private static String getEncryptedString(String originalString) {
        return DigestUtils.sha256Hex(originalString);
    }

    public boolean isPasswordNotCorrect(String password) {
        return !getEncryptedString(password).equals(this.password);
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public boolean isSecurityAnswerNotCorrect(String securityAnswer) {
        return !securityAnswer.equals(this.securityAnswer);
    }

    public String getSlogan() {
        return this.slogan;
    }

    public int getHighScore() {
        return this.highScore;
    }

    public int getRank() {
        return this.rank;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = getEncryptedString(password);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void removeSlogan() {
        slogan = null;
    }
}