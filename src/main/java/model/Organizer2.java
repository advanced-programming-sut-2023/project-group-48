package model;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Organizer2 {
    public static void main(String[] args) throws IOException {
        File peopleDirectory = new File("D:\\PG\\UN\\Term 2\\1- AP\\6- Project\\strong 3");
        File[] directories = peopleDirectory.listFiles();
        assert directories != null;
        for (File directory : directories) {
            File targetDirectory = new File(directory.getPath() + "\\body_woodcutter");
            removeStatics(targetDirectory, 961, 1032);
        }
    }

    private static void removeStatics(File targetDirectory, int start, int end) throws IOException {
        File staticsDirectory = new File(targetDirectory.getPath() + "\\Static");
        staticsDirectory.mkdir();
        File sourceDirectory = new File(targetDirectory.getPath() + "\\Images");
        for (int i = start; i <= end; i++) {
            File sourceFile = new File(sourceDirectory.getPath() + "\\Image" + i + ".png");
            File staticFile = new File(staticsDirectory.getPath() + "\\" +
                    (Objects.requireNonNull(staticsDirectory.listFiles()).length + 1) + ".png");
            FileUtils.copyFile(sourceFile, staticFile);
            sourceFile.delete();
        }
    }
}