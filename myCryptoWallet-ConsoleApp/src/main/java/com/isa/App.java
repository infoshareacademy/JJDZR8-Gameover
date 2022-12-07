package com.isa;


import com.isa.menu.Menu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static void main( String[] args ) {
        Scanner sc = new Scanner(System.in);


//        Menu.getMenu();


            while(sc.nextInt() != Menu.EXIT.getPosition())
                try {
                    Menu.printMenu();
                    int chooseOption = sc.nextInt();
                    switch (chooseOption) {
                        case 1:
                            System.out.println(Menu.ADD_COIN);
                            break;
                        case 2:
                            System.out.println(Menu.SEARCH_COIN);
                            break;
                        case 3:
                            System.out.println(Menu.LIST_COINS);
                            break;
                        case 4:
                            System.out.println(Menu.UPDATE_COIN_LIST);
                            break;
                        case 5:
                            System.out.println(Menu.ADD_FAVOURITE_COIN);
                            break;
                        case 6:
                            System.out.println(Menu.EXPORT_FILE);
                            break;
                        case 7:
                            System.out.println(Menu.IMPORT_FILE);
                            break;
                        case 8:
                            System.out.println(Menu.EXIT);
                            break;
                        default:
                            System.out.println("Nie ma takiej opcji, spróbuj ponownie");
                    }
                } catch (InputMismatchException | IllegalArgumentException e) {
                    System.out.println("Podaj liczbę całkowitą");
                    return;
                }
        }

    }
//      public static int getInt() {
//        int valueInt = 0;
//
//        while ((valueInt <= 0)) {
//            String value = scanner.nextLine();
//            try {
//                valueInt = Integer.parseInt(value);
//                if (valueInt <= 0) {
//                    System.out.println(MSG);}
//            } catch (Exception e) {
//                System.out.println(MSG);
//            }
//        }
//        return valueInt;
//    }
//}
