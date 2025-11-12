package org.aren_rend;

public class Session {
    private boolean isApplicationWork = true;

    public void startApplication() {
        SelectionScreen selectionScreen = new SelectionScreen();
        selectionScreen.getGreeting();
        Validator validator = new Validator();

        while(isApplicationWork) {
            selectionScreen.displayMainMenu();
            String menuItem = UserInput.readLine();
            if(validator.isMenuItem(menuItem, 1, 3)) {
                switch (menuItem) {
                    case "1":
                        Notes notes = new Notes();
                        notes.createNote(selectionScreen, validator);
                        break;
                    case "2":
                        History history = new History();
                        history.showHistory(selectionScreen, validator);
                        break;
                    case "3":
                        isApplicationWork = false;
                }
            } else {
                System.out.println("Wrong parameter! Write digit from 1 to 3: ");
            }
        }
    }

}
