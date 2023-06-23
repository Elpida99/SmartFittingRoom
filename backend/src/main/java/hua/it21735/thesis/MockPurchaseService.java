package hua.it21735.thesis;

import hua.it21735.thesis.persistence.model.PointOfSale;
import hua.it21735.thesis.persistence.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MockPurchaseService {

    @Autowired
    PurchaseService purchaseService;

    public void setStatusOfPurchase() {
        List<PointOfSale> pointOfSales = purchaseService.findAll();
        for(PointOfSale sale : pointOfSales) {
            LocalDateTime date = sale.getDate();
            //TODO: change status
        }
    }

}
