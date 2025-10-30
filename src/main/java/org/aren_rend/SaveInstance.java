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
            String date = determineExistsDateNotes(fileReader);

            if(!note.isEmpty()) {
                if(date != null) {
                    fileWriter.write(date);
                    fileWriter.newLine();
                }
                note = ". " + note;
                String lastLine = findLastLine();
                if(!lastLine.equals(date)) {
                    int index = determineLineNumber(lastLine);
                    index++;
                    fileWriter.write(index + note);
                    fileWriter.newLine();
                } else {
                    fileWriter.write("0. " + note);
                    fileWriter.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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

    protected static String findLastLine() {
        String lastLine = "";
        String currentLine;
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath.toFile()))){
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
        return pointIndex != -1 ? Integer.parseInt(line.substring(0, pointIndex).trim()) : 0;
    }

    protected static String determineExistsDateNotes(BufferedReader fileReader) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("---dd.MM.yyyy---");
        String currentDate = LocalDate.now().format(formatter);
        String currentLine;
        boolean isFound = false;
        try {
            while((currentLine = fileReader.readLine()) != null) {
                if(currentLine.equals(currentDate)) {
                    isFound = true;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(!isFound) {
            return currentDate;
        }
        return null;
    }

}
