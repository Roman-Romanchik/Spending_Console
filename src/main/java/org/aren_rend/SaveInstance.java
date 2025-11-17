package org.aren_rend;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SaveInstance {

    private static final Path filePath = Paths.get("C:/7._Projects/Spending_Console/SaveInformation.txt");

    protected static void editNote(String oldLine, String newLine) {
        Path tempFilePath = Paths.get(filePath + ".tmp");
        try(BufferedReader fileReader = new BufferedReader(new FileReader(filePath.toFile()));
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(tempFilePath.toFile(), true))) {
            String currentLine;
            while((currentLine = fileReader.readLine()) != null) {
                if(currentLine.equals(oldLine)) {
                    fileWriter.write(newLine);
                } else {
                    fileWriter.write(currentLine);
                }
                fileWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Files.move(tempFilePath, filePath, StandardCopyOption.REPLACE_EXISTING);
            Files.deleteIfExists(tempFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected static void showNotes() {
        try(BufferedReader fileReader = new BufferedReader(new FileReader(filePath.toFile()))){
            String line;
            while((line = fileReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
