package server.Controller;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.pizza.*;
import server.pizza.constants.PizzaSize;
import java.util.List;

@RestController
@RequestMapping("/pizzas")
public class PizzaController {

    private final PizzaBuilder pizzaBuilder;

    @Autowired
    public PizzaController(PizzaBuilder pizzaBuilder) {
        this.pizzaBuilder = pizzaBuilder;
    }

    /*@PostMapping("/build")
    public Pizza buildPizza(@RequestBody @JsonProperty ObjectNode jsonNodes) {
        Pizza pizza = pizzaBuilder.chooseSize(PizzaSize.valueOf(jsonNodes.get("Size").asText()))
                .withBase(new Base(jsonNodes.get("Base")))
                .withToppings(toppings)
                .withDrizzle(drizzle)
                .build();
        return pizza;
    }*/
}
