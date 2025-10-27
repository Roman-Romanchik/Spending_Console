package org.aren_rend;

public class ShowVariations {

    private final static String[] menu = new String[]{"1. History", "2. Add spending", "3. Calculate spending"};
    private final static String greeting = "\nWelcome in track spending application\n";
    private final static String help = "(Write digit of item)";

    protected static void showGreeting() {
        int difference = leveling(greeting, help);
        System.out.print(greeting);
        while(difference > 0) {
            System.out.print(' ');
            difference--;
        }
        System.out.println(help);
    }

    protected static void showMenu() {
        for(String choice: menu) {
            System.out.println(choice);
        }
    }

    private static int  leveling(String firstString, String secondString) {
        int differenceLength = firstString.length() - secondString.length();
        if(differenceLength % 2 == 1) {
            differenceLength--;
        }
        return differenceLength / 2;
    }
}
