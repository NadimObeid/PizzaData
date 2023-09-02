package server.pizza;

import jakarta.persistence.Embeddable;

@Embeddable
public record Topping(String toppingIngredient, double multiplier) {

}
