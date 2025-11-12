package org.aren_rend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataOutput {
    private static final String filePath = "C:/7._Projects/Spending_Console/SaveInformation.txt";

    protected static void showNotes() {
        try(BufferedReader fileReader = Files.newBufferedReader(Path.of(filePath), StandardCharsets.UTF_8)){
            String line;
            while((line = fileReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch(IOException e) {
            throw new RuntimeException("Error read file: " + e.getMessage(), e);
        }
    }
}
