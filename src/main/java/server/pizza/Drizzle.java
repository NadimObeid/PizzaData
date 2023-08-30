package server.pizza;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import server.pizza.constants.ServingQuantity;
import server.pizza.constants.ServingStyle;

@Embeddable
public record Drizzle(@JsonProperty String drizzleIngredient, @JsonProperty @Enumerated ServingStyle drizzleStyle,@JsonProperty @Enumerated ServingQuantity drizzleQuantity) {
    public Double getQuantity(){
        if(this.drizzleQuantity==ServingQuantity.TINY){
            return 0.5;
        } else if (this.drizzleQuantity==ServingQuantity.MEDIUM) {
            return 1.00;
        }else{
            return 1.5;
        }
    }
}
