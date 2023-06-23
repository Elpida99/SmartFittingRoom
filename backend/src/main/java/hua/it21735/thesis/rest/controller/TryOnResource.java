package hua.it21735.thesis.rest.controller;

import hua.it21735.thesis.persistence.model.Customer;
import hua.it21735.thesis.persistence.model.Garment;
import hua.it21735.thesis.persistence.model.TryOn;
import hua.it21735.thesis.persistence.service.CustomerService;
import hua.it21735.thesis.persistence.service.GarmentService;
import hua.it21735.thesis.persistence.service.RecommendationService;
import hua.it21735.thesis.persistence.service.TryOnService;
import hua.it21735.thesis.rest.jpo.StoreAvailabilityJpo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping(path = "/tryOn",
//        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TryOnResource {

    //TODO: make these cookies?
    private static final String storeId = "S001";
    private static Long customerId = null;

    @Autowired
    TryOnService tryOnService;

    @Autowired
    GarmentService garmentService;

    @Autowired
    CustomerService customerService;

    @Autowired
    RecommendationService recommendationService;

    @GetMapping
    @RequestMapping(path = "/scanCard")
    public ResponseEntity<?> scanCustomerCard(@RequestParam("customerId") String id) {
        System.out.println("IN SCAN CARD " + id);
        String customerUsername = "";

        if (id != null) {
            Customer customer = customerService.getById(Long.valueOf(id));
            try {
                customerUsername = customer.getEmail().split("@")[0];
            } catch (Exception e) {
                if (customerId == null) {
                    throw new RuntimeException("There was a problem while scanning you card.\n Please try again.");
                }
            }
            if (customer != null) {
                customerId = Long.valueOf(id);
                System.out.println("CUSTOMER ID: " + customerId);
            }
        }

        return ResponseEntity.ok("{\"username\": \"" + customerUsername + "\"}  ");

    }

    @PostMapping
    @RequestMapping(path = "/scanGarment")
    public ResponseEntity<TryOn> scanGarment(@RequestParam("barcode") String barcode) {
        System.out.println(">>>>>>>>>>>>>>>>TryOnResource.scanGarment");
        System.out.println("customerId = " + customerId);
        TryOn tryOn;
        if (customerId == null) {
            tryOn = tryOnService.guestTryOn(Long.valueOf(barcode), storeId);
        } else {
            tryOn = tryOnService.customerTryOn(Long.valueOf(barcode), customerId, storeId);
        }
        return ResponseEntity.ok(tryOn);
    }

    @GetMapping
    @RequestMapping(path = "/availability")
    public ResponseEntity<List<StoreAvailabilityJpo>> getGarmentAvailability(@RequestParam("barcode") String barcode) {
        System.out.println(">>>>>>>>>>>>>>>>TryOnResource.getGarmentAvailability");
        List<StoreAvailabilityJpo> storeAvailabilityJpos = garmentService.getGarmentAvailability(Long.valueOf(barcode));
        return ResponseEntity.ok(storeAvailabilityJpos);

    }

    @GetMapping
    @RequestMapping(path = "/logout")
    public ResponseEntity<String> logout() {
        System.out.println("TryOnResource.logout");
        customerId = null;
        System.out.println("customerId = " + customerId);
        return ResponseEntity.ok("Success");

    }

    @GetMapping
    @RequestMapping(path = "/isloggedin")
    public ResponseEntity<String> isloggedin() {
        System.out.println("TryOnResource.isloggedin");
        String isloggedIn = "none";
        String email="";
        if (customerId != null) {
            email = customerService.getById(customerId).getEmail();
            isloggedIn = email.split("@")[0];
        }
        System.out.println("isloggedIn = " + isloggedIn);
        return ResponseEntity.ok("{\"loggedIn\": \"" + isloggedIn + "\",\n\t\"email\": \"" +email+ "\"\n}  ");

    }

    @GetMapping
    @RequestMapping(path = "/outfits")
    public ResponseEntity<List<Garment>> recommend(@RequestParam("sku") String sku) {
        return ResponseEntity.ok(recommendationService.getOutfit(sku));

    }

    @GetMapping
    @RequestMapping(path = "/myTryons")
    public ResponseEntity<List<TryOn>> getCustomerTryons() {
        System.out.println("......................................TryOnResource.getCustomerTryons");
        if (customerId != null) {
            return ResponseEntity.ok(tryOnService.getTryOnsByCustomer(customerId));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
