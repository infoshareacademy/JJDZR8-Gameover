package com.isa.boundary;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa.control.Coin;
import com.isa.control.Data;
import com.isa.control.Endpoints;
import com.isa.control.Transaction;
import com.isa.menu.Menu;

public class App {
    public static void main(String[] args) {
      new Endpoints();
      Menu.getMenu();



    }

}

