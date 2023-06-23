package hua.it21735.thesis.persistence.dao;

import hua.it21735.thesis.persistence.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressDao extends JpaRepository<Address, Long> {

    Optional<Address> findSameAddress(String city, String number, String postCode, String street);

}
