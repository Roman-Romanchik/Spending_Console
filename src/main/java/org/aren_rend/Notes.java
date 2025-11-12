package org.aren_rend;

public class Notes {

    public void createNote(SelectionScreen selectionScreen, Validator validator) {
        StringBuilder note = new StringBuilder();
        selectionScreen.displayCategoriesMenu();
        String categoriesItem = UserInput.readLine();
        chooseCategories(categoriesItem, validator, note);
        writeSpendingName(selectionScreen, note);
        writeSpendingCost(selectionScreen, note);
        verificationNote(selectionScreen, validator, note);
    }

    private void chooseCategories(String categoryItem, Validator validator, StringBuilder note) {
        int categoryNumber;
        if(validator.isCategoryItem(categoryItem, 1, 6)) {
            try {
                categoryNumber = Integer.parseInt(categoryItem.trim());
                switch(categoryNumber) {
                    case 1:
                        note.append("Food : ");
                        break;
                    case 2:
                        note.append("Sport : ");
                        break;
                    case 3:
                        note.append("Clothes : ");
                        break;
                    case 4:
                        note.append("Technique : ");
                        break;
                    case 5:
                        note.append("Office : ");
                        break;
                    case 6:
                        note.append("Furniture : ");
                        break;
                }
            } catch (NumberFormatException e) {
                note.append(categoryItem.trim());
            }
        }
    }

    private void writeSpendingName(SelectionScreen selectionScreen, StringBuilder note) {
        System.out.println(selectionScreen.getSpendingName());
        note.append(UserInput.readLine()).append(" : ");
    }

    private void writeSpendingCost(SelectionScreen selectionScreen, StringBuilder note) {
        System.out.println(selectionScreen.getSpendingCost());
        note.append(UserInput.readLine()).append(" : ");
    }

    private void verificationNote(SelectionScreen selectionScreen, Validator validator, StringBuilder note) {
        System.out.println(selectionScreen.getCorrectSpelling() + note);
        System.out.println(selectionScreen.getVerification());
        String verificationChoice = UserInput.readLine();
        if(verificationChoice.equals("2")) {
            createNote(selectionScreen, validator);
        }
    }
}
