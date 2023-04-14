package model;

import java.util.ArrayList;

public class User {
    private static final ArrayList<String> questions;
    private static final ArrayList<String> slogans;
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

    public User(String username, String password, String email, String nickname, String passwordQuestion, String passwordAnswer, String... slogan) {
    }

    public static ArrayList<String> getQuestions() {
    }

    public static String getRnadomPassword() {
    }

    public static String getRandomSlogan() {
    }

    public static String getSuggesteedUsername(String username) {
    }

    private static boolean isPasswordNotValid(String password) {
    }

    private static boolean isPasswrodWeak(String password) {
    }

    private static boolean isEmailNotValid(String email) {
    }

    public boolean isPasswordNotCorrect(String password {
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