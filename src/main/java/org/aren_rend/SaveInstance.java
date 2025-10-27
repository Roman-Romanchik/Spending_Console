package org.aren_rend;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveInstance {

    private static final Path filePath = Paths.get("C:/7._Projects/Spending_Console/SaveInformation.txt");

    protected static void createSavingDataFile() {
        if(!Files.exists(filePath)) {
            try{
                Files.createFile(filePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected static void addNote(BufferedReader consoleReader) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile(), true))) {
            String note = consoleReader.readLine();
            writer.write(note);
            writer.newLine();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected static void showNotes() {
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))){
            String line;
            while((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

    }
}
