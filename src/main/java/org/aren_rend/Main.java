package org.aren_rend;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ShowVariations.showGreeting();
        ShowVariations.showMenu();

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch(choice) {
            case 1: break;
            case 2: break;
            case 3: break;
        }

    }
}