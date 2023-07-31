package Controller;

import Service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import parser.Ingredient;

@RestController
@RequestMapping("/Ingredient")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;
    @PostMapping("/add")
    public String add(@RequestBody Ingredient ingredient){
        ingredientService.saveIngredient(ingredient);
        return "New Ingredient Added!";
    }
}
