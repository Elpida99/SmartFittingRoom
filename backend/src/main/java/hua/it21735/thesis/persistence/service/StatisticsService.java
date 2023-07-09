package hua.it21735.thesis.persistence.service;

import hua.it21735.thesis.persistence.dao.StoreInventoryDao;
import hua.it21735.thesis.persistence.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
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
    @Autowired
    private CustomerService customerService;
    @Autowired
    private StoreInventoryDao storeInventoryDao;

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

    public Map<String, Integer> getGarmentSales() {
        List<PointOfSale> sales = purchaseService.findAll();
        Map<String, Integer> codes = new HashMap<>();
        Map<String, Integer> avPrice = new HashMap<>();

        for (PointOfSale sale : sales) {

            List<String> collect = sale.getDetails().stream().map(PurchaseDetails::getProduct).map(Garment::getCode).collect(Collectors.toList());
            for (String c : collect) {
                if (codes.containsKey(c)) {
                    codes.put(c, codes.get(c) + 1);
                } else {
                    codes.put(c, 1);
                }
            }
        }
        Map<String, Integer> finalcodes = new HashMap<>();
        for (Map.Entry<String, Integer> entry : codes.entrySet()) {
            List<Garment> byCode = garmentService.findByCode(entry.getKey());
            if (!byCode.isEmpty()) {
                String name = byCode.get(0).getName();
                finalcodes.put(entry.getKey() + "_NAME_" + name, entry.getValue());
            }
        }


        return finalcodes;

    }

    public Map<String, Integer[]> getCustomerSales() {
        Map<String, Integer[]> result = new HashMap<>();

        List<Customer> customers = customerService.findAll();
        for (Customer c : customers) {
            int countOfTryons = tryOnService.getTryOnsByCustomer(c.getId()).size();
            int countOfPos = purchaseService.getPurchasesByCustomer(c.getId()).size();

            String key = c.getId() + "__" + c.getFirstName() + " " + c.getLastName() + "__" + c.getPhoneNumber();
            Integer[] counts = new Integer[2];
            counts[0] = countOfTryons;
            counts[1] = countOfPos;

            result.put(key, counts);
        }

        return result;
    }

    public Map<String, Integer> getInventory() {
        List<StoreInventory> storeInventory = storeInventoryDao.findAll();
        Map<String, Integer> result = new HashMap<>();

        for (StoreInventory si : storeInventory) {
            String skuNumber = si.getGarment().getSkuNumber();
            String storeName = si.getStore().getName();
            String key = storeName + "__" + skuNumber;

            if (result.containsKey(key)) {
                result.put(key, result.get(key) + 1);
            } else {
                result.put(key, 1);
            }
        }

        return result;
    }


    public Map<String, List<Integer>> getCustomerInfo() {
        List<Customer> all = customerService.findAll();
        List<String> collect = all.stream().map(Customer::getAddress).map(Address::getCity).distinct().collect(Collectors.toList());
        Map<String, List<Integer>> result = new HashMap<>();
        for (String city : collect) {
            List<Customer> customersCity =
                    all.stream().filter(customer -> customer.getAddress().getCity().equals(city)).collect(Collectors.toList());
            int countOfTryonsPerCity = 0;
            int countOfPosPerCity = 0;
            for (Customer c : customersCity) {
                countOfTryonsPerCity += tryOnService.getTryOnsByCustomer(c.getId()).size();
//                countOfPosPerCity += purchaseService.getPurchasesByCustomer(c.getId()).size();
                countOfPosPerCity += purchaseService.getPurchasesRevenueByCustomer(c.getId());
            }
            result.put(city, Arrays.asList(customersCity.size(), countOfTryonsPerCity, countOfPosPerCity));
        }

        return result;
    }
}
