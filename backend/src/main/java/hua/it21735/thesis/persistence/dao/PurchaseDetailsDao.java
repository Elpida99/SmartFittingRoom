package hua.it21735.thesis.persistence.dao;

import hua.it21735.thesis.persistence.model.PurchaseDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseDetailsDao extends JpaRepository<PurchaseDetails, Long> {

}
