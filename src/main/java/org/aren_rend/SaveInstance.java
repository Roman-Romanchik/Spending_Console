package org.aren_rend;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
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
        try(BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath.toFile(), true));
        BufferedReader fileReader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String note = consoleReader.readLine();
            if(!note.isEmpty()) {
                note = ". " + note;
                int index = determineLineNumber(findLastLine(fileReader));
                index++;
                fileWriter.write(index + note);
                fileWriter.newLine();
            }
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

    protected static String findLastLine(BufferedReader fileReader) {
        String lastLine = "";
        String currentLine;
        try {
            while((currentLine = fileReader.readLine()) != null) {
                lastLine = currentLine;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lastLine;
    }

    protected static int determineLineNumber(String line) {
        int pointIndex = line.indexOf('.');
        return pointIndex != -1 ? Integer.parseInt(line.substring(0, pointIndex)) : 0;
    }

}
