package com.isa.menu;
import javax.swing.text.Position;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StartApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        do {
            PrintOut.printAppName();
            Menu.printMenu();
            try {
                int userNumber = sc.nextInt();
                System.out.println(Menu.getMenuItem(userNumber));
            } catch (InputMismatchException e) {
                System.out.println("Podana wartość musi być liczbą");
            }
        } while (sc.nextInt() != Menu.EXIT.getPosition());
    }
}