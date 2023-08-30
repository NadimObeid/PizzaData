package server.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import server.parser.Ingredient;

import java.util.List;
import java.util.Optional;


@Service
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
