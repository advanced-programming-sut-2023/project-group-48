package model;

import java.util.ArrayList;
import java.util.Random;

// Parsa
public class User {
    private static final ArrayList<String> questions = new ArrayList<>();
    private static final ArrayList<String> slogans = new ArrayList<>();
    private final String username;
    private final String password;
    private final String email;
    private final String nickname;
    private final String securityQuestion;
    private final String securityAnswer;
    private final String slogan;
    private int highScore;
    private int rank;

    static {
        questions.add("What is your favorite food?");
        questions.add("What is your best friend's name?");
        questions.add("What is your pet's name?");
    }

    public User(String username, String password, String email, String nickname, String securityQuestion, String securityAnswer, String... slogan) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.slogan = slogan[0] != null ? slogan[0] : null;
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

    public boolean isPasswordNotCorrect(String password) {
        return !password.equals(this.password);
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
}