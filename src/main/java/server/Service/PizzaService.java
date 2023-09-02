package server.Service;

import server.Repository.PizzaRepository;
import server.pizza.Pizza;

public class PizzaService {
    private PizzaRepository pizzaRepository;


    public void savePizza(Pizza pizza){
        pizzaRepository.save(pizza);
    }
}
