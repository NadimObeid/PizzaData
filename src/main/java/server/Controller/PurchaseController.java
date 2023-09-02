package server.Controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.Purchase.Purchase;

import server.Repository.PurchaseRepository;
import server.Service.PurchaseService;
import server.parser.IngredientUnavialableException;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @PostMapping("/place")
    public String placePurchase(@RequestBody @JsonProperty Purchase purchaseRequest){
        try{
            purchaseService.processPurchaseRequest(purchaseRequest);
        }
        catch (IngredientUnavialableException e){
            return "Purchase unavailable";
        }
        return "Order placed";
    }
}
