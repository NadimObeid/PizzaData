package server.Service;

import org.springframework.http.ResponseEntity;
import server.Repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.parser.Ingredient;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class IngredientServiceImpl implements IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public Ingredient saveIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }


    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredient getByName(String Name) throws NoSuchElementException {
        try{
        List<Ingredient> ingredients = ingredientRepository.findAll().stream().filter(n-> Name.equals(n.getName())).toList();
        return ingredients.get(0);
        }
        catch (IndexOutOfBoundsException i){
            throw new NoSuchElementException();
        }
    }

    @Override
    public Optional<Ingredient> getMostExpensiveIngredient() {
        return getAllIngredients().stream()
                .max(Comparator.comparing(Ingredient::getPrice));
    }

    @Override
    public Optional<Ingredient> getClosestExpiryDate() {
        return getAllIngredients().stream()
                .filter(n -> n.getExpDate().isAfter(LocalDate.now()))
                .min(Comparator.comparing(Ingredient::getExpDate));
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
    public List<List<Ingredient>> getLeast3Weights() {
        return getAllIngredients()
                .stream()
                .collect(Collectors.toMap(Ingredient::getWeight, Ingredient::getName, (oldValue, newValue) -> oldValue + ";" + newValue, TreeMap::new))
                .values().stream()
                .map(n->n.split(";"))
                .map(n-> Arrays.stream(n).map(this::getByName).toList()).toList().subList(0,3);
    }

    @Override
    public void consumeIngredient(String Name, Double Amount) {
        Ingredient ingredient = getByName(Name);
        ingredient.setWeight(ingredient.getWeight()-Amount>0?ingredient.getWeight()-Amount:0);
    }

    @Override
    public void remove(String name) {
        ingredientRepository.delete(getByName(name));
    }

    @Override
    public ResponseEntity<String> saveChanges(List<Ingredient> ingredients) {
        return null;
    }


}
