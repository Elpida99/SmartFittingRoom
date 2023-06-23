package hua.it21735.thesis.persistence.dao;

import hua.it21735.thesis.persistence.model.Garment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GarmentDao extends JpaRepository<Garment, Long> {

    List<Garment> findByName(String name);

    Optional<Garment> findByBarcode(Long barcode);

    List<Garment> findBySkuNumber(String skuNumber);

    @Query("SELECT g from Garment g where g.category.categoryCode = :categoryGiven " +
            "or g.category.categoryName = :categoryGiven")
    List<Garment> findByCategory(String categoryGiven);

    List<Garment> findByCode(String code);

    @Query("SELECT DISTINCT g.code from Garment g")
    List<String> findGarmentCodes();

    @Query("SELECT DISTINCT g.skuNumber from Garment g")
    List<String> findSkuNumbers();
}
