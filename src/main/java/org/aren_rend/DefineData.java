package org.aren_rend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

public class DefineData {
    private static final Path filePath = Paths.get("C:/7._Projects/Spending_Console/SaveInformation.txt");
    private static final LinkedList<String> spending = new LinkedList<>();

    protected static int calculateAllSpending() {
        int sum = 0;
        try(BufferedReader fileReader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String currentLine;
            while((currentLine = fileReader.readLine()) != null) {
                if(!currentLine.contains("---")) {
                    spending.add(currentLine.substring(currentLine.indexOf(':') + 1, currentLine.indexOf("rub")).trim());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(String spend : spending) {
            sum += Integer.parseInt(spend);
        }
        spending.clear();
        return sum;
    }
}
