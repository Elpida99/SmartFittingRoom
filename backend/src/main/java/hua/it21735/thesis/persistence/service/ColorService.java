package hua.it21735.thesis.persistence.service;

import hua.it21735.thesis.persistence.dao.ColorDao;
import hua.it21735.thesis.persistence.model.Colour;
import hua.it21735.thesis.validator.EntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ColorService {

    @Autowired
    private ColorDao colorDao;

    @Autowired
    private EntityValidator entityValidator;

    public Long save(Colour colour) {
        entityValidator.validateColour(colour);
        if (getByCode(colour.getCode()) != null) {
            throw new IllegalStateException("Color code already exists");
        }
        if (getByName(colour.getName()) != null) {
            throw new IllegalStateException("Color name already exists");
        }
        Colour saved = colorDao.save(colour);
        return saved.getId();
    }

    public Colour getById(Long id) {
        Optional<Colour> color = colorDao.findById(id);
        return color.orElse(null);
    }

    public Colour getByName(String name) {
        Optional<Colour> color = colorDao.findByName(name);
        return color.orElse(null);
    }

    public Colour getByCode(String code) {
        Optional<Colour> color = colorDao.findByCode(code);
        return color.orElse(null);
    }
}
