package model.Buildings;

import model.Match.Direction;
import model.Match.Governance;
import model.Match.Property;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Storage extends Building {
    private static final HashMap<String, Integer> storagesCapacities = new HashMap<>() {{
        put("Armoury", 1000);
        put("Stockpile", 1000);
        put("Granary", 1000);
        put("Stable", 1000);
    }};
    private static final HashMap<String, ArrayList<Property>>  storageProperties= new HashMap<>() {{
        put("Armoury", new ArrayList<>() {{;
            add(Property.ARMOUR);
            add(Property.MACE);
            add(Property.SWORD);
            add(Property.BOW);
            add(Property.SPEAR);
            add(Property.PIKE);
            add(Property.CROSSBOW);
            add(Property.LEATHERARMOUR);
        }});
        put("Stockpile", new ArrayList<>() {{
            add(Property.WOOD);
            add(Property.STONE);
            add(Property.GOLD);
            add(Property.IRON);
            add(Property.BITUMEN);
            add(Property.OIL);
            add(Property.PITCH);


        }});
        put("Granary", new ArrayList<>() {{
            add(Property.WHEAT);
            add(Property.FLOUR);
            add(Property.APPLE);
            add(Property.HOPS);
            add(Property.BARLEY);
            add(Property.MEAT);
            add(Property.CHEESE);
            add(Property.BREAD);
            add(Property.BEER);

        }});
        put("Stable", new ArrayList<>() {{
            add(Property.HORSE);
            add(Property.OX);
        }});
    }};
    private final HashMap<Property, Integer> properties;
    private final String type;
    private final int capacity;

    public Storage(Governance governance, int row, int column, String type, BuildingType buildingType, Direction direction) {
        super(governance, row, column, type, buildingType, direction);
        this.properties = new HashMap<>();
        this.capacity = storagesCapacities.get(type);
        this.type = type;
    }

    public void addProperty(Property property, int count) {
        properties.put(property, properties.getOrDefault(property, 0) + count);
    }

    public void removeProperty(Property property, int count) {
        properties.put(property, properties.getOrDefault(property, 0) - count);
    }

    public int getPropertyCount(Property property) {
        return properties.getOrDefault(property, 0);
    }

    public int getCapacity() {
        return capacity;
    }
}
