package server.Controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ObjectNode;
import server.Service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.parser.Ingredient;


import java.util.List;

@CrossOrigin
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
    public Ingredient get(@RequestBody @JsonProperty("Name") String name) {
        return ingredientService.getByName(name);
    }

    @GetMapping("/getAll")
    public List<Ingredient> getAll() {
        return ingredientService.getAllIngredients();
    }

    @GetMapping("/getExpensiveItem")
    public Ingredient getMostExpensiveIngredient() {
        return ingredientService.getMostExpensiveIngredient().orElseThrow();
    }

    @GetMapping("/getClosestExpDate")
    public Ingredient getClosestExpiryDate() {
        return ingredientService.getClosestExpiryDate().orElseThrow();
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
    public List<List<Ingredient>> getLeast3Weights() {
        return ingredientService.getLeast3Weights();
    }
    @PatchMapping("/consume")
    public void consumeIngredient(@RequestBody @JsonProperty ObjectNode jsonNodes){
        ingredientService.consumeIngredient(jsonNodes.get("Name").asText(), jsonNodes.get("Amount").asDouble());
    }
    @DeleteMapping("/remove")
    public void remove(@RequestBody @JsonProperty String name){
        ingredientService.remove(name);
    }

}
