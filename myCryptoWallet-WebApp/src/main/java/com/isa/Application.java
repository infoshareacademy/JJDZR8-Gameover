package com.isa;

import com.isa.control.Coin;
import com.isa.control.Coins;
import com.isa.control.Endpoints;
import com.isa.service.CoinService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		List<Coin> coinList = CoinService.getCoinList();
		//coinList.forEach(System.out::println);

		SpringApplication.run(Application.class, args);
	}

}
