package com.isa;

import com.isa.menu.Menu;

public class App {
    public static void main(String[] args) {
        Endpoints.setEndpoints(); //na starcie wczytuje symbole z pliku endpoints.json żeby uniknąć stracenia zapisanych "Coinów"
        Menu.getMenu();
    }
}

