package server.Controller;

import server.Service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.parser.Ingredient;

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
    @GetMapping("/hello")
    public String hello(){
        return "This is working";
    }
}
