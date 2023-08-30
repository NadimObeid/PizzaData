package server.Purchase;

import server.pizza.*;
import server.pizza.constants.PizzaSize;

import java.util.ArrayList;
import java.util.List;

public class PurchaseBuilder {
    private Purchase purchase;
    private PizzaBuilder pizza;
    private List<PizzaBuilder> pizzaList = new ArrayList<>();


    public Purchase placeOrder(String customerPhoneNumber) {
        List<Pizza> list = pizzaList.stream().map(PizzaBuilder::build).toList();
        this.purchase = new Purchase(customerPhoneNumber, list);
        return purchase;
    }

    public PurchaseBuilder generateNewPizza(PizzaSize size) {
        this.pizza = new PizzaBuilder().chooseSize(size);
        pizzaList.add(pizza);
        return this;
    }

    public PurchaseBuilder addBase(Base base, int i) {
        pizzaList.get(i).withBase(base);
        return this;
    }

    public PurchaseBuilder addDrizzle(Drizzle drizzle, int i) {
        pizzaList.get(i).withDrizzle(drizzle);
        return this;
    }

    public PurchaseBuilder addToppings(List<Topping> toppings, int i) {
        pizzaList.get(i).withToppings(toppings);
        return this;
    }
}
