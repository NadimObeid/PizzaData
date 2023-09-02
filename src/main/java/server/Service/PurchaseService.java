package server.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.Purchase.Purchase;
import server.Purchase.PurchaseProcessor;
import server.Repository.PurchaseRepository;
import server.parser.IngredientUnavialableException;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private PurchaseProcessor purchaseProcessor;

    public void processPurchaseRequest(Purchase purchaseRequested) throws IngredientUnavialableException {
        purchaseProcessor.consumePurchaseIngredientsIfAvailable(purchaseRequested);
       // storePurchaseInTable(purchaseRequested);
    }

    private void storePurchaseInTable(Purchase purchaseRequested){
        purchaseRepository.save(purchaseRequested);
    }
    private Double getPurchasePrice(Purchase purchaseRequested) throws IngredientUnavialableException {
       return purchaseProcessor.calculatePurchasePrice(purchaseRequested);
    }
}
