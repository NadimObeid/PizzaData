package server.pizza;

import jakarta.persistence.*;

@Embeddable
public record Topping(String toppingIngredient, Double multiplier) {

}
