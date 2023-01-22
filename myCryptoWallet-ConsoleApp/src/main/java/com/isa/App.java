package com.isa;

import com.isa.menu.Menu;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
      new Endpoints();
      Menu.getMenu();

 /*       Map<String,String> map = new LinkedHashMap<>();
        map = Endpoints.getCoinsNames();
 //       map.forEach((k,v) -> System.out.println(k + " - " + v));

        Stream stream = map.keySet().stream().filter(n -> n.matches("^\\[(\"[A-Z0-9-_.]{1,20}\"(,\"[A-Z0-9-_.]{1,20}\"){0,}){0,1}\]$"));
        stream.forEach(System.out::println);
  */
    }

}

