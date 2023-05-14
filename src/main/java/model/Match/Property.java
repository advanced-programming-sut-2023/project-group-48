package model.Match;

public enum Property {
    COIN("coin"), WOOD("wood"), GOLD("gold"), STONE("stone"), IRON("iron"), HORSE("horse"), OX("ox"), WHEAT("wheat"), FLOUR("flour"), BITUMEN("bitumen"), OIL("oil"), APPLE("apple"),HOPS("hops"),PITCH("pitch") , BARLEY("barley"), MEAT("meat"), CHEESE("cheese"), BREAD("bread"), BEER("beer"), ARMOUR("armour"), MACE("mace"), SWORD("sword"), BOW("bow"), SPEAR("spear"), PIKE("pike"), CROSSBOW("crossbow"), LEATHERARMOUR("leatherarmour");
    private static final Property[] properties = {COIN, WOOD, GOLD, STONE, IRON, HORSE, OX, WHEAT, FLOUR, BITUMEN, OIL, APPLE,HOPS, BARLEY, MEAT, CHEESE, BREAD, BEER, ARMOUR, MACE, SWORD, BOW, SPEAR, PIKE, CROSSBOW};
    private static final Property[] foods = {MEAT, APPLE, CHEESE, BREAD};
    private static final Property[] weapons = {ARMOUR, MACE, SWORD, BOW, SPEAR, PIKE, CROSSBOW, LEATHERARMOUR};
    private final String propertyInString;

    private Property(String propertyInString) {
        this.propertyInString = propertyInString;
    }
    public static Property[] getAllProperties() {
        return properties;
    }
    public static Property[] getAllFoods() {
        return foods;
    }

    public static Property getProperty(String propertyInString) {
        for (Property property : properties) {
            if (property.propertyInString.equals(propertyInString)) {
                return property;
            }
        }
        return null;
    }

    public static boolean isFood(Property property) {
        for (Property food : foods) {
            if (food.equals(property)) return true;
        }
        return false;
    }

    public String getPropertyInString() {
        return propertyInString;
    }
}
