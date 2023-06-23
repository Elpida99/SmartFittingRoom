package hua.it21735.thesis.persistence.dao;

import hua.it21735.thesis.persistence.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerDao extends JpaRepository<Customer, Long> {

    List<Customer> findByFirstNameAndLastName(String firstName, String lastName);

    Optional<Customer> findByPhoneNumber(String phoneNumber);

    Optional<Customer> findByEmail(String email);
}
