package com.isa;

import com.isa.menu.Menu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		Menu.getMenu();
		SpringApplication.run(Application.class, args);
	}

}
