package model.Buildings;

import model.Match.Direction;
import model.Match.Governance;
import model.Match.Property;
import model.User;

import java.util.HashMap;

public class IndustrialCenter extends Building {
    private static final HashMap<String, Property[]> industrialCentersProperties = new HashMap<>() {{
        // used property-provided property
        // TODO: 5/29/2021 add properties
        put("Stable", new Property[]{Property.OX, Property.OX});
                put("Mill", new Property[]{Property.WHEAT, Property.OX});
                put("Iron Mine", new Property[]{Property.IRON, Property.OX});
                put("Pitch Rig", new Property[]{Property.IRON, Property.OX});
                put("ًQuarry", new Property[]{Property.STONE, Property.OX});
                put("Woodcutter", new Property[]{Property.WOOD, Property.OX});
                put("Oil Smelter", new Property[]{Property.OIL, Property.OX});
                put("Apple Orchard", new Property[]{Property.APPLE, Property.OX});
                put("Diary Farmer", new Property[]{Property.CHEESE, Property.OX});
                put("Hops Farmer", new Property[]{Property.IRON, Property.OX});
                put("Hunter Post", new Property[]{Property.MEAT, Property.OX});
                put("Wheat Farmer", new Property[]{Property.WHEAT, Property.OX});
                put("Bakery", new Property[]{Property.BREAD, Property.OX});
                put("Brewer", new Property[]{Property.IRON, Property.OX});
                put("Armourer", new Property[]{Property.ARMOUR, Property.OX});
                put("Blacksmith", new Property[]{Property.IRON, Property.OX});
                put("Fletcher", new Property[]{Property.BOW, Property.OX});
                put("Pole Turner", new Property[]{Property.IRON, Property.OX});
    }};
    private static final HashMap<String, Integer> industrialCentersRate = new HashMap<>(){{
        // TODO: 5/29/2021 add properties
        put("Stable", 10);
        put("Mill", 10);
        put("Iron Mine", 10);
        put("Pitch Rig", 10);
        put("ًQuarry", 10);
        put("Woodcutter", 10);
        put("Oil Smelter", 10);
        put("Apple Orchard", 10);
        put("Diary Farmer", 10);
        put("Hops Farmer", 10);
        put("Hunter Post", 10);
        put("Wheat Farmer", 10);
        put("Bakery", 10);
        put("Brewer", 10);
        put("Armourer", 10);
        put("Blacksmith", 10);
        put("Fletcher", 10);
        put("Pole Turner", 10);
    }};
    private final Property usedProperty;
    private final Property producedProperty;
    private final int rate;

    public IndustrialCenter(Governance governance, int row, int column, String type, BuildingType buildingType, Direction direction) {
        super(governance, row, column, type, buildingType, direction);
        usedProperty = industrialCentersProperties.get(type)[0];
        producedProperty = industrialCentersProperties.get(type)[1];
        rate = industrialCentersRate.get(type);
    }

    public boolean canProduce() {
        return this.getGovernance().getPropertyCount(usedProperty) >= rate;
    }

    public void produce() {
        this.getGovernance().reduceProperty(usedProperty, rate);
        this.getGovernance().addProperty(producedProperty, rate);
    }

    public int getRate() {
        return rate;
    }
}