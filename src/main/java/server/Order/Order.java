package server.Order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import server.pizza.Pizza;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Double totalPrice;
    private List<Pizza> pizzaList;
    private String customerPhoneNumber;
    private LocalDate orderDate;


    protected Order(String customerPhoneNumber, List<Pizza> pizzaList){
        this.customerPhoneNumber = customerPhoneNumber;
        this.orderDate = LocalDate.now();
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
