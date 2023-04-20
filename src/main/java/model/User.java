package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

// Parsa
public class User {
    private static final ArrayList<String> questions = new ArrayList<>();
    private static final ArrayList<String> slogans = new ArrayList<>();
    private final String username;
    private final String password;
    private final String email;
    private final String nickname;
    private String passwordQuestion;
    private String passwordAnswer;
    private String slogan;
    private int highScore;
    private int rank;

    static {
        questions.add("What is your favorite food?");
        questions.add("What is your best friend's name?");
        questions.add("What is your pet's name?");
    }

    public User(String username, String password, String email, String nickname, String... slogan) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
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

    public String getPasswordQuestion() {
        return passwordQuestion;
    }

    public void setPasswordQuestion(String passwordQuestion) {
        this.passwordQuestion = passwordQuestion;
    }

    public String getPasswordAnswer() {
        return passwordAnswer;
    }

    public void setPasswordAnswer(String passwordAnswer) {
        this.passwordAnswer = passwordAnswer;
    }

    public String getSlogan() {
    }

    public int getHighScore() {
    }

    public int getRank() {
    }

    public void setUsername(String username) {
    }

    public void setPassword(String password) {
    }

    public void setEmail(String email) {
    }

    public void setNickname(String nickname) {
    }

    public void setSlogan(String slogan) {
    }
}