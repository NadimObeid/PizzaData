package server.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jdbc.repository.query.Query;
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
    public List<String> getLeast3Weights();

}
