package model.Match;

public enum Property {
    COIN("coin"), WOOD("wood"), GOLD("gold"), STONE("stone"), IRON("iron"), HORSE("horse"), OX("ox"), WHEAT("wheat"), FLOUR("flour"), BITUMEN("bitumen"), OIL("oil"), APPLE("apple"), BARLEY("barley"), MEAT("meat"), CHEESE("cheese"), BREAD("bread"), BEER("beer"), ARMOUR("armour"), MACE("mace"), SWORD("sword"), BOW("bow"), SPEAR("spear"), PIKE("pike");
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
