package hua.it21735.thesis.persistence.service;

import hua.it21735.thesis.persistence.dao.CustomerDao;
import hua.it21735.thesis.persistence.model.Address;
import hua.it21735.thesis.persistence.model.Customer;
import hua.it21735.thesis.validator.EntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private AddressService addressService;

    @Autowired
    private EntityValidator entityValidator;

    public Long save(Customer customer) {
//        entityValidator.validateCustomer(customer);

        Customer emailFound = getByEmail(customer.getEmail());
        if (emailFound != null) {
            throw new RuntimeException("Customer with this email already exists!");
        }
        Customer phoneFound = getByPhoneNumber(customer.getPhoneNumber());
        if (phoneFound != null) {
            throw new RuntimeException("Customer with this phone number already exists!");
        }
        Address savedAddress = addressService.findSameAddress(customer.getAddress());
        if (savedAddress == null) {
            savedAddress = addressService.save(customer.getAddress());
        }
        customer.setAddress(savedAddress);
        Customer saved = customerDao.save(customer);
        return saved.getId();
    }

    public Customer update(Customer customer) {
        System.out.println("CustomerService.update");
        Customer customerFound = getByEmail(customer.getEmail());

        if (customerFound != null) {
            customerFound.setAddress(addressService.getById(customerFound.getAddress().getId()));
            customerFound.setPhoneNumber(customer.getPhoneNumber());
            customerFound.setFirstName(customer.getFirstName());
            customerFound.setLastName(customer.getLastName());

            Address savedAddress = addressService.findSameAddress(customer.getAddress());

            if (savedAddress == null) {
                savedAddress = addressService.save(customer.getAddress());
                customerFound.setAddress(savedAddress);
            }


            return customerDao.save(customerFound);
        }
        return null;

    }

    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    public Customer getById(Long id) {
        Optional<Customer> customer = customerDao.findById(id);
        return customer.orElse(null);
    }

    public Customer getByPhoneNumber(String phoneNumber) {
        Optional<Customer> customer = customerDao.findByPhoneNumber(phoneNumber);
        return customer.orElse(null);
    }

    public Customer getByEmail(String email) {
        System.out.println("email = " + email);
        System.out.println("CustomerService.getByEmail");
        Optional<Customer> customer = customerDao.findByEmail(email);
        System.out.println(customer.toString());
        return customer.orElse(null);
    }

    public List<Customer> getByFirstNameAndLastName(String firstName, String lastName) {
        List<Customer> byFirstNameAndLastName = customerDao.findByFirstNameAndLastName(firstName, lastName);
        return byFirstNameAndLastName;
    }

}
