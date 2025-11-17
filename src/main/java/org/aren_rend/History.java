package org.aren_rend;

public class History {
    private static boolean isContinue = true;
    public void showHistory(SelectionScreen selectionScreen, Validator validator) {
        while(isContinue) {
            selectionScreen.displayHistoryMenu();
            String menuItem = UserInput.readLine();
            chooseHistoryItem(menuItem, selectionScreen, validator);
        }
        isContinue = true;
    }

    private void chooseHistoryItem(String menuItem, SelectionScreen selectionScreen, Validator validator) {
        if(validator.isMenuItem(menuItem, 1, 2)) {
            switch(menuItem) {
                case "1":
                    defineTimePeriod(selectionScreen, validator);
                    break;
                case "2":
                    isContinue = false;
                    break;
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
                    System.out.println("Input date(dd.mm.yyyy): ");
                    firstDate = UserInput.readLine();
                    showSpending(firstDate, "day");
                    break;
                case "2":
                    System.out.println("Input previously monday date(dd.mm.yyyy): ");
                    firstDate = UserInput.readLine();
                    showSpending(firstDate, "week");
                    break;
                case "3":
                    System.out.println("Input first date(dd.mm.yyyy): ");
                    firstDate = UserInput.readLine();
                    System.out.println("Input second date(dd.mm.yyyy): ");
                    secondDate = UserInput.readLine();
                    showSpending(firstDate, secondDate);
                    break;
                case "4":
                    isContinue = false;
                    break;
            }
        } else {
            System.out.println("Wrong parameter! Write digit 1 or 4: ");
        }
    }

    private void showSpending(String firstDate, String secondDate) {
        if(secondDate.equals("day")) {

        } else if(secondDate.equals("week")){

        } else {

        }
    }
}
