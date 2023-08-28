package server.Order;

import server.Service.IngredientService;
import server.pizza.*;
import server.pizza.constants.PizzaSize;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class OrderBuilder {
    private Order order;
    private PizzaBuilder pizza;
    private List<PizzaBuilder> pizzaList = new ArrayList<>();


    public Order placeOrder(String customerPhoneNumber) {
        List<Pizza> list = pizzaList.stream().map(PizzaBuilder::build).toList();
        this.order = new Order(customerPhoneNumber, list);
        return order;
    }

    public OrderBuilder generateNewPizza(PizzaSize size) {
        this.pizza = new PizzaBuilder(size);
        pizzaList.add(pizza);
        return this;
    }

    public OrderBuilder addBase(Base base, int i) {
        pizzaList.get(i).withBase(base);
        return this;
    }

    public OrderBuilder addDrizzle(Drizzle drizzle, int i) {
        pizzaList.get(i).withDrizzle(drizzle);
        return this;
    }

    public OrderBuilder addToppings(List<Topping> toppings, int i) {
        pizzaList.get(i).withToppings(toppings);
        return this;
    }
}
