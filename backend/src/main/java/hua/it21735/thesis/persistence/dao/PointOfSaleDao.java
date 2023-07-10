package hua.it21735.thesis.persistence.dao;

import hua.it21735.thesis.persistence.model.Customer;
import hua.it21735.thesis.persistence.model.PointOfSale;
import hua.it21735.thesis.persistence.model.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointOfSaleDao extends JpaRepository<PointOfSale, Long> {

    List<PointOfSale> findByStatus(StatusEnum statusEnum);

    List<PointOfSale> findByStore(String storeName);

    List<PointOfSale> findbyCustomer(Customer customer);

    boolean existsByDetails_Product_Barcode(Long barcode);
}
