package server.Controller;

import server.Service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.parser.Ingredient;


import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;

    @PostMapping("/add")
    public String add(@RequestBody Ingredient ingredient) {
        ingredientService.saveIngredient(ingredient);
        return "New Ingredient Added!";
    }

    @GetMapping("/get")
    public Ingredient get(@RequestBody String name) {
        return ingredientService.getByName(name);
    }

    @GetMapping("/getAll")
    public List<Ingredient> getAll() {
        return ingredientService.getAllIngredients();
    }

    @GetMapping("/getExpensiveItem")
    public Ingredient getMostExpensiveIngredient() {
        return get(ingredientService.getMostExpensiveIngredient());
    }

    @GetMapping("/getClosestExpDate")
    public Ingredient getClosestExpiryDate() {
        return get(ingredientService.getClosestExpiryDate());
    }

    @GetMapping("/getTotalPrice")
    public Double getTotalPrice() {
        return ingredientService.getTotalPrice();
    }

    @GetMapping("/getPriceOfExpiredObjects")
    public Double getPriceOfExpiredObjects() {
        return ingredientService.getPriceOfExpiredObjects();
    }

    @GetMapping("/getLeast3Weights")
    public List<String> getLeast3Weights() {
        return ingredientService.getLeast3Weights();
    }

}
