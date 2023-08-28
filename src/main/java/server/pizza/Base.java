package server.pizza;

import server.pizza.constants.ServingQuantity;

public record Base(String baseIngredient, ServingQuantity baseServingQuantity) {
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
