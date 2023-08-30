package server.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.Purchase.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,Integer> {
}
