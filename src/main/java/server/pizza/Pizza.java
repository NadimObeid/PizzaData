package server.pizza;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import server.pizza.constants.PizzaSize;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="Pizza")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value=EnumType.STRING)
    private PizzaSize size;
    @Embedded
    private Base base;
    @ElementCollection
    @CollectionTable(name = "toppings", joinColumns = @JoinColumn(name = "pizza_id"))
    private List<Topping> toppings;
    @Embedded
    private Drizzle drizzle;

    protected Pizza(@JsonProperty("size") PizzaSize size, @JsonProperty("base")Base base, @JsonProperty("toppings")List<Topping> toppings, @JsonProperty("drizzle")Drizzle drizzle) {
        this.size = size;
        this.base = base;
        this.toppings = toppings;
        this.drizzle = drizzle;
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

    public Map<String, Double> getTotalIngredientsWithQuantities(){
        Map<String, Double> ingredientMap = new HashMap<>();
        if(base != null)
            ingredientMap.put(base.baseIngredient(),base.getQuantity());
        if(drizzle != null)
            ingredientMap.put(drizzle.drizzleIngredient(), drizzle.getQuantity());
        if(toppings != null)
            toppings.forEach(topping -> ingredientMap.put(topping.toppingIngredient(), topping.multiplier()));
        return ingredientMap;
    }

}


