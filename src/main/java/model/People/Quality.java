package model.People;

public enum Quality {
    LOW(20),
    MEDIUM(40),
    HIGH(60);
    private int value;

    private Quality(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
