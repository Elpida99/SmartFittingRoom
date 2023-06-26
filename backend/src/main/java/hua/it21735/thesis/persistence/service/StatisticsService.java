package hua.it21735.thesis.persistence.service;

import hua.it21735.thesis.persistence.model.Garment;
import hua.it21735.thesis.persistence.model.PointOfSale;
import hua.it21735.thesis.persistence.model.PurchaseDetails;
import hua.it21735.thesis.persistence.model.TryOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    @Autowired
    private GarmentService garmentService;
    @Autowired
    private TryOnService tryOnService;
    @Autowired
    private PurchaseService purchaseService;

    @Transactional
    public Map<String, Long> getPopularGarments() {
        List<String> garmentCodes = garmentService.findGarmentCodes();
        List<TryOn> allTryOns = tryOnService.findAll();
        Map<String, Long> map = new HashMap<>();

        for (String code : garmentCodes) {

            long tryOns =
                    allTryOns.stream().filter(tryOn -> tryOn.getGarments()
                                    .stream()
                                    .map(Garment::getCode)
                                    .collect(Collectors.toList())
                                    .contains(code))
                            .count();

            String name = "";
            List<Garment> byCode = garmentService.findByCode(code);
            if (!byCode.isEmpty())
                name = byCode.get(0).getName();
            map.put(code + "_NAME_" + name, tryOns);
        }


        return map;

    }

    @Transactional
    public Map<String, Long> getPopularGarmentsSKU() {
        List<String> garmentCodes = garmentService.findSkuNumbers();
        List<TryOn> allTryOns = tryOnService.findAll();
        Map<String, Long> map = new HashMap<>();

        for (String code : garmentCodes) {

            long tryOns =
                    allTryOns.stream().filter(tryOn -> tryOn.getGarments()
                                    .stream()
                                    .map(Garment::getSkuNumber)
                                    .collect(Collectors.toList())
                                    .contains(code))
                            .count();

            String name = "";
            List<Garment> byCode = garmentService.findBySku(code);
            if (!byCode.isEmpty())
                name = byCode.get(0).getName();
            map.put(code + "_NAME_" + name, tryOns);
        }


        return map;

    }

    public Map<String,Integer> getGarmentSales() {
        List<PointOfSale> sales = purchaseService.findAll();
        Map<String,Integer> codes = new HashMap<>();
        Map<String,Integer> avPrice = new HashMap<>();

        for(PointOfSale sale : sales) {

            List<String> collect = sale.getDetails().stream().map(PurchaseDetails::getProduct).map(Garment::getCode).collect(Collectors.toList());
            for(String c : collect) {
                if(codes.containsKey(c)) {
                    codes.put(c, codes.get(c) + 1);
                }else {
                    codes.put(c, 1);
                }
            }
        }
        Map<String,Integer> finalcodes = new HashMap<>();
        for (Map.Entry<String, Integer> entry : codes.entrySet()) {
            List<Garment> byCode = garmentService.findByCode(entry.getKey());
            if (!byCode.isEmpty()) {
                String name = byCode.get(0).getName();
                finalcodes.put(entry.getKey() + "_NAME_" + name, entry.getValue());
            }
        }



        return finalcodes;

    }

//    public void getSales

}