package com.isa.menu;

import java.util.Scanner;

public class PrintOut {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do {
            Menu.getMenu();
        } while (sc.nextInt() != Menu.EXIT.getPosition());
    }
}


