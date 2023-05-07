package com.isa.boundary;

import com.isa.control.*;
import com.isa.control.transactions.ActiveTransaction;
import com.isa.menu.Balance;
import com.isa.menu.Menu;

import java.util.HashSet;
import java.util.Set;


public class App {
    public static void main(String[] args) {
        Coins.getInstance();
        Menu.getMenu();
    }
}

