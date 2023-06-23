package hua.it21735.thesis.persistence.dao;

import hua.it21735.thesis.persistence.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreDao extends JpaRepository<Store, Long> {

    Optional<Store> findByName(String name);
}
