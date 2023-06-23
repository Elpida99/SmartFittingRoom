package hua.it21735.thesis.persistence.service;

import hua.it21735.thesis.persistence.dao.AddressDao;
import hua.it21735.thesis.persistence.model.Address;
import hua.it21735.thesis.validator.EntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private EntityValidator validator;

    public Address getById(Long id) {
        Optional<Address> found = addressDao.findById(id);
        return found.orElse(new Address());
    }

    public List<Address> findAll() {
        return addressDao.findAll();
    }

    public Address save(Address address) {
        validator.validateAddress(address);

        return addressDao.save(address);
    }

    public Address findSameAddress(Address address) {
        Optional<Address> found = addressDao.findSameAddress(address.getCity(), address.getNumber(), address.getPostCode(), address.getStreet());
        return found.orElse(null);
    }
}
