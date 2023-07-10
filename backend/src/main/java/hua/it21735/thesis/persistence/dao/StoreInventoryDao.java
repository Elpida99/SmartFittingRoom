package hua.it21735.thesis.persistence.dao;

import hua.it21735.thesis.persistence.model.Store;
import hua.it21735.thesis.persistence.model.StoreInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreInventoryDao extends JpaRepository<StoreInventory, Long> {

    List<StoreInventory> findByStore(Store store);

    Optional<StoreInventory> findByGarment_Barcode(Long barcode);

}
