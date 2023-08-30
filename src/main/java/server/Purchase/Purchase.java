package server.Purchase;

import jakarta.persistence.*;
import server.pizza.Pizza;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double totalPrice;
    @OneToMany
    @JoinColumn(name="purchase_id")
    private List<Pizza> pizzaList;
    private String customerPhoneNumber;
    private LocalDate purchaseDate;


    protected Purchase(String customerPhoneNumber, List<Pizza> pizzaList){
        this.customerPhoneNumber = customerPhoneNumber;
        this.purchaseDate = LocalDate.now();
        this.pizzaList= pizzaList;
        setTotalPrice();

    }

    private void setTotalPrice(){
        totalPrice = pizzaList.stream().map(Pizza::getPrice).reduce(0.00,Double::sum);
    }
    public Double getTotalPrice() {
        return totalPrice;
    }

    public List<Pizza> getPizzaList() {
        return pizzaList;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }


}
