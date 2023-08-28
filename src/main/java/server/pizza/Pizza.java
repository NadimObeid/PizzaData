package server.pizza;

import server.pizza.constants.PizzaSize;

import java.util.List;

public class Pizza {
    private PizzaSize size;
    private Base base;
    private List<Topping> toppings;
    private Drizzle drizzle;

    private Double price;

    protected Pizza(PizzaBuilder builder) {
        size = builder.getSize();
        base = builder.getBase();
        toppings = builder.getToppings();
        drizzle = builder.getDrizzle();
        price = builder.getPrice();
    }

    public static PizzaBuilder getBuilder(PizzaSize size) {
        return new PizzaBuilder(size);
    }

    public PizzaSize getSize() {
        return size;
    }

    public Base getBase() {
        return base;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public Drizzle getDrizzle() {
        return drizzle;
    }

    public Double getPrice() {
        return price;
    }
}


