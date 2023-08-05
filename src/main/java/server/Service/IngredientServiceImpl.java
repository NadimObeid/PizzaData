package server.Service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import server.Repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.parser.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class IngredientServiceImpl implements IngredientService {
    @Autowired
    private PizzaRepository pizzaRepository;

    @Override
    public Ingredient saveIngredient(Ingredient ingredient) {
        return pizzaRepository.save(ingredient);
    }


    @Override
    public ArrayList<Ingredient> getAllIngredients() {
        return (ArrayList<Ingredient>) pizzaRepository.findAll();
    }

    @Override
    public Ingredient getByName(String Name) {
        return pizzaRepository.findAll().stream().filter(n-> Objects.equals(n.getName(), Name)).toList().get(0);
    }


}
