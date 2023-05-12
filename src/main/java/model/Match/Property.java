package model.Match;

public enum Property {
    COIN("coin"), WOOD, GOLD, STONE, IRON, HORSE, OX, WHEAT, FLOUR, BITUMEN, OIL, APPLE, BARLEY, MEAT, CHEESE, BREAD, BEER, ARMOUR, MACE, SWORD, BOW, SPEAR, PIKE;
    public static final Property[] properties = {COIN, WOOD, GOLD, STONE, IRON, HORSE, OX, WHEAT, FLOUR, BITUMEN, OIL, APPLE, BARLEY, MEAT, CHEESE, BREAD, BEER, ARMOUR, MACE, SWORD, BOW, SPEAR, PIKE};
    private final String propertyInString;

    private Property(String propertyInString) {
        this.propertyInString = propertyInString;
    }
    public static Property getProperty(String propertyInString) {
        for (Property property : properties) {
            if (property.propertyInString.equals(propertyInString)) {
                return property;
            }
        }
        return null;
    }

    public String getPropertyInString() {
        return propertyInString;
    }
}
