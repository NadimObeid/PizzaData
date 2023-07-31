package server.Service;

import server.Repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.parser.Ingredient;

@Service
public class IngredientServiceImpl implements IngredientService{
    @Autowired
    private PizzaRepository pizzaRepository;
    @Override
    public Ingredient saveIngredient(Ingredient ingredient) {
        return pizzaRepository.save(ingredient);
    }
}
