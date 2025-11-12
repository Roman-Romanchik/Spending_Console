package org.aren_rend;

import org.gradle.internal.cc.impl.CheckedFingerprint;

public class History {
    private static boolean isContinue = true;
    public void showHistory(SelectionScreen selectionScreen, Validator validator) {
        while(isContinue) {
            selectionScreen.displayHistoryMenu();
            String menuItem = UserInput.readLine();
            chooseHistoryItem(menuItem, selectionScreen, validator);
            if(isContinue) {
                showSpending();
            }
        }
    }

    private void chooseHistoryItem(String menuItem, SelectionScreen selectionScreen, Validator validator) {
        if(validator.isMenuItem(menuItem, 1, 2)) {
            switch(menuItem) {
                case "1":
                    defineTimePeriod(selectionScreen, validator);
                    break;
                case "2":
                    isContinue = false;
            }
        } else {
            System.out.println("Wrong parameter! Write digit 1 or 2: ");
        }
    }

    private void defineTimePeriod(SelectionScreen selectionScreen, Validator validator) {
        selectionScreen.displayDefinePeriodMenu();
        String menuItem = UserInput.readLine();
        String firstDate;
        String secondDate;
        if(validator.isMenuItem(menuItem, 1, 4)) {
            switch(menuItem) {
                case "1":
                    System.out.println("Input date: ");
                    firstDate = UserInput.readLine();
                    break;
                case "2":
                    System.out.println("Input first date: ");
                    firstDate = UserInput.readLine();
                    System.out.println("Input second date: ");
                    secondDate = UserInput.readLine();
                    break;
                case "3":

            }
        } else {
            System.out.println("Wrong parameter! Write digit 1 or 2: ");
        }
    }

    private void showSpending() {

    }
}
