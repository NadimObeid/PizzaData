package server.pizza;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;

@Embeddable
public record Topping(@JsonProperty("toppingName") String toppingIngredient, @JsonProperty("multiplier") double multiplier) {

}
