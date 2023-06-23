package hua.it21735.thesis.persistence.service;

import hua.it21735.thesis.persistence.dao.PointOfSaleDao;
import hua.it21735.thesis.persistence.model.*;
import hua.it21735.thesis.rest.jpo.PurchaseJpo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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

    public Long save(PointOfSale pos) {
        if (pos != null) {
            PointOfSale saved = pointOfSaleDao.save(pos);
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

//    public PointOfSale toPointOfSaleJpo(PurchaseJpo jpo) {
//        PointOfSale pos = new PointOfSale();
//        pos.setStore(storeService.getByName(jpo.getStoreName()));
//        pos.setCustomer(customerService.getByEmail(jpo.getCustomerEmail()));
//        pos.setStatus(jpo.getStatus());
//        pos.setDate(jpo.getDate());
//
//        List<PurchaseDetails> details = new ArrayList<>();
//        for(Long barcode : jpo.getBarcodes()) {
//            PurchaseDetails d = new PurchaseDetails();
//            d.setProduct(garmentService.findByBarcode(barcode));
//            d.setPrice();
//        }
//        pos.setDetails(details);
//        return pos;
//    }

    @Transactional
    public PurchaseJpo toPurchaseJpo(PointOfSale pos) {
        PurchaseJpo jpo = new PurchaseJpo();
        jpo.setStatus(pos.getStatus());
        jpo.setDate(pos.getDate());
        jpo.setStoreName(pos.getStore().getName());
        jpo.setCustomerEmail(pos.getCustomer().getEmail());
        List<PurchaseDetails> details = pos.getDetails();
        for(PurchaseDetails detail : details) {
            jpo.getBarcodes().put(detail.getProduct().getBarcode(), detail.getPrice());
        }

        return jpo;
    }
}
