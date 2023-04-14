package model.Buildings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum BuildingType {
    NORMAL (new ArrayList<>(List.of())) ,
    CASTLE (new ArrayList<>(List.of())),
    TOWER (new ArrayList<>(List.of())),
    INDUSTRIAL_CENTER (new ArrayList<>(List.of())),
    INN (new ArrayList<>(List.of())),
    KILLING_PIT (new ArrayList<>(List.of())),
    PITCH_DITCH (new ArrayList<>(List.of())),
    RECRUTIMENT_CENTER (new ArrayList<>(List.of())),
    STASH (new ArrayList<>(List.of())),
    WEAPONARY (new ArrayList<>(List.of()))
    ;
    private final ArrayList<String> buildings;

    BuildingType(ArrayList<String> buildings) {
        this.buildings = buildings;
    }

    public static BuildingType getBuildingType(String type) {
    }

}
