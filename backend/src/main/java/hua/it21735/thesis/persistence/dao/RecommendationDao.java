package hua.it21735.thesis.persistence.dao;

import hua.it21735.thesis.persistence.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecommendationDao extends JpaRepository<Recommendation, Long> {

    @Query("SELECT r from Recommendation r where r.garment_sku = :garment_sku")
    List<Recommendation> findByGarment_sku(String garment_sku);


    @Query("SELECT r from Recommendation r where r.outfit = :outfit")
    List<Recommendation> findByOutfit(String outfit);
}
