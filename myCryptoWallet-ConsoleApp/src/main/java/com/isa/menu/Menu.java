package com.isa.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

public enum Menu {
    ADD_COIN(1, "Dodaj nowy coin"),
    SEARCH_COIN(2, "Wyszukaj coin z listy"),
    LIST_COINS(3, "Wyświetl listę coin-ów"),
    UPDATE_COIN_LIST (4, "Zaktualizuj listę coin–ów"),
    ADD_FAVOURITE_COIN (5, "Dodaj coin-a do listy ulubionych"),
    EXPORT_FILE (6, "Exportuj bibliotekę do pliku"),
    IMPORT_FILE (7, "Importuj bibliotekę z pliku"),
    EXIT (8, "Zakończ działanie programu \n  Wybierz opcję");



    private final int position;

    private final String description;

    Menu(final int position, final String description) {
        this.description = description;
        this.position = position;
    }

    int getPosition() {
        return position;
    }

    String getDescription() {
        return description;
    }

    public static void printMenu() {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                "myCryptoWallet \n" +
                "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n");
        for (Menu menu : Menu.values()) {
            System.out.println(menu);
        }

    }
    public static Menu getMenuItem(int number) {
        Menu menuToReturn = null;
        for (Menu menu : Menu.values()) {
            if (menu.getPosition() == number) {
                menuToReturn = menu;
            }
        }
        return menuToReturn;
    }

    public static void getMenu() {
        Scanner sc = new Scanner(System.in);
        do{
        Menu.printMenu();
        try {
            int userNumber = sc.nextInt();

            if (userNumber > Menu.values().length) {
                System.out.println("Wybierz liczbę w zakresie 1-8");

            } else if (userNumber < Menu.values().length) {
                System.out.println(Menu.getMenuItem(userNumber));

            } else if (userNumber == Menu.EXIT.getPosition()) {
                System.out.println("Potwierdzasz wyjście z programu?\nPowtórz liczbę...");
            }
        }   catch (InputMismatchException e) {
            System.out.println("Podana wartość musi być liczbą całkowitą");
            sc.nextLine();
        } }while(sc.nextInt() != Menu.EXIT.getPosition());
    }

    @Override
    public String toString() {
        return position +  " - " + description;
    }
}
