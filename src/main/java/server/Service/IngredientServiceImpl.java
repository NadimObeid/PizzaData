package server.Service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import server.Repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.parser.Ingredient;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class IngredientServiceImpl implements IngredientService {
    @Autowired
    private PizzaRepository pizzaRepository;

    @Override
    public Ingredient saveIngredient(Ingredient ingredient) {
        if(getByName(ingredient.getName())==null) return pizzaRepository.save(ingredient);
        return null;
    }


    @Override
    public ArrayList<Ingredient> getAllIngredients() {
        return (ArrayList<Ingredient>) pizzaRepository.findAll();
    }

    @Override
    public Ingredient getByName(String Name) {
        try{
        List<Ingredient> items = pizzaRepository.findAll().stream().filter(n-> Objects.equals(n.getName(), Name)).toList();
        return items.get(0);
        }
        catch (IndexOutOfBoundsException i){
            return null;
        }
    }

    @Override
    public String getMostExpensiveIngredient() {
        ArrayList<String> names = new ArrayList<>(getAllIngredients().stream().collect(Collectors.toMap(
                Ingredient::getPrice, Ingredient::getName, (oldValue, newValue) -> oldValue + ";" + newValue, TreeMap::new
        )).values());
        return names.get(names.size() - 1);
    }

    @Override
    public String getClosestExpiryDate() {
        ArrayList<String> names = new ArrayList<>(getAllIngredients().stream().filter(n -> n.getExpDate().isAfter(LocalDate.now())).collect(Collectors.toMap(
                Ingredient::getExpDate, Ingredient::getName, (oldValue, newValue) -> oldValue, TreeMap::new
        )).values());
        return names.get(0);
    }

    @Override
    public Double getTotalPrice() {
        return getAllIngredients().stream().map(Ingredient::getPrice).reduce(0.0, Double::sum);
    }

    @Override
    public Double getPriceOfExpiredObjects() {
        return  getAllIngredients().stream().filter(n -> !n.getExpDate().isAfter(LocalDate.now())).map(Ingredient::getPrice).reduce(0.0, Double::sum);
    }

    @Override
    public List<String> getLeast3Weights() {
        return new ArrayList<>(getAllIngredients().stream().collect(Collectors.toMap(
                Ingredient::getWeight, Ingredient::getName, (oldValue, newValue) -> oldValue + ";" + newValue, TreeMap::new
        )).values()).subList(0, 3);
    }


}
