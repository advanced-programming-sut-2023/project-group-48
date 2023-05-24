package model.People;

public enum Quality {
    ZERO(0),
    VERY_LOW(10),
    LOW(20),
    MEDIUM(40),
    HIGH(60),
    VERY_HIGH(80);
    private final int value;

    private Quality(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
