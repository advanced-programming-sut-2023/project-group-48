package model;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public enum Captcha {
    ZERO(new String[]{
            "▓░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓",
            "▓░░▒▒▒▒▒▒▒▒▒▒        ▒▒▒▒▒▒▒▒▒▒▒▒▓",
            "▓▒▒▒▒▒▒▒▓▒▒            ▒▒▒▒▒▒▒▒▒▒▓",
            "▓▒▒▒▒▒▒▒▓▒▒    ▓▓▓▓    ▒▒▒▒▒▒▒▒▒▒▓",
            "▓▒▒▒▒▒▒░▓░░  ░░▓▓▓▓▒▒    ▓▒▒▒▒▒▒▒▓",
            "▓▒▒▒▒▒▒▒▓    ▒▒▓▓▓▓▓▓    ▓▒▒▒▒▒▒▒▓",
            "▓▒▒▒▒▒▒▒▓    ▓▓▓▓▓▓▓▓    ▓▒▒▒▒▒▒▒▓",
            "▓▒▒▒▒▒▒▒▓    ▓▓▓▓▓▓▓▓    ▓▒▒▒▒▒▒▒▓",
            "▓▒▒▒▒▒▒▒▓    ▓▓▓▓▓▓▓▓    ▓▒▒▒▒▒▒▒▓",
            "▓▒▒▒▒▒▒▒▓    ▓▓▓▓▓▓▓▓    ▓▒▒▒▒▒▒▒▓",
            "▓▒▒▒▒▒▒▒▓    ▒▒▓▓▓▓▓▓    ▓▒▒▒▒▒▒▒▓",
            "▓▒▒▒▒▒▒▒▓░░  ░░▓▓▓▓▒▒    ▓▒▒▒▒▒▒▒▓",
            "▓▒▒▒▒▒▒▒▓▒▒    ▓▓▓▓    ▒▒▓▒▒▒▒▒▒▒▓",
            "▓▒▒▒▒▒▒▒▓▓▓            ▓▓▓▒▒▒▒▒▒▒▓",
            "▓▒▒▒▒▒▒▒▓▓▓▓▓        ▓▓▓▓▓▒▒▒▒▒▒▒▓",
            "▓▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▓"
    }),

    ONE(new String[]{
            "▓░░▒▒▒▒▒▒▒▒▒▒▒▒░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓",
            "▓░░▒▒▒▒▒▒▒▒▒▒░░▒▒░░    ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓",
            "▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒      ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓",
            "▓▒▒▒▒▒▒▒▒▒▒▒▒░░        ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓",
            "▓▒▒▒▒▒▒░░▒▒░░          ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓",
            "▓▒▒▒▒▒▒▒▒              ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓",
            "▓▒▒▒▒▒▒▒▒░░  ▒▒▒▒░░    ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓",
            "▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░    ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓",
            "▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░    ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓",
            "▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒      ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓",
            "▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒      ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓",
            "▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒      ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓",
            "▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒      ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓",
            "▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░░░    ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓",
            "▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒  ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓",
            "▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓"
    }),

    TWO(new String[]{
            "▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓",
            "▓░░░░░░░░░░░░            ░░░░░░░░░░░░░░█",
            "▓░░░░░░░░░░                ░░░░░░░░░░░░▓",
            "▓░░░░░░░░░░    ▒▒▒▒▒▒░░    ░░░░░░░░░░░░▓",
            "▓░░░░░░░░      ▒▒▒▒▒▒▒▒      ▒▒░░░░░░░░▓",
            "▓░░░░░░░░░░  ░░▒▒▒▒▒▒▒▒      ▒▒▒▒░░░░░░▓",
            "▓░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒░░    ░░▒▒▒▒▒▒░░░░▓",
            "▓░░░░░░░░░░░░░░▒▒▒▒░░      ▒▒▒▒▒▒▒▒▒▒░░▓",
            "█░░░░░░░░░░░░░░░░      ░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒█",
            "▓░░░░░░░░░░░░░░      ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█",
            "▓░░░░░░░░░░░░      ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█",
            "▓░░░░░░░░░░      ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█",
            "▓░░░░░░░░                    ▒▒▒▒▒▒▒▒▒▒█",
            "▓░░░░░░░░░░                  ▓▓▒▒▒▒▒▒▒▒█",
            "▓░░░░░░░░░░▒▒▓▓▒▒▒▒▒▒▒▒▓▓▒▒▓▓▒▒▒▒▒▒▒▒▒▒█",
            "▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓",
    }),

    THREE(new String[]{
            "▓▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▓",
            "█░░░░░░░░░░░░░░          ░░░░░░░░░░▓▓",
            "▓░░░░░░░░░░              ░░░░░░░░░░▓▓",
            "▓░░░░░░░░░░    ░░▓▓▒▒░░    ░░░░░░░░▓▓",
            "▓░░░░░░░░░░    ▓▓▒▒▒▒▒▒    ▒▒░░░░░░▓▓",
            "▓░░░░░░░░░░░░▒▒▓▓▒▒▒▒░░    ▒▒▒▒░░░░▓▓",
            "▓░░░░░░░░░░░░░░▒▒▒▒░░    ▓▓▒▒▒▒▒▒░░▓▓",
            "▓░░░░░░░░░░░░░░          ▓▓▒▒▒▒▒▒░░▓▓",
            "█░░░░░░░░░░░░░░░░▒▒▒▒░░    ▒▒▒▒▒▒▒▒▓▓",
            "█░░░░░░░░░░░░░░░░░░▒▒▒▒    ▓▓▒▒▒▒▒▒▓▓",
            "█░░░░░░░░      ░░░░░░▒▒    ▓▓▒▒▒▒░░▓▓",
            "█░░░░░░░░░░    ▒▒░░░░░░    ▓▓▒▒▒▒░░▓▓",
            "█░░░░░░░░░░      ░░░░    ░░▒▒▒▒▒▒▒▒▓▓",
            "█░░░░░░░░░░░░░░          ▓▓▒▒▒▒▒▒▒▒▓▓",
            "█░░░░░░░░░░░░░░▓▓░░░░▒▒▓▓▒▒▒▒▒▒▒▒▒▒▓▓",
            "▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓"
    }),

    FOUR(new String[]{
            "▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓",
            "▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░    ▒▒▒▒▒▒▒▒▒▒▓▓",
            "██▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒      ▓▓▒▒▒▒▒▒▒▒▓▓",
            "██▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒        ▓▓▓▓▒▒▒▒▒▒▓▓",
            "██▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░  ░░    ▓▓▓▓▒▒▒▒▒▒▓▓",
            "██▒▒▒▒▒▒▒▒▒▒▒▒▒▒    ██    ▓▓▓▓▓▓▒▒▒▒▓▓",
            "██▒▒▒▒▒▒▒▒▒▒▒▒    ▒▒██    ▓▓▓▓▓▓▓▓▒▒▓▓",
            "██▒▒▒▒▒▒▒▒▒▒░░  ░░▓▓▓▓    ▓▓▓▓▓▓▓▓▓▓▓▓",
            "██▒▒▒▒▒▒▒▒░░  ░░▓▓▓▓██    ▓▓▓▓▓▓▓▓▓▓▓▓",
            "██▒▒▒▒▒▒░░                  ▒▒▓▓▓▓▓▓▓▓",
            "██▒▒▒▒▒▒▒▒                  ░░▓▓▓▓▓▓██",
            "██▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓    ▓▓▓▓▓▓▓▓▓▓██",
            "██▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓    ▓▓▓▓▓▓▓▓▓▓██",
            "██▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓    ▓▓▓▓▓▓▓▓▓▓██",
            "██▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▒▒░░▓▓▓▓▓▓▓▓▓▓██",
            "▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓"
    }),

    FIVE(new String[]{
            "▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒",
            "▓▓░░░░░░░░░░              ░░░░░░░░░░▒▒",
            "██░░░░░░░░░░              ▒▒▒▒░░░░░░▒▒",
            "██░░░░░░░░░░  ░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒░░░░▒▒",
            "██░░░░░░░░░░  ▒▒▓▓▓▓▓▓▒▒▓▓▓▓▓▓▒▒▒▒░░▒▒",
            "██░░░░░░░░    ▒▒▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒",
            "██░░░░░░░░    ░░        ▒▒▒▒▒▒▒▒▒▒▒▒▒▒",
            "██░░░░░░░░      ░░        ▒▒▒▒▒▒▒▒▒▒▓▓",
            "██░░░░░░░░░░░░▓▓▓▓▓▓▓▓    ░░▒▒▒▒▒▒▒▒▓▓",
            "██░░░░░░░░░░▒▒▓▓▓▓▒▒▓▓      ▓▓▒▒▒▒▒▒▓▓",
            "██░░░░░░░░  ░░▒▒▒▒▒▒▒▒      ▓▓▓▓▒▒▒▒▒▒",
            "██░░░░░░░░    ▒▒▒▒▒▒▒▒    ░░▓▓▓▓▒▒▒▒▒▒",
            "██░░░░░░░░      ░░░░      ▓▓▓▓▒▒▒▒▒▒▓▓",
            "██░░░░░░░░░░            ▓▓▓▓▓▓▓▓▒▒▒▒▓▓",
            "██░░░░░░░░░░░░▒▒░░░░▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▓▓",
            "▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓"
    }),

    SIX(new String[]{
            "▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█",
            "▓▒▒▒▒▒▒▒▒▒▒▒▒░░        ░░▒▒▒▒▒▒▒▒▒▒▒▒▓",
            "▓▒▒▒▒▒▒▒▒▒▒░░              ▒▒▒▒▒▒▒▒▒▒▓",
            "▓▒▒▒▒▒▒▒▒▒▒    ▒▒▓▓▓▓▒▒    ▒▒▒▒▒▒▒▒▒▒▓",
            "▓▒▒▒▒▒▒▒▒░░    ▓▓▓▓▓▓▓▓    ▓▓▓▓▒▒▒▒▒▒▓",
            "▓▒▒▒▒▒▒▒▒    ░░▓▓▓▓▓▓▓▓▓▓██▓▓▓▓▓▓▒▒▒▒▓",
            "▓▒▒▒▒▒▒▒▒    ▒▒▓▓      ▓▓▓▓▓▓▓▓▓▓▓▓▒▒▓",
            "▓▒▒▒▒▒▒▒▒    ░░          ░░▓▓▓▓▓▓▓▓▓▓▓",
            "▓▒▒▒▒▒▒▒▒      ░░▓▓▓▓      ▒▒▓▓▓▓▓▓▓▓▓",
            "▓▒▒▒▒▒▒▒▒      ▓▓▓▓▓▓▓▓      ▓▓▓▓▓▓▓▓█",
            "▓▒▒▒▒▒▒▒▒    ░░▓▓▓▓▓▓▓▓      ▓▓▓▓▓▓▓▓█",
            "▓▒▒▒▒▒▒▒▒      ▓▓▓▓▓▓▓▓      ▓▓▓▓▓▓▓▓█",
            "▓▒▒▒▒▒▒▒▒▒▒    ▓▓▓▓▓▓▒▒    ▒▒▓▓▓▓▓▓▓▓▓",
            "▓▒▒▒▒▒▒▒▒▒▒                ▓▓▓▓▓▓▓▓▓▓█",
            "▓▒▒▒▒▒▒▒▒▒▒▒▒░░        ░░▓▓▓▓▓▓▓▓▓▓▓▓█",
            "▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓██▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓█"
    }),

    SEVEN(new String[]{
            "█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒█",
            "▓▒▒▒▒▒▒▒▒▒▒                  ▒▒▒▒▒▒▒▒▒▒█",
            "▓▒▒▒▒▒▒▒▒▒▒                  ░░▒▒▒▒▒▒▒▒█",
            "▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▒▒▓▓▒▒    ░░▓▓▓▓▒▒▒▒▒▒█",
            "▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓░░    ▓▓▓▓▓▓▓▓▒▒▒▒█",
            "▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒    ▓▓▓▓▓▓▓▓▓▓▓▓▒▒█",
            "▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓    ░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓█",
            "▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒    ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓█",
            "▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒    ░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓█",
            "▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒    ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓█",
            "█▒▒▒▒▒▒▒▒▒▒▒▒▒▒      ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓█",
            "▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒    ░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓█",
            "▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒    ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒█",
            "▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒    ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒█",
            "█▒▒▒▒▒▒▒▒▒▒▒▒▒▒    ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒█",
            "█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒█"
    }),

    EIGHT(new String[]{
            "█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓",
            "█▒▒▒▒▒▒▒▒▒▒▒▒░░        ░░▒▒▒▒▒▒▒▒▒▒▓▓",
            "█▒▒▒▒▒▒▒▒▒▒        ░░    ░░▒▒▒▒▒▒▒▒▒▒",
            "█▒▒▒▒▒▒▒▒▒▒    ░░▓▓▓▓▒▒    ▒▒▒▒▒▒▒▒▓▓",
            "█▒▒▒▒▒▒▒▒░░    ▒▒▓▓▓▓▓▓    ▓▓▒▒▒▒▒▒▓▓",
            "█▒▒▒▒▒▒▒▒░░    ▒▒▓▓▓▓▓▓    ▓▓▓▓▒▒▒▒▓▓",
            "█▒▒▒▒▒▒▒▒▒▒      ▓▓▓▓    ░░▓▓▓▓▒▒▒▒▓▓",
            "█▒▒▒▒▒▒▒▒▒▒▒▒            ▓▓▓▓▓▓▓▓▒▒██",
            "█▒▒▒▒▒▒▒▒▒▒░░            ▒▒▓▓▓▓▓▓▓▓▓▓",
            "█▒▒▒▒▒▒▒▒░░    ░░▓▓▓▓▒▒    ▓▓▓▓▓▓▓▓▓▓",
            "█▒▒▒▒▒▒▒▒      ▓▓▓▓▓▓▓▓    ▓▓▓▓▓▓▓▓▓▓",
            "█▒▒▒▒▒▒▒▒░░    ░░▓▓▓▓▓▓    ▓▓▓▓▓▓▓▓▓▓",
            "█▒▒▒▒▒▒▒▒▒▒              ░░▓▓▓▓▓▓▓▓▓▓",
            "█▒▒▒▒▒▒▒▒▒▒▒▒░░        ░░▓▓▓▓▓▓▓▓▓▓▓▓",
            "█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓",
            "▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓"
    }),

    NINE(new String[]{
            "▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▒▒▒▒▓▓▒▒▒▒▒▒██",
            "██▓▓▒▒▒▒▒▒▒▒▒▒░░        ▒▒▒▒▒▒▒▒▒▒▒▒██",
            "██▓▓▒▒▒▒▒▒▓▓              ▒▒▒▒▒▒▒▒▒▒██",
            "██▒▒▒▒▒▒▒▒░░    ▓▓▓▓██░░    ▒▒▒▒▒▒▒▒▓▓",
            "██▒▒▒▒▒▒▒▒      ▓▓▓▓▓▓▓▓    ▓▓▒▒▒▒▒▒▓▓",
            "██▒▒▒▒▒▒▒▒    ░░▓▓▓▓▓▓▓▓    ██▓▓▒▒▓▓▓▓",
            "▓▓▒▒▒▒▒▒▒▒      ▓▓▓▓▓▓▓▓    ▓▓▓▓▓▓▒▒▓▓",
            "▓▓▒▒▒▒▒▒▒▒▒▒    ░░▓▓▒▒      ▓▓▓▓▓▓▓▓▓▓",
            "██▒▒▒▒▒▒▒▒▒▒░░              ██▓▓▓▓▓▓██",
            "██▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓██▓▓    ▓▓▓▓▓▓▓▓██",
            "██▒▒▒▒▒▒▒▒▒▒  ░░▒▒▓▓▓▓▓▓    ▓▓▓▓▓▓▓▓██",
            "██▒▒▒▒▒▒▒▒▒▒    ▓▓▓▓▓▓░░  ░░▓▓▓▓▓▓▓▓██",
            "██▒▒▒▒▒▒▒▒▓▓      ░░      ██▓▓▓▓▓▓▓▓██",
            "██▒▒▒▒▒▒▒▒▒▒▓▓░░        ▓▓▓▓▓▓▓▓▓▓▓▓██",
            "██▓▓▒▒▒▒▒▒▒▒▒▒▓▓▓▓██▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓██"
    });


    private static final ArrayList<String> CAPTCHA = new ArrayList<>(List.of("1181", "1381", "1491", "1722", "1959", "2163",
            "2177", "2723", "2785", "3541", "3847", "3855", "3876", "3967", "4185", "4310", "4487", "4578", "4602", "4681",
            "4924", "5326", "5463", "5771", "5849", "6426", "6553", "6601", "6733", "6960", "7415", "7609", "7755", "7825",
            "7905", "8003", "8070", "8368", "8455", "8506", "8555", "8583", "8692", "8776", "8972", "8996", "9061", "9386",
            "9582", "9633"));
    private final String[] AsciiArt;

    Captcha(String[] asciiArt) {
        AsciiArt = asciiArt;
    }

    public static String getRandomCaptcha() {
        return CAPTCHA.get(new Random().nextInt(CAPTCHA.size()));
    }
    private static Captcha getCaptchaNumberByNumber(int number) {
        switch (number) {
            case 0:
                return ZERO;
            case 1:
                return ONE;
            case 2:
                return TWO;
            case 3:
                return THREE;
            case 4:
                return FOUR;
            case 5:
                return FIVE;
            case 6:
                return SIX;
            case 7:
                return SEVEN;
            case 8:
                return EIGHT;
            case 9:
                return NINE;
            default:
                return null;
        }
    }

    public static String getFullArt(ArrayList<Integer> numbersToUse) {
        String[] finalCaptcha = new String[15];
        for (int i = 0; i < 15; i++) {
            finalCaptcha[i] = "";
        }
        for (Integer number : numbersToUse) {
            for (int index = 0; index < 15; index++) {
                finalCaptcha[index] += getCaptchaNumberByNumber(number).getCaptchaArtLine(index);
            }
        }

        StringBuilder result = new StringBuilder();
        for (int index = 0; index < 15; index++) {
            result.append(finalCaptcha[index]).append("\n");
        }
        Random rand = new Random();
        int noiseCount = rand.nextInt(100 * numbersToUse.size()) + 100 * numbersToUse.size();
        for (int i = 0; i < noiseCount; i++) {
            String[] noise = {"*", "%", "$", "?", "#", "@"};
            int start = rand.nextInt(result.length());
            if (result.charAt(start) != '\n') result.replace(start, start + 1, noise[rand.nextInt(noise.length)]);
        }
        return result.toString();
    }

    private String getCaptchaArtLine(int lineIndex) {
        return this.AsciiArt[lineIndex];
    }
}