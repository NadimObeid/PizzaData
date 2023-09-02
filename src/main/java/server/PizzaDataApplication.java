package server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import server.pizza.Pizza;

@SpringBootApplication
public class PizzaDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzaDataApplication.class, args);
	}

}
