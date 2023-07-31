package Service;

import Repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parser.Ingredient;

@Service
public class IngredientServiceImpl implements IngredientService{
    @Autowired
    private PizzaRepository pizzaRepository;
    @Override
    public Ingredient saveIngredient(Ingredient ingredient) {
        return pizzaRepository.save(ingredient);
    }
}
