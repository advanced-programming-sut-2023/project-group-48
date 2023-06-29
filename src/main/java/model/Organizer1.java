package model;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Objects;

public class Organizer1 {
    private static HashMap<String, String> newNames = new HashMap<>();

    static {
        newNames.put("body_arab_assasin", "Assassins");
        newNames.put("body_arab_slave", "Slaves");
        newNames.put("body_arab_swordsman", "Arabian Swordsmen");
        newNames.put("body_archer", "Archer");
        newNames.put("body_armourer", "Armourer");
        newNames.put("body_baker", "Baker");
        newNames.put("body_blacksmith", "Blacksmith");
        newNames.put("body_brewer", "Brewer");
        newNames.put("body_crossbowman", "Crossbowmen");
        newNames.put("body_farmer", "Farmer");
        newNames.put("body_fighting_monk", "Black Monk");
        newNames.put("body_fletcher", "Fletcher");
        newNames.put("body_horse_archer", "Horse Archers");
        newNames.put("body_horse_archer_top", "Horse Archers T");
        newNames.put("body_hunter", "Hunter");
        newNames.put("body_innkeeper", "Innkeeper");
        newNames.put("body_iron_miner", "Iron Miner");
        newNames.put("body_knight", "Knight");
        newNames.put("body_knight_top", "Knight T");
        newNames.put("body_ladder_bearer", "Laddermen");
        newNames.put("body_lord", "Sultan");
        newNames.put("body_maceman", "Macemen");
        newNames.put("body_miller", "Miller");
        newNames.put("body_pikeman", "Pikemen");
        newNames.put("body_siege_engineer", "Engineer");
        newNames.put("body_spearman", "Spearmen");
        newNames.put("body_stonemason", "Stone Miner");
        newNames.put("body_swordsman", "Swordsmen");
        newNames.put("body_tanner", "Tanner");
        newNames.put("body_trader", "Trader");
        newNames.put("body_tunnelor", "Tunneler");
        newNames.put("body_woodcutter", "Woodcutter");
        // Slingers  Archer Bow  Fire Throwers Quarry Worker
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        File peopleDirectory = new File("D:\\PG\\UN\\Term 2\\1- AP\\6- Project\\strong 3");
        File[] directories = peopleDirectory.listFiles();
        assert directories != null;
        System.out.println(directories.length);
        for (File directory : directories) {
            File[] colorDirectories = directory.listFiles();
            for (File colorDirectory : colorDirectories) {
                if (colorDirectory.getPath().contains("body")) renameDirectory(colorDirectory);
            }
        }
    }

    private static void copyFiles(File directory) throws URISyntaxException, IOException {
        File sourceDirectory = new File(directory.getPath() + "/Images");
        File[] sourceFiles = sourceDirectory.listFiles();
        File[] destinationDirectories = new File[8];
        int index = 1, counter = 0, finalCount = sourceFiles.length;
        while (true) {
            File sourceFile = new File(sourceDirectory.getPath() + "/Image" + index + ".png");
            if (sourceFile.exists())
                sourceFile.renameTo(new File(sourceFile.getPath().replace("Image" + index, String.valueOf(++counter))));
            if (counter == finalCount) break;
            index++;
        }
        for (int i = 0; i < 8; i++) {
            destinationDirectories[i] = new File(directory.getPath() + "/" + (i + 1));
            destinationDirectories[i].mkdir();
//            destinationDirectories[i].delete();
        }
        int sourceCount = Objects.requireNonNull(sourceDirectory.listFiles()).length;
        for (int i = 0; i < sourceCount; i++) {
            File sourceFile = new File(sourceDirectory.getPath() + "/" + (i + 1) + ".png");
            File destinationFile = new File(destinationDirectories[i % 8].getPath() + "/" +
                    (Objects.requireNonNull(destinationDirectories[i % 8].listFiles()).length + 1) + ".png");
            FileUtils.copyFile(sourceFile, destinationFile);
            FileUtils.delete(sourceFile);
        }
    }

    private static void renameDirectory(File directory) throws IOException {
        FileUtils.delete(new File(directory.getPath() + "/Images"));
        directory.renameTo(new File(directory.getParent() + "/" + newNames.get(directory.getName())));
    }
}