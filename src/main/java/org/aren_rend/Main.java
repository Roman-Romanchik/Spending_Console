package org.aren_rend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static boolean isContinue = true;

    public static void main(String[] args) {
        SelectionScreen ss = new SelectionScreen();
        System.out.println(ss.getGreeting());
        SaveInstance.createSavingDataFile();

        try(BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
            while(isContinue) {
                ss.getMainMenu();
                switch(consoleReader.readLine()) {
                    case "1":
                        System.out.println(ss.getHistoryTitle());
                        SaveInstance.showNotes();
                        ShowVariations.showNotesMenu();
                        switch (consoleReader.readLine()) {
                            case "1":
                                System.out.println("Write string, which we will edit:");
                                String oldLine = consoleReader.readLine();
                                System.out.println("Write string, which will replace previously:");
                                String newLine = consoleReader.readLine();
                                SaveInstance.editNote(oldLine, newLine);
                                break;
                            case "2":
                                System.out.println(DefineData.calculateAllSpending());
                            default:
                                break;

                        }
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