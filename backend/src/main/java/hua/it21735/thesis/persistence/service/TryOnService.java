package hua.it21735.thesis.persistence.service;

import hua.it21735.thesis.persistence.dao.TryOnDao;
import hua.it21735.thesis.persistence.model.Customer;
import hua.it21735.thesis.persistence.model.Garment;
import hua.it21735.thesis.persistence.model.TryOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TryOnService {

    @Autowired
    GarmentService garmentService;
    @Autowired
    StoreService storeService;
    @Autowired
    CustomerService customerService;
    @Autowired
    private TryOnDao tryOnDao;

    public TryOn getById(Long id) {
        Optional<TryOn> found = tryOnDao.findById(id);
        return found.orElse(new TryOn());
    }

    public List<TryOn> findAll() {
        return tryOnDao.findAll();
    }

    public Long save(TryOn tryOn) {
        if (tryOn != null) {
            TryOn saved = tryOnDao.save(tryOn);
            return saved.getId();
        }
        return null;
    }

    @Transactional
    public TryOn customerTryOn(Long barcode, Long customerId, String storeId) {
        TryOn tryOn = new TryOn();
        Garment garment = garmentService.findByBarcode(barcode);
        if (garment != null) {
            tryOn.getGarments().add(garment);
            tryOn.setDateTime(LocalDateTime.now());
            tryOn.setStore(storeService.getByName(storeId));
            tryOn.setCustomer(customerService.getById(customerId));
            System.out.println("tryOn = " + tryOn.toString());
            save(tryOn);
        } else {
            throw new NullPointerException("Garment not found");
        }
        return tryOn;
    }

    public TryOn guestTryOn(Long barcode, String storeId) {
        TryOn tryOn = new TryOn();
        Garment garment = garmentService.findByBarcode(barcode);
        if (garment != null) {
            tryOn.getGarments().add(garment);
            tryOn.setDateTime(LocalDateTime.now());
            tryOn.setStore(storeService.getByName(storeId));
            tryOn.setCustomer(null);
            save(tryOn);
        } else {
            throw new NullPointerException("Garment not found");
        }
        return tryOn;
    }

    public List<TryOn> getTryOnsByCustomer(Long customerId) {
        Customer customer = customerService.getById(customerId);
        System.out.println("customer = " + customer.toString());
        List<TryOn> byCustomer = tryOnDao.findByCustomer(customer);
        System.out.println("byCustomer = " + byCustomer.toString());
        return byCustomer;
    }


}
