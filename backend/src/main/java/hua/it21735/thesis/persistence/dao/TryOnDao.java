package hua.it21735.thesis.persistence.dao;

import hua.it21735.thesis.persistence.model.Customer;
import hua.it21735.thesis.persistence.model.TryOn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TryOnDao extends JpaRepository<TryOn, Long> {

    List<TryOn> findByCustomer(Customer customer);

}
