package hua.it21735.thesis.persistence.dao;

import hua.it21735.thesis.persistence.model.Colour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColorDao extends JpaRepository<Colour, Long> {

    Optional<Colour> findByName(String name);

    Optional<Colour> findByCode(String code);


}
