package hua.it21735.thesis.rest.controller;

import hua.it21735.thesis.persistence.model.Customer;
import hua.it21735.thesis.persistence.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping(path = "/customer",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CustomerResource {


    @Autowired
    CustomerService customerService;

    @GetMapping
    @RequestMapping(path = "/get/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.findAll());
    }


    @GetMapping
    @RequestMapping(path = "/get/phonenumber")
    public ResponseEntity<Customer> getByPhoneNumber(@RequestParam(name = "phoneNumber") String phoneNumber) {
        return ResponseEntity.ok(customerService.getByPhoneNumber(phoneNumber));
    }

    @GetMapping
    @RequestMapping(path = "/get/email")
    public ResponseEntity<Customer> getByEmail(@RequestParam(name = "email") String email) {
        return ResponseEntity.ok(customerService.getByEmail(email));
    }

    @GetMapping
    @RequestMapping(path = "/get/fullname")
    public ResponseEntity<List<Customer>> getByFullName(@RequestParam(name = "firstName") String firstName,
                                                        @RequestParam(name = "lastName") String lastName) {
        return ResponseEntity.ok(customerService.getByFirstNameAndLastName(firstName, lastName));
    }

    @PostMapping
    @RequestMapping(path = "/save")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {

        Long id = customerService.save(customer);
        return id != null ? ResponseEntity.ok(customer) : ResponseEntity.badRequest().build();
    }

    @PutMapping
    @RequestMapping(path = "/update")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {

        Customer updated = customerService.update(customer);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.badRequest().build();
    }

}
