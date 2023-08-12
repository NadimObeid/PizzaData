package server.Service;


import server.parser.Ingredient;

import java.util.List;
import java.util.Optional;


public interface IngredientService {
    public Ingredient saveIngredient(Ingredient ingredient);
    public List<Ingredient> getAllIngredients();
    public Ingredient getByName(String string);
    public Optional<Ingredient> getMostExpensiveIngredient();
    public Optional<Ingredient> getClosestExpiryDate();
    public Double getTotalPrice();
    public Double getPriceOfExpiredObjects();
    public List<List<Ingredient>> getLeast3Weights();
    public void consumeIngredient(String Name, Double Amount);

}
