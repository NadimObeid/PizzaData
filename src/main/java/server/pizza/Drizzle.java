package server.pizza;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import server.pizza.constants.ServingQuantity;
import server.pizza.constants.ServingStyle;
@Embeddable
public record Drizzle(@JsonProperty("drizzleName") String drizzleIngredient, @JsonProperty("drizzleStyle") @Enumerated ServingStyle drizzleStyle,@JsonProperty("drizzleQuantity") @Enumerated ServingQuantity drizzleQuantity) {
    public Double getQuantity(){
        return switch (drizzleQuantity){
            case TINY -> 0.5;
            case MEDIUM -> 1.0;
            default -> 1.5;
        };
    }
}
