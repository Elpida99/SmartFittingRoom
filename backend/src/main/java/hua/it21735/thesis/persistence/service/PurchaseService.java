package hua.it21735.thesis.persistence.service;

import hua.it21735.thesis.persistence.dao.PointOfSaleDao;
import hua.it21735.thesis.persistence.model.*;
import hua.it21735.thesis.rest.jpo.PurchaseJpo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseService {

    @Autowired
    private PointOfSaleDao pointOfSaleDao;
    @Autowired
    private GarmentService garmentService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private StoreService storeService;

    @Transactional
    public Long save(PointOfSale pos) {
        if (pos != null) {
            PointOfSale saved = pointOfSaleDao.save(pos);
            List<Garment> collect = saved.getDetails().stream().map(PurchaseDetails::getProduct).collect(Collectors.toList());
            for(Garment g : collect) {
                StoreInventory storeInventory = storeService.getByBarcode(g.getBarcode());
                if(storeInventory != null) {
                    storeService.deleteInv(storeInventory);
                }
            }


            return saved.getId();
        }
        return null;
    }

    public Long save(PurchaseJpo purchaseJpo) {
        if (purchaseJpo != null) {
            PointOfSale pos = new PointOfSale();

            pos.setStatus(purchaseJpo.getStatus());
            pos.setCustomer(customerService.getByEmail(purchaseJpo.getCustomerEmail()));
            pos.setStore(storeService.getByName(purchaseJpo.getStoreName()));

            List<PurchaseDetails> purchaseDetailsList = new ArrayList<>();
//            for (Long barcode : purchaseJpo.getBarcodes()) {
            for (Long barcode : purchaseJpo.getBarcodes().keySet()) {
                Garment garment = garmentService.findByBarcode(barcode);
                if (garment != null) {
                    PurchaseDetails purchaseDetails = new PurchaseDetails();
                    purchaseDetails.setProduct(garment);
                    purchaseDetails.setPrice();
                    purchaseDetailsList.add(purchaseDetails);

                    StoreInventory storeInventory = storeService.getByBarcode(barcode);
                    if(storeInventory != null) {
                        storeService.deleteInv(storeInventory);
                    }
                }
            }
            pos.getDetails().addAll(purchaseDetailsList);

            return save(pos);
        }
        return null;
    }

    public List<PointOfSale> findAll() {
        return pointOfSaleDao.findAll();
    }

    public List<PointOfSale> findByStatus(StatusEnum statusEnum) {
        if (statusEnum != null) {
            return pointOfSaleDao.findByStatus(statusEnum);
        }
        return null;
    }

    public List<PointOfSale> findByStore(String storeName) {
        if (storeName != null) {
            return pointOfSaleDao.findByStore(storeName);
        }
        return null;
    }

    public float getAveragePrice(String code) {
        List<PointOfSale> all = findAll();
        List<Float> prices = new ArrayList<>();
        float average = 0;
        for (PointOfSale pos : all) {
            List<PurchaseDetails> details = pos.getDetails();
            for (PurchaseDetails d : details) {
                if (d.getProduct().getCode().equals(code)) {
                    prices.add(d.getPrice());
                }
            }
        }
        for (float price : prices) {
            average += price;
        }

        return average / prices.size();
    }

    @Transactional
    public List<PointOfSale> findbyCustomer(String email) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>.email = " + email);
        if (email != null) {
            Customer customer = customerService.getByEmail(email);
            System.out.println("customer.toString() = " + customer.toString());
            return pointOfSaleDao.findbyCustomer(customer);
        }
        return null;
    }

    @Transactional
    public PurchaseJpo toPurchaseJpo(PointOfSale pos) {
        PurchaseJpo jpo = new PurchaseJpo();
        jpo.setStatus(pos.getStatus());
        jpo.setDate(pos.getDate());
        jpo.setStoreName(pos.getStore().getName());
        jpo.setCustomerEmail(pos.getCustomer().getEmail());
        List<PurchaseDetails> details = pos.getDetails();
        for (PurchaseDetails detail : details) {
            jpo.getBarcodes().put(detail.getProduct().getBarcode(), detail.getPrice());
        }

        return jpo;
    }

    public List<PointOfSale> getPurchasesByCustomer(Long id) {

        Customer customer = customerService.getById(id);
        System.out.println("customer = " + customer.toString());
        List<PointOfSale> byCustomer = pointOfSaleDao.findbyCustomer(customer);
        System.out.println("byCustomer = " + byCustomer.toString());
        return byCustomer;
    }

    public Integer getPurchasesRevenueByCustomer(Long id) {

        int revenue = 0;
        List<PointOfSale> byCustomer = getPurchasesByCustomer(id);
        for (PointOfSale pos : byCustomer) {
            revenue += pos.getDetails().stream().map(PurchaseDetails::getPrice).reduce(0.0F, Float::sum);
        }

        return revenue;
    }

    public boolean existsByDetails_Product_Barcode(final Long barcode) {
        return pointOfSaleDao.existsByDetails_Product_Barcode(barcode);
    }
}
