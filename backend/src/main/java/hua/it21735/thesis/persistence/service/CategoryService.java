package hua.it21735.thesis.persistence.service;

import hua.it21735.thesis.persistence.dao.CategoryDao;
import hua.it21735.thesis.persistence.model.Category;
import hua.it21735.thesis.validator.EntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private EntityValidator entityValidator;

    public Long save(Category category) {
        entityValidator.validateCategory(category);
        Category saved = categoryDao.save(category);

        return saved.getId();
    }

    //todo update

    public Category getById(Long id) {
        Optional<Category> categoryOptional = categoryDao.findById(id);
        return categoryOptional.orElse(new Category());
    }


    public Category getByName(String name) {
        Optional<Category> categoryOptional = categoryDao.findByCategoryName(name);
        return categoryOptional.orElse(new Category());
    }

    public Category getByCode(String code) {
        Optional<Category> categoryOptional = categoryDao.findByCategoryCode(code);
        return categoryOptional.orElse(new Category());
    }

    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}
