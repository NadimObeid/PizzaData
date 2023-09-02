package server.pizza.constants;

import jakarta.persistence.Enumerated;

public enum PizzaSize {
    S(8.99),
    M(12.99),
    L(16.99);

    private final double price;

    PizzaSize(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

}
