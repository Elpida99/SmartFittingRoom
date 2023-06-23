package hua.it21735.thesis.persistence.dao;

import hua.it21735.thesis.persistence.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryDao extends JpaRepository<Category, Long> {

    Optional<Category> findByCategoryName(String categoryName);

    Optional<Category> findByCategoryCode(String categoryCode);
}
