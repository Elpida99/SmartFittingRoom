package hua.it21735.thesis.persistence.service;

import hua.it21735.thesis.persistence.dao.StoreDao;
import hua.it21735.thesis.persistence.dao.StoreInventoryDao;
import hua.it21735.thesis.persistence.model.Store;
import hua.it21735.thesis.persistence.model.StoreInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    private StoreDao storeDao;
    @Autowired
    private StoreInventoryDao storeInventoryDao;

    public List<Store> findAll() {
        return storeDao.findAll();
    }

    public Store getByName(String name) {
        if (name != null) {
            Optional<Store> store = storeDao.findByName(name);
            return store.orElse(null);
        }
        return null;
    }

    public List<StoreInventory> getInventory(String storeId) {
        return storeInventoryDao.findByStore(getByName(storeId));
    }

    public StoreInventory getByBarcode(Long barcode) {
        return storeInventoryDao.findByGarment_Barcode(barcode).orElse(null);
    }

    public void deleteInv(StoreInventory storeInventory) {
        if (storeInventory.getId() != null)
            storeInventoryDao.deleteById(storeInventory.getId());
    }

}
