package org.aren_rend;

public class ShowVariations {

    private final static String[] menu = new String[]{"\n1. History", "2. Add spending", "3. Exit"};
    private final static String newNoteTitle = "Write your spending one at a time\n";
    private final static String[] notesMenu = new String[] {"\n1.Edit", "2. Calculate", "3. Exit"};

    protected static void showMenu() {
        for(String choice: menu) {
            System.out.println(choice);
        }
    }

    protected static void showNewNoteTitle() {
        System.out.println(newNoteTitle);
    }

    protected static void showNotesMenu() {
        for(String choice: notesMenu) {
            System.out.println(choice);
        }
    }

}
