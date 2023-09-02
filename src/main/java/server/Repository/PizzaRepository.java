package server.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.parser.Ingredient;
import server.pizza.Pizza;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
}
