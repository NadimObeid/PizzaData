package server.Repository;



import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.parser.Ingredient;

import java.util.List;


@Repository
public interface PizzaRepository extends JpaRepository<Ingredient, Integer> {

}
