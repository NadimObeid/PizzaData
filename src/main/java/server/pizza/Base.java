package server.pizza;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import server.pizza.constants.ServingQuantity;

@Embeddable
public record Base(@JsonProperty("name") String baseIngredient, @JsonProperty("servingQuantity") @Enumerated ServingQuantity baseServingQuantity) {
    public Double getQuantity(){
        return switch (baseServingQuantity){
            case TINY -> 0.5;
            case MEDIUM -> 1.0;
            default -> 1.5;
        };
    }
}
