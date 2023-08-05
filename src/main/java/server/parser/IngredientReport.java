package server.parser;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import server.Controller.IngredientController;
import server.Repository.PizzaRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Component
public class IngredientReport{

    private List<Ingredient> ingredientList;

    public IngredientReport(List<Ingredient> ingredients) {
        ingredientList = ingredients;
    }

    public IngredientReport() {
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }


    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public Ingredient get(int i) {
        return ingredientList.get(i);
    }

    public String getMostExpensiveIngredient() {
        ArrayList<String> names = new ArrayList<>(ingredientList.stream().collect(Collectors.toMap(
                n -> n.getPrice() / n.getWeight(), Ingredient::getName, (oldValue, newValue) -> oldValue + ";" + newValue, TreeMap::new
        )).values());
        return names.get(names.size() - 1);
    }

    public String getClosestExpiryDate() {
        ArrayList<String> names = new ArrayList<>(ingredientList.stream().filter(n -> n.getExpDate().isAfter(LocalDate.now())).collect(Collectors.toMap(
                Ingredient::getExpDate, Ingredient::getName, (oldValue, newValue) -> oldValue, TreeMap::new
        )).values());
        return names.get(0);
    }

    public Double getTotalPrice() {
        return ingredientList.stream().map(Ingredient::getPrice).reduce(0.0, Double::sum);
    }

    public Double getPriceOfExpiredObjects() {
        return ingredientList.stream().filter(n -> !n.getExpDate().isAfter(LocalDate.now())).map(Ingredient::getPrice).reduce(0.0, Double::sum);
    }

    public List<String> getLeast3Weights() {
        return new ArrayList<>(ingredientList.stream().collect(Collectors.toMap(
                Ingredient::getWeight, Ingredient::getName, (oldValue, newValue) -> oldValue + ";" + newValue, TreeMap::new
        )).values()).subList(0, 3);
    }
}
