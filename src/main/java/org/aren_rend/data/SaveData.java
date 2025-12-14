package org.aren_rend.data;

import java.io.*;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SaveData {

    public static String save(StringBuilder note, Path filePath) {
        if (note == null || note.isEmpty()) {
            System.out.println("Note is null!");
            return null;
        }
		boolean isToday = false;
        try {
            String dateHeader = determineDateHeader(filePath);
			String result = "";

            try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath.toFile(), true))) {

                if (dateHeader != null) {
					isToday = true;
                    fileWriter.write(dateHeader);
                    fileWriter.newLine();
					result += dateHeader + "\n";
                }

				int lineNumber = determineNextLineNumber(isToday, filePath);
                note.insert(0, lineNumber);
                fileWriter.write(note.toString());
                fileWriter.newLine();
				result += note;
				return result;
            }
        } catch (IOException e) {
            throw new RuntimeException("Error to file write: " + e.getMessage(), e);
        }
    }

    private static String determineDateHeader(Path filePath) {
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

    private static int determineNextLineNumber(boolean isToday, Path filePath) {
        if (!filePath.toFile().exists()) {
            return 1;
        }
		if(isToday) {
			return 1;
		} else {
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
}