package server.pizza;

import server.pizza.constants.ServingQuantity;
import server.pizza.constants.ServingStyle;

public record Drizzle(String drizzleIngredient, ServingStyle drizzleStyle, ServingQuantity drizzleQuantity) {
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
