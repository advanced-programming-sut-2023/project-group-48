package model;

import java.util.ArrayList;
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
            "██▒▒▒▒▒▒▒▒    ░░▓▓▓▓▓▓▓▓    ▓▓▓▓▒▒▒▒▓▓",
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


    private final String[] AsciiArt;

    Captcha(String[] asciiArt) {
        AsciiArt = asciiArt;
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
        for (Integer number : numbersToUse) {
            for (int index = 0; index < 15; index++) {
                finalCaptcha[index] += getCaptchaNumberByNumber(numbersToUse.get(index)).getCaptchaArtLine(index);
            }
        }

        StringBuilder result = new StringBuilder();
        for (int index = 0; index < 15; index++) {
            result.append(index != 0 ? "\n" : "").append(finalCaptcha[index]);
        }
        Random rand = new Random();
        int noiseCount = rand.nextInt(15 * numbersToUse.size());
        for (int i = 0; i < noiseCount; i++) {
            String[] noise = {"*", "%", "$", "?", "#", "@"};
            int start = rand.nextInt(numbersToUse.size());
            if (result.charAt(start) != '\n') result.replace(start, start + 1, noise[rand.nextInt(noise.length)]);
        }
        return result.toString();
    }

    private String getCaptchaArtLine(int lineIndex) {
        return this.AsciiArt[lineIndex];
    }
}