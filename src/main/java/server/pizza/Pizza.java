package server.pizza;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import server.Service.IngredientService;
import server.pizza.constants.PizzaSize;


import java.util.List;
import java.util.NoSuchElementException;

@Entity
@Table(name = "pizza")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated
    private PizzaSize size;
    @Embedded
    private Base base;
    @Embedded
    @ElementCollection
    private List<Topping> toppings;
    @Embedded
    private Drizzle drizzle;

    private Double price;

    protected Pizza(@NotNull PizzaBuilder builder) {
        size = builder.getSize();
        base = builder.getBase();
        toppings = builder.getToppings();
        drizzle = builder.getDrizzle();
        price = builder.getPrice();
        this.id=id;
    }

    public static PizzaBuilder getBuilder(PizzaSize size) {
        return new PizzaBuilder().chooseSize(size);
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


