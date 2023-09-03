package server.Purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import server.Service.IngredientService;
import server.parser.Ingredient;
import server.parser.IngredientUnavialableException;
import server.pizza.Pizza;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Component
public class PurchaseProcessor {
    @Autowired
    private IngredientService ingredientService;


    public boolean isPurchaseAvailable(Purchase purchase) throws IngredientUnavialableException {
        List<Ingredient> availableIngredients = ingredientService.getAllIngredients();
        List<Pizza> pizzaList = purchase.getPizzaList();
        Map<String,Double> requestedIngredients = getIngredientMapWithMultiplier(pizzaList);
        return areAllIngredientsAvailable(availableIngredients,requestedIngredients);
    }

    private boolean areAllIngredientsAvailable(List<Ingredient> availableIngredients, Map<String, Double> requestedIngredients) throws IngredientUnavialableException {
        for(String requestedIngredientName: requestedIngredients.keySet()){
            Ingredient availableIngredient = getRequestedIngredientFromList(availableIngredients,requestedIngredientName);
            final double requestedMultiplier = requestedIngredients.get(requestedIngredientName);
            if (requestedMultiplier * availableIngredient.getServingQuantity() > availableIngredient.getWeight())
                return false;
        }
        return true;
    }
    private Ingredient getRequestedIngredientFromList(List<Ingredient> availableIngredients, String ingredientName) throws IngredientUnavialableException {
        return availableIngredients.stream().filter(ingredient -> ingredientName.equals(ingredient.getName())).findAny().orElseThrow(IngredientUnavialableException::new);
    }

    private Map<String,Double> getIngredientMapWithMultiplier(List<Pizza> pizzaList) {
        return pizzaList.stream().map(Pizza::getTotalIngredientsWithQuantities).flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        Double::sum
                ));
    }

    public void consumePurchaseIngredientsIfAvailable(Purchase purchase) throws IngredientUnavialableException{
        List<Ingredient> availableIngredients = ingredientService.getAllIngredients();
        if(isPurchaseAvailable(purchase)){
            final List<Pizza> pizzaList = purchase.getPizzaList();
            getIngredientMapWithMultiplier(pizzaList)
                    .forEach((ingredientName, multiplier)-> {
                        consumePurchaseIngredientsFromDb(availableIngredients, ingredientName, multiplier);
                    });
        }
    }

    private void consumePurchaseIngredientsFromDb(List<Ingredient> availableIngredients, String ingredientName, Double multiplier) {
        try {
            final double totalServingQuantity = multiplier * getRequestedIngredientFromList(availableIngredients, ingredientName).getServingQuantity();
            ingredientService.consumeIngredient(ingredientName, totalServingQuantity);
        } catch (IngredientUnavialableException e) {
            // Do Nothing
        }
    }

    public double calculatePurchasePrice(Purchase purchase) throws IngredientUnavialableException {
        double totalPurchasePrice = purchase.getPizzaList().stream().mapToDouble(pizza->pizza.getSize().getPrice()).sum();
        List<Ingredient> availableIngredients = ingredientService.getAllIngredients();
        Map<String,Double> ingredientMapWithMultiplier =getIngredientMapWithMultiplier(purchase.getPizzaList());
        for(String requestedIngredientName:ingredientMapWithMultiplier.keySet()){
            Ingredient requestedIngredient = getRequestedIngredientFromList(availableIngredients,requestedIngredientName);
            totalPurchasePrice += requestedIngredient.getServingQuantity()*ingredientMapWithMultiplier.get(requestedIngredientName)*requestedIngredient.getPrice();
        }
        return totalPurchasePrice;
    }
}
