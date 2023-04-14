package model;

import java.util.ArrayList;

public class User {
    private static final ArrayList<String> questions = new ArrayList<>();
    private static final ArrayList<String> slogans = new ArrayList<>();
    private String username;
    private String password;
    private String email;
    private String nickname;
    private String passwordQuestion;
    private String passwordAnswer;
    private String slogan;
    private int highScore;
    private int rank;

    static {
    }

    public User(String username, String password, String email, String nickname, String... slogan) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        if (slogan[0] != null) this.slogan = slogan[0;]
    }

    public static ArrayList<String> getQuestions() {
    }

    public static String getRandomPassword() {
    }

    public static String getRandomSlogan() {
    }

    public static String getSuggestedUsername(String username) {
    }

    private static boolean isPasswordNotValid(String password) {
    }

    private static boolean isPasswordWeak(String password) {
    }

    private static boolean isEmailNotValid(String email) {
    }

    public boolean isPasswordNotCorrect(String password) {
    }

    public boolean isPasswordNotRepeated(String password) {
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