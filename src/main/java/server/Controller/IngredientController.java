package server.Controller;

import server.Service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.parser.Ingredient;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;

    @PostMapping("/add")
    public String add(@RequestBody Ingredient ingredient){
        ingredientService.saveIngredient(ingredient);
        return "New Ingredient Added!";
    }

    @GetMapping("/get")
    public Ingredient get(@RequestBody String name){
            return ingredientService.getByName(name);
    }
    @GetMapping("/getAll")
    public List<Ingredient> getAll(){
        return ingredientService.getAllIngredients();
    }
    @GetMapping("/getExpensiveItem")
    public String getMostExpensiveIngredient() {
        ArrayList<String> names = new ArrayList<>(getAll().stream().collect(Collectors.toMap(
                Ingredient::getPrice, Ingredient::getName, (oldValue, newValue) -> oldValue + ";" + newValue, TreeMap::new
        )).values());
        return names.get(names.size() - 1);
    }
    @GetMapping("/getClosestExpDate")
    public String getClosestExpiryDate() {
        ArrayList<String> names = new ArrayList<>(getAll().stream().filter(n -> n.getExpDate().isAfter(LocalDate.now())).collect(Collectors.toMap(
                Ingredient::getExpDate, Ingredient::getName, (oldValue, newValue) -> oldValue, TreeMap::new
        )).values());
        return names.get(0);
    }
    @GetMapping("/getTotalPrice")
    public Double getTotalPrice() {
        return getAll().stream().map(Ingredient::getPrice).reduce(0.0, Double::sum);
    }
    @GetMapping("/getPriceOfExpiredObjects")
    public Double getPriceOfExpiredObjects() {
        return getAll().stream().filter(n -> !n.getExpDate().isAfter(LocalDate.now())).map(Ingredient::getPrice).reduce(0.0, Double::sum);
    }

    @GetMapping("/getLeast3Weights")
    public List<String> getLeast3Weights() {
        return new ArrayList<>(getAll().stream().collect(Collectors.toMap(
                Ingredient::getWeight, Ingredient::getName, (oldValue, newValue) -> oldValue + ";" + newValue, TreeMap::new
        )).values()).subList(0, 3);
    }

}
