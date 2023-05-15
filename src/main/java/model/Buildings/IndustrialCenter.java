package model.Buildings;

import model.Match.Direction;
import model.Match.Governance;
import model.Match.Property;
import model.User;

import java.util.HashMap;

public class IndustrialCenter extends Building {
    private static final HashMap<String, Property[]> industrialCentersProperties = new HashMap<>() {{
        // used property-provided property

        put("Stable", new Property[]{null, Property.OX});
        put("Mill", new Property[]{Property.WHEAT, Property.FLOUR});
        put("Iron Mine", new Property[]{null, Property.IRON});
        put("Pitch Rig", new Property[]{null, Property.PITCH});
        put("ًQuarry", new Property[]{null, Property.STONE});
        put("Woodcutter", new Property[]{null, Property.WOOD});//TODO: 2.cutting trees
        put("Oil Smelter", new Property[]{null, Property.OIL});
        put("Apple Orchard", new Property[]{null, Property.APPLE});
        put("Diary Farmer", new Property[]{null, Property.CHEESE});
        put("Hops Farmer", new Property[]{null, Property.HOPS});
        put("Hunter Post", new Property[]{null, Property.MEAT});
        put("Wheat Farmer", new Property[]{null, Property.WHEAT});
        put("Bakery", new Property[]{Property.FLOUR, Property.BREAD});
        put("Brewer", new Property[]{Property.HOPS, Property.BEER});
        put("Armourer", new Property[]{Property.IRON, Property.ARMOUR});
        put("Blacksmith", new Property[]{Property.IRON, Property.SWORD});
        put("Fletcher", new Property[]{Property.WOOD, Property.BOW});
        put("Pole Turner", new Property[]{Property.IRON, Property.SPEAR});
    }};
    private static final HashMap<String, Integer> industrialCentersRate = new HashMap<>() {{
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
    private static final HashMap<String, String> industrialCentersWorkers = new HashMap<>() {{
        put("Stable", "Ox");
        put("Mill", "Miller");
        put("Iron Mine", "Iron Miner");
        put("Pitch Rig", "Quarry Worker");
        put("Quarry", "Quarry Worker");
        put("Woodcutter", "Woodcutter");
        put("Oil Smelter", "Quarry Smelter");
        put("Apple Orchard", "Farmer");
        put("Diary Farmer", "Farmer");
        put("Hops Farmer", "Farmer");
        put("Hunter Post", "Hunter");
        put("Wheat Farmer", "Farmer");
        put("Bakery", "Baker");
        put("Brewer", "Brewer");
        put("Armourer", "Armourer");
        put("Blacksmith", "Blacksmith");
        put("Fletcher", "Fletcher");
        put("Pole Turner", "Tanner");
    }};
    public static String getWorkerType(String buildingType){
        return industrialCentersWorkers.get(buildingType);
    }
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
        this.getGovernance().unloadStorages(usedProperty, rate);
        this.getGovernance().loadStorages(producedProperty, rate);
    }

    public int getRate() {
        return rate;
    }
}