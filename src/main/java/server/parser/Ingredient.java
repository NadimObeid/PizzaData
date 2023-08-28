package server.parser;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private IngredientType type;
    private double servingQuantity;
    private double price;
    private double weight;
    private String origin;
    private String canCauseAllergy;
    private String typeOfAllergy;
    private double temperature;
    private LocalDate expDate;

    public Ingredient(@JsonProperty("Name") String name,
                      @JsonProperty("Type") IngredientType type,
                      @JsonProperty double servingSize,
                      @JsonProperty("Price") double price,
                      @JsonProperty("Weight") double weight,
                      @JsonProperty("Origin") String origin,
                      @JsonProperty("CanCauseAllergy") String canCauseAllergy,
                      @JsonProperty("TypeOfAllergy") String typeOfAllergy,
                      @JsonProperty("Temperature") double temperature,
                      @JsonProperty("ExpDate") LocalDate expDate) {
        this.servingQuantity = servingSize;
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.weight = weight;
        this.origin = origin;
        this.canCauseAllergy = canCauseAllergy;
        this.typeOfAllergy = this.canCauseAllergy.equalsIgnoreCase("yes")?typeOfAllergy:"none";
        this.temperature = temperature;
        this.expDate = expDate;
    }

    public Ingredient() {
    }



    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public IngredientType getType() {
        return type;
    }
    public double getServingQuantity(){
        return servingQuantity;
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    public String getOrigin() {
        return origin;
    }

    public String getCanCauseAllergy() {
        return canCauseAllergy;
    }

    public String getTypeOfAllergy() {
        return typeOfAllergy;
    }

    public double getTemperature() {
        return temperature;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(IngredientType type) {
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setCanCauseAllergy(String canCauseAllergy) {
        this.canCauseAllergy = canCauseAllergy;
    }

    public void setTypeOfAllergy(String typeOfAllergy) {
        this.typeOfAllergy = typeOfAllergy;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setExpDate(String expDate) {
        this.expDate = LocalDate.parse(expDate);
    }
    public void consumeIngredient(Double quantity) throws NotEnoughIngredientException {
        if(getWeight()-quantity>=0){
            setWeight(getWeight()-quantity);
        }
        else{
            throw new NotEnoughIngredientException();
        }
    }
}
