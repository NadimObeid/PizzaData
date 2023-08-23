package server.Service;


import org.springframework.http.ResponseEntity;
import server.parser.Ingredient;

import java.util.List;
import java.util.Optional;


public interface IngredientService {
    Ingredient saveIngredient(Ingredient ingredient);
    List<Ingredient> getAllIngredients();
    Ingredient getByName(String string);
    Optional<Ingredient> getMostExpensiveIngredient();
    Optional<Ingredient> getClosestExpiryDate();
    Double getTotalPrice();
    Double getPriceOfExpiredObjects();
    List<List<Ingredient>> getLeast3Weights();
    void consumeIngredient(String Name, Double Amount);
    void remove(String name);
    ResponseEntity<String> saveChanges(List<Ingredient> ingredients);


}
