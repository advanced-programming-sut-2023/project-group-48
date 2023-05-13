package model.Match;

public enum Food {
    ;
    private final String name;

    private Food(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
