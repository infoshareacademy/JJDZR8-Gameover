package com.isa;

import java.util.Scanner;

public class Menu {
    private static final int EXIT = 0;


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

    do {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ \n" +
                "myCryptoWallet \n" +
                "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    System.out.println("Daj znać co chcesz zrobić, wybierając odpowiednią cyfrę:" +
                    "\n 1 - Dodaj nowy coin " +
                    "\n 2 - Wyszukaj coin z listy " +
                    "\n 3 - Wyświetl listę coin-ów " +
                    "\n 4 - Zaktualizuj listę coin–ów" +
                    " \n 5 - Dodaj coin-a do listy ulubionych" +
                    "\n 6 - Eksportuj bibliotekę do pliku/Importuj bibliotekę z pliku" +
                    " \n 7 - Zakończ działanie programu");

    int mainOption = input.nextInt();
    input.nextLine();

    if(mainOption == 1){
        System.out.println("Dodałeś coin-a");
    }
    if(mainOption == 2){
        System.out.println("Wyszukałeś coin-a");
    }
    if (mainOption == 3) {
        System.out.println("Wyświetliłeś listę coin-ów");
    }
    if (mainOption == 4) {
        System.out.println("Zaktualizowałeś listę coin-ów");
    }
    if (mainOption == 5) {
        System.out.println("Dodałeś coin-a do listy ulubionych");
    }
    if (mainOption == 6) {
            System.out.println("Wyeksportowałeś bibliotekę do pliku/Zaimportowałeś bibliotekę z pliktu");
    }
    if (mainOption == 7) {
            System.out.println("Zakończ działanie programu");
    }
    System.out.println("Koniec programu, wprowadź " + EXIT);
    System.out.println("Kontynuuj, wprowadź wartość inną od " + EXIT);
}
            while (input.nextInt() != EXIT) ;
            input.close();
}
}

