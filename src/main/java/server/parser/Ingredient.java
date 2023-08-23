package server.parser;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String Name;
    private String Type;
    private double Price;
    private double Weight;
    private String Origin;
    private String CanCauseAllergy;
    private String TypeOfAllergy;
    private double Temperature;
    private LocalDate ExpDate;

    public Ingredient(@JsonProperty("Name") String name,
                      @JsonProperty("Type") String type,
                      @JsonProperty("Price") double price,
                      @JsonProperty("Weight") double weight,
                      @JsonProperty("Origin") String origin,
                      @JsonProperty("CanCauseAllergy") String canCauseAllergy,
                      @JsonProperty("TypeOfAllergy") String typeOfAllergy,
                      @JsonProperty("Temperature") double temperature,
                      @JsonProperty("ExpDate") LocalDate expDate) {
        this.id = id;
        Name = name;
        Type = type;
        Price = price;
        Weight = weight;
        Origin = origin;
        CanCauseAllergy = canCauseAllergy;
        TypeOfAllergy = CanCauseAllergy.equalsIgnoreCase("yes")?typeOfAllergy:"none";
        Temperature = temperature;
        ExpDate = expDate;
    }

    public Ingredient() {
    }



    public Integer getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getType() {
        return Type;
    }

    public double getPrice() {
        return Price;
    }

    public double getWeight() {
        return Weight;
    }

    public String getOrigin() {
        return Origin;
    }

    public String getCanCauseAllergy() {
        return CanCauseAllergy;
    }

    public String getTypeOfAllergy() {
        return TypeOfAllergy;
    }

    public double getTemperature() {
        return Temperature;
    }

    public LocalDate getExpDate() {
        return ExpDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public void setWeight(double weight) {
        Weight = weight;
    }

    public void setOrigin(String origin) {
        Origin = origin;
    }

    public void setCanCauseAllergy(String canCauseAllergy) {
        CanCauseAllergy = canCauseAllergy;
    }

    public void setTypeOfAllergy(String typeOfAllergy) {
        TypeOfAllergy = typeOfAllergy;
    }

    public void setTemperature(double temperature) {
        Temperature = temperature;
    }

    public void setExpDate(String expDate) {
        ExpDate = LocalDate.parse(expDate);
    }
}
