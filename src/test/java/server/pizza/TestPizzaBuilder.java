package server.pizza;

import org.junit.jupiter.api.Test;
import server.Order.Order;
import server.Order.OrderBuilder;
import server.pizza.constants.PizzaSize;
import server.pizza.constants.ServingQuantity;
import server.pizza.constants.ServingStyle;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestPizzaBuilder {
    @Test
    public void testBuildingPizza() {
        Pizza pizza = Pizza.getBuilder(PizzaSize.L)
                .build();

        assertNull(pizza.getBase());
        assertNull(pizza.getToppings());
    }

    @Test
    public void testBuildingPizzaWithBaseAndToppings() {
        Pizza pizza = Pizza.getBuilder(PizzaSize.S)
                .withBase(new Base("Tomato", ServingQuantity.MEDIUM))
                .withToppings(List.of(new Topping("Cheese", 2.0), new Topping("Olives", 3.0)))
                .withDrizzle(new Drizzle("Ranch", ServingStyle.SWIRL, ServingQuantity.EXTRA))
                .build();
        assertEquals("Tomato", pizza.getBase().baseIngredient());
        assertEquals("Cheese", pizza.getToppings().get(0).toppingIngredient());
    }
    @Test
    public void testPrice(){
        Order newOrder = new OrderBuilder().generateNewPizza(PizzaSize.S).placeOrder("71357407");
        assertEquals(8.99,newOrder.getTotalPrice());
    }
}
