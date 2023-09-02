package server.Purchase;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import server.pizza.Pizza;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="Purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection
    @CollectionTable(name = "pizzas", joinColumns = @JoinColumn(name = "purchase_id"))
    private List<Pizza> pizzaList;
    private String customerPhoneNumber;
    private LocalDate purchaseDate;


    public Purchase(@JsonProperty("pizzas") List<Pizza> pizzaList, @JsonProperty("phoneNumber") String customerPhoneNumber){
        this.pizzaList= pizzaList;
        this.customerPhoneNumber = customerPhoneNumber;
        this.purchaseDate = LocalDate.now();
    }

    public List<Pizza> getPizzaList() {
        return pizzaList;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }


}
