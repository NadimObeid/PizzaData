package server.pizza;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import server.pizza.constants.ServingQuantity;

@Embeddable
public record Base(@JsonProperty String baseIngredient, @JsonProperty @Enumerated ServingQuantity baseServingQuantity) {
    public Double getQuantity(){
        if(this.baseServingQuantity==ServingQuantity.TINY){
            return 0.5;
        } else if (this.baseServingQuantity==ServingQuantity.MEDIUM) {
            return 1.0;
        }else{
            return 1.5;
        }
    }
}
