package view;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class test {
    public static void main(String[] args) throws IOException, URISyntaxException {
        File peopleDirectory = new File("C:\\PG\\AP\\Project\\project-group-48\\src\\main\\resources\\people");
        File[] directories = peopleDirectory.listFiles();
        assert directories != null;
        System.out.println(directories.length);
        for (File directory : directories) {
            copyFiles(directory);
        }
    }

    private static void copyFiles(File directory) throws URISyntaxException, IOException {
        File sourceDirectory = new File(directory.getPath() + "/Images");
        File[] sourceFiles = sourceDirectory.listFiles();
        File[] destinationDirectories = new File[8];
        for (int i = 0; i < 8 ; i++) {
            destinationDirectories[i] = new File(directory.getPath() + "/"+ (i + 1));
//            destinationDirectories[i].mkdir();
//            destinationDirectories[i].delete();
        }
        for (int i = 0; i < Objects.requireNonNull(sourceFiles).length; i++) {
            File file = new File(sourceDirectory.getPath() + "/Image" + (i + 1) + ".png");
            FileUtils.delete(file);
//            FileUtils.copyFile(new File(sourceDirectory.getPath() + "/Image" + (i + 1) + ".png"),
//                    new File(destinationDirectories[i % 8].getPath() + "/" + (Objects.requireNonNull(destinationDirectories[i % 8].listFiles()).length + 1) + ".png"));
        }
        for (int i = 0; i < 8; i++) {
            if (sourceDirectory.exists()) FileUtils.delete(sourceDirectory);
        }
    }
}
