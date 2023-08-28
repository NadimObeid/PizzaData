package server.parser;

public class NotEnoughIngredientException extends Exception{
    @Override
    public String getMessage(){
        return String.format("There is not enough ingredients to place order");
    }
}
