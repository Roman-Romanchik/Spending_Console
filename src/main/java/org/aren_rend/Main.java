package org.aren_rend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static boolean isContinue = true;

    public static void main(String[] args) {
        ShowVariations.showGreeting();
        SaveInstance.createSavingDataFile();

        try(BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
            while(isContinue) {
                ShowVariations.showMenu();
                switch(consoleReader.readLine()) {
                    case "1":
                        ShowVariations.showHistoryTitle();
                        SaveInstance.showNotes();
                        break;
                    case "2":
                        ShowVariations.showNewNoteTitle();
                        SaveInstance.addNote(consoleReader);
                        break;
                    case "3":
                        isContinue = false;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}