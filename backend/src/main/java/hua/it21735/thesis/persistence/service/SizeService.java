package hua.it21735.thesis.persistence.service;

import hua.it21735.thesis.persistence.dao.SizeDao;
import hua.it21735.thesis.persistence.model.Size;
import hua.it21735.thesis.persistence.model.SizeEnum;
import hua.it21735.thesis.persistence.model.TargetConsumer;
import hua.it21735.thesis.validator.EntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SizeService {

    @Autowired
    private SizeDao sizeDao;

    @Autowired
    private EntityValidator validator;

    public Size save(Size size) {
        validator.validateSize(size);
        boolean sizeExists = sizeDao.findAll()
                .stream()
                .anyMatch(size1 -> size1.getSize().equals(size.getSize())
                        && size1.getGender().equals(size.getGender()));
        if (sizeExists) {
            throw new IllegalStateException("Size " + size.getSize() + " for " + size.getGender().toString() + " already exist! ");
        }
        if (size.getId() == null) {
            return sizeDao.save(size);
        } else {
            throw new IllegalStateException("Id must be null!");
        }
    }

    public List<Size> findAll() {
        return sizeDao.findAll();
    }

    public Size findById(Long id) {
        Optional<Size> sizeFound = sizeDao.findById(id);
        return sizeFound.orElse(null);
    }

    public Size findBySizeAndGender(SizeEnum size, TargetConsumer gender) {
        Optional<Size> sizeFound = sizeDao.findBySizeAndGender(size, gender);
        return sizeFound.orElse(null);

    }

    public List<Size> findBySize(SizeEnum size) {
        return sizeDao.findBySize(size);
    }

    public List<Size> findByGender(TargetConsumer gender) {
        return sizeDao.findByGender(gender);
    }
}
