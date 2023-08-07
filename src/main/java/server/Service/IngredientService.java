package server.Service;


import server.parser.Ingredient;

import java.util.List;


public interface IngredientService {
    public Ingredient saveIngredient(Ingredient ingredient);
    public List<Ingredient> getAllIngredients();
    public Ingredient getByName(String string);
    public String getMostExpensiveIngredient();
    public String getClosestExpiryDate();
    public Double getTotalPrice();
    public Double getPriceOfExpiredObjects();
    public List<List<Ingredient>> getLeast3Weights();
    public void consumeIngredient(String Name, Double Amount);

}
