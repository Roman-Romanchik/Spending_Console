package org.aren_rend;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SaveData {
    private static final Path filePath = Paths.get("C:/7._Projects/Spending_Console/SaveInformation.txt");

    public static void save(StringBuilder note) {
        if (note == null || note.isEmpty()) {
            System.out.println("Note is null!");
            return;
        }

        try {
            String dateHeader = determineDateHeader();
            int lineNumber = determineNextLineNumber();

            try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath.toFile(), true))) {

                if (dateHeader != null) {
                    fileWriter.write(dateHeader);
                    fileWriter.newLine();
                }

                note.insert(0, ". ").insert(0, lineNumber);
                fileWriter.write(note.toString());
                fileWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error to file write: " + e.getMessage(), e);
        }
    }

    private static String determineDateHeader() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("---dd.MM.yyyy---");
        String currentDate = LocalDate.now().format(formatter);

        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String currentLine;

            while ((currentLine = fileReader.readLine()) != null) {
                if(currentLine.equals(currentDate)) {
                    return null;
                }
            }
            return currentDate;

        } catch (IOException e) {
            throw new RuntimeException("Error to file read: " + e.getMessage(), e);
        }
    }

    private static int determineNextLineNumber() {
        if (!filePath.toFile().exists()) {
            return 1;
        }

        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String lastLine = "";
            String currentLine;

            while ((currentLine = fileReader.readLine()) != null) {
                lastLine = currentLine;
            }

            if (lastLine.matches("^\\d+\\..*")) {
                String numberPart = lastLine.substring(0, lastLine.indexOf('.'));
                return Integer.parseInt(numberPart) + 1;
            }
            return 1;

        } catch (IOException e) {
            throw new RuntimeException("Error file read: " + e.getMessage(), e);
        }
    }
}