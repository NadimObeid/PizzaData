package server.pizza;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import server.Service.IngredientService;
import server.pizza.constants.PizzaSize;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PizzaBuilder {
    private PizzaSize size;
    private Base base;
    private List<Topping> toppings;

    private Drizzle drizzle;
    private Double price = 0.00;

    @Autowired
    private IngredientService ingredientService;

    public PizzaBuilder chooseSize(PizzaSize size){
        if(this.size == null){
            this.size=size;
        }
        return this;
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


    public PizzaBuilder withBase(Base base) {
        if (this.base != null) {
            this.base = base;
            try {
                price += base.getQuantity() * ingredientService.getByName(base.baseIngredient()).getPrice();
                //ingredientService.consumeIngredient(base.baseIngredient(), base.getQuantity());
            } catch (NoSuchElementException e) {
                System.err.format("Our inventory does not contain %s at this moment.", base.baseIngredient());
            }
        }
        return this;
    }

    public PizzaBuilder withToppings(@NotNull List<Topping> toppings) {
        Double totalPrice = toppings.stream()
                .map(n -> n.multiplier() * ingredientService.getByName(n.toppingIngredient()).getServingQuantity() * ingredientService.getByName(n.toppingIngredient()).getPrice())
                .reduce(0.0, Double::sum);
        this.toppings = toppings;
        price += totalPrice;
        return this;
    }

    public PizzaBuilder withDrizzle(Drizzle drizzle) {
        if (this.drizzle == null) {
            this.drizzle = drizzle;
            double quantum = drizzle.getQuantity() * ingredientService.getByName(drizzle.drizzleIngredient()).getServingQuantity();
            try {
                price += quantum * ingredientService.getByName(drizzle.drizzleIngredient()).getPrice();
                //ingredientService.consumeIngredient(drizzle.drizzleIngredient(), quantum);
            } catch (NoSuchElementException e) {
                System.err.format("Our inventory does not contain %s at this moment.", drizzle.drizzleIngredient());
            }
        }
        return this;
    }

    public Pizza build() {
        return new Pizza(this);
    }
}
