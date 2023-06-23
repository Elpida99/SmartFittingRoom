package hua.it21735.thesis.persistence.dao;

import hua.it21735.thesis.persistence.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialDao extends JpaRepository<Material, Long> {

    List<Material> findByGarmentCode(String garmentCode);

}
